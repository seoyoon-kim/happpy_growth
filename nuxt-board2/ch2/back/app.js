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
passportConfig();

app.use(morgan('dev'));
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
    const hash = awiat bcrypt.hash(req.body.password, 12); //비밀번호 암호화, 숫자는 암호화 정도라 높을수록 암호화 강함
    const newUser = await db.User.create({
      email : req.body.email, 
      password : hash,
      nickname : req. body.nickname
    });
    res.status(201).json(newUser); //200은 성공, 201은 성공적으로 생성됨
  }catch(err){
    console.log(err);
    next(err);
  }
});

app.listen(3085, () => {
  console.log(`백엔드 서버 ${3085}번 포트에서 작동중.`);
});

//모델 수정과 응답 종류