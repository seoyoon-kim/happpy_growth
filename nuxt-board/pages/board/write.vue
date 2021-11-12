<template>
    <main>
        <nav>    
            <NuxtLink to="/">Home</NuxtLink>
        </nav> 
        <div >
            <h3>Write</h3>
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
                            <li><label>{{lists.length+1}}</label> </li> 
                            <li><li class="writeArea-li"><input type="text" placeholder="제목을 입력하세요" v-model="title"></li>  
                            <li class="writeArea-li"><input type="text" placeholder="아이디를 입력하세요" v-model="id"></li> 
                            <li id="writeArea-last-li"><label>{{date}}</label></li>  
                        </ul>
                        <div id="writeContent">  
                            <textarea cols="300" rows="20" placeholder="내용을 입력하세요" v-model="contents"></textarea> 
                        </div>
                    </div>   
                    <div class="btnBox"> 
                        <input type="submit" value="글쓰기" class="btn write-btn" @click="doWrite"/> 
                        <NuxtLink to="/"> <input type="submit" value="취소" class="btn cancel-btn"/></NuxtLink> 
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
            no: '', //화면에 띄워줄 경우  no: "1"
            id: "",
            title: "",
            contents: "",
            date:"", 
          
            }
    },  
    computed: { 
        ...mapState({
        writeContents: state => state.writeContents 
        }) ,
       ...mapGetters({
            lists: 'list'
            // store에 폴더별로 생성할 경우 lists: 'board/board/list'
        }),
    }, 
    created() {
        this.getDate();
        //setInterval(this.getDate, 100); 
    }, 
    methods: {    
        getDate() { 
            const today = new Date();
            let year =  today.getFullYear();
            let month = today.getMonth() + 1;
            let day =  today.getDate();

            function getDigit( num ){ 
                if( 9 < parseInt(num) ){
                    return num;
                } 
                return "0" + num;
            } 
            this.date= [year, getDigit(month), getDigit(day)].join("/");
            //const date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            //const time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
            //const dateTime = date +' '+ time;
        }, 
        doWrite(){  

            let param ={
                no: this.no,
                id: this.id,
                title: this.title,
                contents: this.contents,
            }  

            this.$store.dispatch("doWrite", param); 
             
            this.$router.push({
                path:'/board' 
            });
                   
        },   
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
    #writeArea li:not(#writeArea li:last-of-type), .writeArea-li{
        border-bottom: 1px solid lightgray; 
    }
    #writeArea-last-li{ 
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
    /*write의 댓글*/
    .replyContent{
        padding: 5px 0 5px 15px;
        font-size: 14px;
    }
    .replyContent li{
        margin-bottom: 5px; 
        padding: 5px 0 0 10px; 
        display: inline-block;
    }
    .replyContent li:first-child{
        width:1600px;
    }
    .replyContent li:nth-of-type(2){
        width:100px;
    }
    .replyContent:not(.replyContent:last-child){
        border-bottom: lightgray 1px dotted;
    }
    #writeReply{
        padding: 8px 0 8px 0;
        display: flex;
    }
    #writeReply input{
        width:1500px;
        margin: 0 205px 0 30px;  
    }
    #writeReply button{
        padding: 0.2rem;
        border-radius: 30px;
    } 
    input[type=submit], .btn{
        width:100px !important;
        padding:10px 0 10px 0;
        border-radius: 5px; 
    }
    textarea{
        border:none;
        padding:10px;
    }
</style>