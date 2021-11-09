import createPersistedState from "vuex-persistedstate";
  
export const state = () => ({
    hello: 'vuex',
  });
  
  export const mutations = {
    bye(state) {
      state.hello = 'goodbye';
    },
    addMainPost() {
  
    },
  };