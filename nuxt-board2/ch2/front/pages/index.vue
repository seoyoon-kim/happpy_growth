<template>
  <v-container>
    <post-form v-if="me" />
    <div>
      <post-card v-for="p in mainPosts" :key="p.id" :post="p" />
    </div>
  </v-container>
</template>

<script>
  import PostCard from '~/components/PostCard';
  import PostForm from '~/components/PostForm';
  export default {
    components: {
      PostCard,
      PostForm,
    },
    data() {
      return {
        name: 'Nuxt.js',
      };
    },
    computed: {
      me() {
        return this.$store.state.users.me;
      },
      mainPosts() {
        return this.$store.state.posts.mainPosts;
      },
      hasMorePost() {
        return this.$store.state.posts.hasMorePost;
      }
    },
    fetch({ store }) {
      //컴포넌트가 mount 되기 전에 store에서 비동기적으로 데이터 가져올 때 
       store.dispatch('posts/loadPosts');
    },
    mounted() {
      window.addEventListener('scroll', this.onScroll);
    },
    beforeDestroy() {
      window.removeEventListener('scroll', this.onScroll);
     
      //window는 mounted에서만 사용 가능
      //F12 창에서 window.scrollY로 스크롤 얼마나 내렸는지 확인 가능
      //document.documentElement.clientHeight 스크롤을 고려하지 않은 클라이언트 화면의 높이
      //document.documentElement.scrollHeight 스크롤을 다 내렸을 때의 전체 화면의 높이
      //스크롤을 제일 끝으로 내렸을 때 window.scrollY  + document.documentElement.clientHeight === document.documentElement.scrollHeight 
    },
    methods: {
      onScroll() {
        console.log('scroll');
        if (window.scrollY + document.documentElement.clientHeight > document.documentElement.scrollHeight - 300) {
          //300px 남았을 때 다음 스크롤 로딩해옴
          if (this.hasMorePost) {
            this.$store.dispatch('posts/loadPosts');
          }
        }
      },
    },
  };
</script>

<style>

</style>