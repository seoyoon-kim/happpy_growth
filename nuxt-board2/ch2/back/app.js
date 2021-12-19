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
const postsRouter = require('./routes/posts');
const hashtagRouter = require('./routes/hashtag');
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
app.use('/posts', postsRouter);
app.use('/hashtag', hashtagRouter); 
  
app.listen(3085, () => {
	console.log(`백엔드 서버 ${3085}번 포트에서 작동중.`);
});
 //1대 다 관계