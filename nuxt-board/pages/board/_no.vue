<template>
  <main>
    <nav>    
        <NuxtLink to="/">Home</NuxtLink>
    </nav> 
    <div >
        <h3>Read</h3>
        <div id="readContainer">
            <div id="readBox">
                <ul id="readSubject">
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
                <ul id="readInfo">  
                    <li>{{content.no}}</li>
                    <li>{{content.title}}</li>
                    <li>{{content.id}}</li>
                    <li>{{content.date}}</li>  
                    <li id="readContent"> 
                        <article>
                            {{content.contents}}
                        </article> 
                    </li>   
                    <li>댓글</li>  
                </ul>  
            </div>    
            <div id="replyBox">
                <ul class="replyContent" v-for="reply in replyContent" :key="reply.no">  
                    <li>{{reply.replyContent}}</li>  
                    <li>{{reply.id}}</li>
                    <li>{{reply.date}}</li>
                </ul> 
                <ul id="writeReply">
                    <li><input type="text" placeholder="┗ 댓글을 입력하세요" v-model="newReplyContent" ></li>
                    <li><input type="text" placeholder="ID를 입력하세요" v-model="replyId"></li>
                    <li><button class="btn" id="replyWrite" @click="doWriteComment">댓글쓰기</button></li>
                </ul>
            </div>    
        </div>    
    </div>
    <div class="btnBox"> 
        <NuxtLink :to="'/board/edit/'+content.no"><input type="button" value="수정하기" class="btn edit-btn"/></NuxtLink>
        <input type="button" value="삭제하기" class="btn delete-btn" @click="doDelete"/>  
        <input type="submit" value="목록보기" class="btn cancel-btn" @click="goLists"/> 
    </div>
  </main> 
</template>
 
<script>  
import { mapState, mapMutations, mapActions, mapGetters } from 'vuex' 
 
export default { 
    name: 'boardRead',
    data() {
      return {
            replyNo: '',
            listNo:'',
            replyId: '',
            newReplyContent: '',
            replyDate:''
      };
    },
    async asyncData(){ 
        const list = $nuxt.$store.getters.list;  
        const param = $nuxt.$route.params.no; 
        const content = await list.filter( l => l.no.toString() === param)[0]; 
        const replies = $nuxt.$store.getters.replies; 
        const replyContent = await replies.filter( r => r.listNo.toString() === param); 
        
        return { param, content, replyContent } 
    }, 
    watch: { //watch
        newReplies(newVal, oldVal){
            console.log("watch", newVal);
        }
    },    
    created() {
        this.getDate();  
    },
    methods: {   
        goLists(){ 
            this.$router.push({
            path: '/board'
            }) 
        },   
        doDelete(){ 
            this.$store.dispatch('doDelete', {
                no:this.content.no
            })
            .then(() => {
                this.$router.push({
                path: '/board'
            })
            }) 
            .catch(() => {
            });
        },  
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
        },  

        
        async doWriteComment(){ 
            const replies = $nuxt.$store.getters.replies; 
            const nextReplyNo = replies.length+1;  
        
            let newReplyContent ={
                replyNo: nextReplyNo,
                listNo:this.content.no,
                replyId: this.replyId,
                newReplyContent: this.newReplyContent,
                replyDate:this.date
            }     
            this.$store.dispatch("doWriteComment", newReplyContent); 
            let newReplies = await $nuxt.$store.getters.replies; 
            
            this.newReplies =  newReplies.filter( r => r.listNo.toString() === $nuxt.$route.params.no); 


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
input:not(.searchBox input){ 
    border:none;
}  
#readContainer, h3{
  margin-left:10px;
}
#readContainer{
    border-top: 1px solid gray; 
    border-bottom: 1px solid gray;  
} 
#readInfo{
    width:1700px;
}
#readInfo li{ 
    padding: 7px 0 6px 20px;
    width:1770px;
    height:18px;
}  
#readInfo li:not(#readInfo li:nth-of-type(5)){
    border-bottom: 1px solid lightgray;
}
#readInfo li:nth-of-type(4){ 
    border-bottom: 1px solid darkgray;
}
#readInfo li:nth-of-type(6){ 
    border-top:1px solid darkgray;
    border-bottom:1px solid darkgray;
    padding:10px 0 10px 30px;
    position:relative;
    left:-100px;
    width:1860px;
} 
#readBox, #writeBox{
    display: flex;
}
#readBox{
    margin-bottom: 30px;
}
#readSubject li{
    padding-top:5px;
    padding-bottom: 5px;
    border-bottom: 1px solid darkgray;
    width:100px;
    text-align: center;
    background-color: lightgray;    
    border-right: 1px solid darkgray;
}    
#readContent>article{
    line-height: 200%; 
    padding:20px;
    position:relative;
    left:-100px;  
}  
.btnBox{
    display:flex;
    margin:30px 0 0 750px;
} 
.btnBox>form:nth-of-type(2){
    width:100px;
} 
#readInfo>li:nth-of-type(5){
    padding: 0 10px; 
    margin-bottom: 30px;
}
#readInfo>li:nth-of-type(6){  
    position: relative;
    top:30px;

}
.cancel-btn{ 
    margin-left:30px;
}
/*Read의 댓글*/
#replyBox{
    position: relative;
    top:10px; 
}
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
    padding: 5px 0 10px 0;
    display: flex;
    height:30px;
}
#writeReply input:nth-of-type(1){
    width:1200px;
    margin: 0 50px 0 30px;  
} 
#writeReply>li:nth-of-type(2)> input{
    width:300px;
    margin: 0 8px 0 30px;  
}
#writeReply button{
    padding: 0.2rem;
    border-radius: 30px;
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
#replyWrite{
    border:1px gray solid;
}
</style>