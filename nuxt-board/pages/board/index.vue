 <template>  

  <div> 
        <nav>    
            <NuxtLink to="/">Home</NuxtLink>
        </nav> 
        <div class="container">
            <h3>List</h3>
            <ul id="boardSubject">
                <li>번호</li>
                <li>제목</li>
                <li>작성자</li>
                <li>작성일</li> 
                <li>댓글</li> 
            </ul>
            <ul id="boardContent" v-for="list in lists" :key="list.no">
                <li>{{list.no}}</li>  
                <NuxtLink :to="'/board/'+list.no"><li>{{list.title}}</li></NuxtLink>
                <li>{{list.id}}</li>
                <li>{{list.date}}</li> 
                <li>{{list.replyCount}}</li> 
            </ul> 
        </div> 
        <div class="searchBox">
            <form method="get" id="searchForm" action="#">
                <select name="searchKey">
                    <option value="subject">제목</option>
                    <option value="userid">작성자</option>
                    <option value="contents">글내용</option>
                </select>			
                <input type="text" name="searchWord" id="searchWord"/>
                <input type="submit" value="Search"/>
            </form> 
                <input type="submit" value="글쓰기" class="btn list-btn" @click="goWrite"/>
            
        </div>  
  </div>
</template>

<script>     
    import { mapState, mapMutations, mapActions, mapGetters } from 'vuex'  
  
    export default {   
        name:'boardList',     
        computed: {
            ...mapGetters({
                lists: 'list' 
            }),
        }, 
        methods: { 
            goWrite () {
                this.$router.push({
                        path:'/board/write'
                })
            }  , 
            // getReplyCount(){ string->json/JSON->String
            //     let list = JSON.parse(JSON.stringify($nuxt.$store.getters.list)) ;   
            //     const replies = $nuxt.$store.getters.replies;  
                
            // if( list === null || list.length === 0 ){
            //     return;
            // }
            // console.log("list", list);  
            // },  
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
    
    .nuxt-link.nuxt-link:visited{
        color: black;
    }
    a:hover{
        color:royalblue;
    } 
    #boardSubject, #boardContent{
        display: flex;
    }    
    input:not(.searchBox input){ 
        border:none;
    }  
    .container{ 
        border-bottom: darkgray 1px solid;
        margin-left:10px;
    }
    .container ul{ 
        padding-top:5px;
        padding-bottom: 5px;
    }
    /*게시판 제목*/
    #boardSubject{ 
        border-top: darkgray 1px solid;
        border-bottom: darkgray 1px solid;  
        font-weight: bold;
        background-color:black;
    }
    #boardSubject li{
        margin-left:40px;
        color: white;
    }
    #boardSubject li:nth-of-type(2){
        margin-left:100px;
    }
    #boardSubject li:nth-of-type(3){
        margin-left:1200px;
    } 
    #boardSubject li:nth-of-type(4){
        margin-left:200px;
    } 
    #boardSubject li:nth-of-type(5){
        margin-left:100px;
    }

    /*게시판 내용*/
    #boardContent:not(#boardContent:last-of-type){    
        border-bottom: lightgray 1px solid;
    }
    #boardContent li{ 
        margin-left:48px; 
    }
    #boardContent li:nth-of-type(1){/*번호*/
        margin-right:70px;
    }
    #boardContent a{/*제목*/
        width:1200px; 
    } 
    #boardContent>a>li:hover{ 
        color:royalblue;
    }
    #boardContent li:nth-of-type(2){/*작성자*/
        margin-right:100px; 
        width:110px;
        text-align: center;
    }
    #boardContent li:nth-of-type(3){/*작성일*/
        margin-right:45px;
    }

    /*검색*/
    .searchBox{
        margin-top:20px;
        display: flex; 
        padding-left:10px;
    } 
    .searchBox form{
        width:400px;
    }
    .btn{
        border-radius: 4px; 
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        padding: 0.5rem 1rem;
        width:100px; 
        border: lightgray 1px solid; 
    }
    .list-btn{
        margin-left:1350px; 
    }
    .btn:hover{
        background-color: gray;
        font-weight: bold;
    }
    input{
      border:1px black solid;
      border-radius: 5px;
      padding:2px;
    }  
</style>