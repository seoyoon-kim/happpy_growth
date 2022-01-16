const express = require('express');
const cors = require('cors');
const bcrypt = require('bcrypt');
const passport = require('passport');
const session = require('express-session');
const cookie = require('cookie-parser');
const morgan = require('morgan');

const db = require('./models');
const passportConfig = require('./passport');
const usersRouter = require('./routes/users');
const postRouter = require('./routes/post'); 
const postsRouter = require('./routes/posts');
const hashtagRouter = require('./routes/hashtag');
const app = express();

db.sequelize.sync({force: true}); //db 실행
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
	res.status(200).send('안녕 growth');
});

app.use('/user', usersRouter); //user + Router의 주소

app.post('/user', async (req, res, next) =>{ //회원가입
	try{
		const has = await bcrypt.hash(req.body.password, 12);
		const exUser = await db.User.findOne({
			where : {
				email : req.body.email,
			},
		});
		if (exUser) { //회원가입 되어있으면
			return res.status(403).json({
				errorCode : 1,
				message : '이미 회원가입 되어있습니다',				
			});	
		}
		await db.User.create({
			email: req.body.email,
			password: hash,
			nickname: req.body.nickname,

		}); //HTTP STATUS CODE
		passport.authenticate('local', (err, user, info) =>{
			if (err){
				console.log(err);
				return next(err);
			}
			if (info){
				return res.status(401).send(info.reason);
			}
			return req.login(user, async (err) =>{ //세션에다 사용자 정보 저장 : serializeUser
				if (err){
					console.error(err);
					return next(err);
				}
				return res.json(user);
			});
		})(req, res, next);
	} catch (err){
		console.log(err);
		return next(err);
	}
});

app.post('/user/login', (req, res, next) => {
	passport.authenticate('local', (err, user, info) => {
		if (err){
			console.error(err);
			return next(err);
		}
		if (info) {
			return res.status(401).send(info.reason);
		}
		return req.login(user, async(err) => { //session에 사용자 정보 저장 : serializeUser
			if (err){
				console.log(err);
				return next(err);
			}
			return res.json(user);
		});
	})(req, res, next);
}); 

app.post('/user/logout', (req, res) => {
	if (req.isAuthenticated()){
		req.logout();
		teq.session.destroy(); //session에 사용자 정보 아닌 다른 정보도 있을 수 있기 때문에, 이 코드는 선택사항.
		return res.status(200).send('로그아웃 되었습니다.');
	}
});

app.post('/post', (req, res) => {
	if (req.isAuthenticated()) {
		
	}
  });

app.listen(3085, () => {
	console.log(`백엔드 서버 ${3085}번 포트에서 작동중.`);
});
 //1대 다 관계