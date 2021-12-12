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
      const user = await db.User.findOne({ where: { id } });
      return done(null, user); // req.user, req.isAuthenticated() === true,
    } catch (err) {
      console.error(err);
      return done(err);
    }
  });
  local();
};
