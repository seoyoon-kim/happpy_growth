module.exports = {
    head:{
        title: '게시판',
    },
    modules:[
        ['@nuxtjs/vuetify',
        'nuxt-validate', {
            lang: 'en',
            nuxti18n: {
              locale: {
                'ko': 'ko'
              }
            } 
        }]
    ],   
    plugins: [ { src: '~plugins/persistedState.js', } ], 
 
};