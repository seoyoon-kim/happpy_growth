const passport = require('passport');
const local = require('./local');
const db = require('../models');

module.exports = () => {
  passport.serializeUser((user, done) => {
    //req.user.login의 user를 받아 아이디만 메모리에 저장
    return done(null, user.id);
  });
  passport.deserializeUser(async (id, done) => {
    try {
      const user = await db.User.findOne({ where: { id } }); //프론트는 db접속을 줄이고 캐싱을 통해 해결한다.
      return done(null, user); // req.user에 정보를 리턴함, req.isAuthenticated() === true 로 전환,
    } catch (err) {
      console.error(err);
      return done(err);
    }
  });
  local();
};
