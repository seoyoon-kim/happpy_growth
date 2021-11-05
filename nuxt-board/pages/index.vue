<template>
  <div> 
        <nav>    
            <h1>This is home</h1>
            <NuxtLink to="/board">List</NuxtLink>
        </nav>    
  </div>
</template>
 
<script> 
</script>

<style scoped>
    *{
        list-style-type: none; 
        font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
        text-decoration: none; 
        padding:0;
        margin:0;
        color:black;
    }    
    nav{
        padding:10px;
        font-size:30px;
        font-weight: bold;
    }
    
    .nuxt-link.nuxt-link:visited{
        color: black;
    }
    a:hover{
        color:royalblue;
    }   
</style>


