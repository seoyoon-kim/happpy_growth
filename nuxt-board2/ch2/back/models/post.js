module.exports = (sequelize, DataTypes) => {
    const Post = sequelize.define('Post', { // 모델명 : Post, 테이블명 : posts
      content: {
        type: DataTypes.TEXT, // 매우 긴 글
        allowNull: false,
      }, // createdAt, updatedAt 자동생성
    }, {
      charset: 'utf8mb4', //한글 및 이모티콘 허용
      collate: 'utf8mb4_general_ci',
    });
    Post.associate = (db) => {
      db.User.hasMany(db.Post);
      db.Post.belongsTo(db.User); // UserId
      db.Post.hasMany(db.Comment);
      db.Post.hasMany(db.Image);
      db.Post.belongsToMany(db.Hashtag, { through: 'PostHashtag' });
    };
    return Post;
  };