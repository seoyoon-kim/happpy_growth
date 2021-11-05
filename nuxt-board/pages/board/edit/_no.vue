<template>
    <main>
        <nav>    
            <NuxtLink to="/">Home</NuxtLink>
        </nav> 
        <div >
            <h3>Edit</h3>
            <div id="writeApp"> 
                <div id="bottomLine">
                    <ul id="writeSubject">
                        <li>
                            번호
                        </li>
                        <li>
                            제목
                        </li>
                        <li>
                            작성자
                        </li>
                        <li>
                            작성일
                        </li> 
                    </ul>
                    <div id="writeBox">
                         <ul id="writeArea">
                            <li><label>{{content.no}}</label></li> 
                            <li><input type="text" v-model="content.title" ></li>
                            <li><label id="date">{{content.id}}</label> 
                            <li><label id="date">{{content.date}}</label></li>
                        </ul>
                        <div id="writeContent">
                            <textarea cols="309" rows="20"  v-model="content.contents"></textarea> 
                        </div> 
                    </div>
                    <div class="btnBox"> 
                        <input type="submit" value="수정하기" class="btn write-btn" @click="doUpdate"/> 
                        <input type="submit" value="취소" class="btn cancel-btn" @click="goBack"/>
                    </div>  
                </div> 
            </div>      
        </div> 
    </main> 
</template>
 
<script>    
  import { mapState, mapGetters } from 'vuex'

  export default {    
      data(){
        return{   
            title: "",
            contents: "", 
            content: []
        }
      }, 
      async asyncData(){ 
        const list = $nuxt.$store.getters.list; 
        const param = $nuxt.$route.params.no; 
        const content = await list.filter( l => l.no.toString() === param)[0];    
        return { param, content } 
      }, 

    computed: { 
        ...mapState({
        lists: state => state.writeContents 
      }) ,
       ...mapGetters({
            lists: 'list' 
         }),
    },

    watch:{
        // title(newVal, oldVal){
        //     console.log("title", newVal);
        // }
    }, 
    methods: {      
        doUpdate(){   
            let param ={ 
                contents: this.content.contents, 
                date: this.content.date,
                id: this.content.id,
                no: this.content.no,
                title: this.content.title
            }     
            this.$store.dispatch("doUpdate", param);  
            
            this.$router.push({
                path:'/board/no'   
            }); 
        },  
        goBack(){
            this.$router.push({
                path:'/board/no'  
            });
        }
    } 
  }
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
    #writeApp, h3{
        margin-left:10px;
    }
    input:not(.searchBox input){ 
        border:none;
    }  
    #writeApp{
        border-top: 1px solid gray; 
    } 
    #writeArea li{ 
        padding: 7px 0 6px 20px;
        width:1770px;
    }  
    #writeArea li:not(#writeArea li:last-of-type){
        border-bottom: 1px solid lightgray;
    }
    #writeArea li:nth-of-type(4){ 
        border-bottom: 1px solid darkgray;
    }
    #writeBox{ 
        position: relative;
        top:-128px;
        left:100px;
    } 
    #bottomLine{
        border-bottom: 1px solid gray; 
        height:500px;
    }
    #writeSubject li{
        padding-top:5px;
        padding-bottom: 5px;
        border-bottom: 1px solid darkgray;
        width:100px;
        text-align: center;
        background-color: lightgray;    
        border-right: 1px solid darkgray;
    }   
    textarea{
        position: relative;
        left:-100px;
        top: 25px;
    }
    .btnBox{
        display:flex;
        margin:30px 0 0 750px; 
    }
    .btnBox>form:nth-of-type(2){
        width:100px;
    } 
    #writeArea>li:nth-of-type(4){
        height:18px; 
    }
    #writeArea>li:nth-of-type(5){
        padding: 0px; 
    }
    #writeArea>li:nth-of-type(6){ 
        padding-top: 0px;
    }
    .cancel-btn{ 
        margin-left:30px;
    }
    #date{
        font-size:14px;
    }
    #writeArea input{
        width:800px;
    } 
    .btn{
    width:80px;
    }
    .edit-btn{
        margin-right: 30px;;
    }
    input[type=submit], .delete-btn, .edit-btn{
    width:100px !important;
    padding:10px 0 10px 0;
    border-radius: 5px;
    }
</style>