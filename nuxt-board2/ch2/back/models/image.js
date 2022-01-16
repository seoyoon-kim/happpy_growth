module.exports = (sequelize, DataTypes) => {
    const Image = sequelize.define('Image', {
      src: {
        type: DataTypes.STRING(200),
        allowNull: false,
      },
    }, {
      charset: 'utf8',
      collate: 'utf8 mb4_general_ci', // 한글 저장
    });
    Image.associate = (db) => {
      db.Image.belongsTo(db.Post); //사용자는 여러 개의 게시글을 쓸 수 있다.
    };
    return Image;
  };