const express = require('express');
const cors = require('cors');
const bcrypt = require('bcrypt');
const passport = require('passport');
const session = require('express-session');
const cookie = require('cookie-parser');
const morgan = require('morgan');

const db = require('./models');
const passportConfig = require('./passport');
const userRouter = require('./routes/user');
const postRouter = require('./routes/post');
const app = express();

db.sequelize.sync(); //db 실행
//db.sequelize.sync({ force:true }); //서버 시작시마다 db 테이블 날리고 새로 만듦
passportConfig();


app.use(morgan('dev'));//보통 위에 작성. 서버에 요청이 왔을 때 콘솔에 기록해줌
//메서드(get), 주소, 응답코드, 소요 시간, 응답 용량
//app.use는 req, res를 조작
app.use(cors({ //허용하는 주소 지정
  origin: 'http://localhost:3000',
  credentials: true,
}));
app.use('/', express.static('uploads'));
app.use(express.json()); // body가 json인지 확인하여 req.body에 파싱
app.use(express.urlencoded({ extended: false }));//form을 통해 전송할 때 데이터를 req.body에 넣어줌
app.use(cookie('cookiesecret'));
app.use(session({
  resave: false,
  saveUninitialized: false,
  secret: 'cookiesecret',
  cookie: {
    httpOnly: true,
    secure: false,
  },
}));
app.use(passport.initialize());
app.use(passport.session());

app.get('/', (req, res) => {
  res.status(200).send('안녕 제로초');
});

app.use('/user', userRouter);
app.use('/post', postRouter);

app.post('/post', (req, res) => {
  if (req.isAuthenticated()) {

  }
});

app.post('/user', async (req, res, next)=>{
  try{
    const hash = await bcrypt.hash(req.body.password, 12); //비밀번호 암호화, 숫자는 암호화 정도라 높을수록 암호화 강함
    const exUser = await db.User.findOne({
      where:{ email : req.body.email }     
    });
    if(exUser){//이미 회원가입 되어있으면
      return res.status(403).json({//403은 금ㄱ지
        errorCode: 1,//임의 지정
        message:'이미 회원가입 되어있습니다',
      })
      //return res.status(403).send('이미 회원가입 되어있습니다')//잘못된 요청
    }
    const newUser = await db.User.create({
      email : req.body.email, 
      password : hash,
      nickname : req. body.nickname
    });
    res.status(201).json(newUser); //200은 성공, 201은 성공적으로 생성됨
  }catch(err){
    console.log(err);
    return res.status(500).json({
      message:'회원가입 에러 발생'
    });
    next(err);
  }
});

const user = {};

app.post('/user/login', (req, res) => {
//   req.body.email;
//   req.body,password;
//   await db.User.findOne(); //세션에 저장
//   user[cookie] = 유저정보;  //프런트에 쿠키 내려주기
passport.authenticate('local', (err, user, info) => {//에러, 성공, 실패
	if(err){
		console.error(err);
		return next(err);
	}
	if(info){
		console.error(err);
		return res.status(401).send(info.reason);//잘못된 요청으로 서버에서 거절
	}
	return req.login(user, async(err)=>{ //세션에 사용자 정보 저장(어떻게? serializeUser)
		if(err){
			console.error(err);
			return next(err);
		}	 
		return res.json(user); //프론트에 사용자 정보 넘겨주기
	})
})(req, res, next);
  
});

app.listen(3085, () => {
  console.log(`백엔드 서버 ${3085}번 포트에서 작동중.`);
});

 