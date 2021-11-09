<template>
  <div>
    <div id="main-banner"></div>     
    <div id="newBook">

    </div>
    <div id="popularBook"></div>
    <div id="recommendedBook"></div> 
 
  </div>


</template>

<script> 
  import MainBookList from '~/components/MainBookList'; 
  
  export default {
    components: {
      MainBookList,
    }, 
    head() {
      return {
        title: '도써관',
      };
    },
  }
</script>

<style>
</style>