const passport = require('passport');
const bcrypt = require('bcrypt');
const { Strategy: LocalStrategy } = require('passport-local');
const db = require('../models');

module.exports = () => {
  passport.use(new LocalStrategy({
    usernameField: 'email', // req.body.email 검사, 아래 async로 들어감
    passwordField: 'password', // req.body.password 검사, 아래 async로 들어감
  }, async (email, password, done) => {
    try {
      const exUser = await db.User.findOne({ where: { email } });//db에서 검색
      if (!exUser) {
        return done(null, false, { reason: '존재하지 않는 사용자입니다.' });
      }
      const result = await bcrypt.compare(password, exUser.password);
      if (result) {
        return done(null, exUser);
      } else {
        return done(null, false, { reason: '비밀번호가 틀립니다.' });
      }
    } catch (err) {
      console.error(err);
      return done(err);
    }
  }));
};