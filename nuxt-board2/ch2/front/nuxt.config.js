module.exports = {
    head:{
        title: '게시판',
    },
    modules:[
        '@nuxtjs/vuetify'
    ],
    plugins: [ { src: '~plugins/persistedState.js', } ], 
 
};