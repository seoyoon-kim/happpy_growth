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
				<ul class="readInfo">  
					<li readonly>{{content.no}}</li>
					<li readonly>{{content.title}}</li>
					<li readonly>{{content.id}}</li>
					<li readonly>{{content.date}}</li>  
					<li id="readContent" readonly> 
						<article>
							{{content.contents}}
						</article> 
					</li>   
					<li>댓글</li>  
				</ul>  
			</div>    
			<div id="replyBox">
				<ul class="reply-content" v-for="(reply, i) in replyList" :key="reply.replyNo">    
					<li v-if="reply.depth === 0" class="replyPadding" >
						┗ {{reply.replyContent}} 
						<button class="btn replyWrite small" @click="showRereply(i)">
							대댓글란열기
						</button>
					</li>  
					<li v-else :class="getPadding(reply)" >
                        ┗ {{reply.reReply}} 
                        <button class="btn replyWrite small" @click="showRereply(i)">
                            대댓글란열기
                        </button>
					<li>{{reply.replyId}}</li>
					<li>{{reply.replyDate}}</li>
					<ul id="writeRereply" :replyNo="reply.replyNo" v-if="showRereCommentList[i]">	
						<li class="reReply" :class="getPadding(reply)">
							┗ <input type="text" placeholder="대댓글을 입력하세요" v-model="reReply" >
						</li>
						<li class="id reReplyId"><input type="text" placeholder="ID를 입력하세요" v-model="reReplyId"></li>
						<li>
							<button class="btn reReplyWrite" @click="doWriteRereply(reply, replyId , reReply)">
								대댓글쓰기
							</button>
						</li>
						<li style="display:none">{{reply.replyGroup}}</li>
					</ul>
				</ul> 
				 
				<ul id="writeReply">
					<li><input type="text" placeholder="┗ 댓글을 입력하세요" v-model="replyContent" ></li>
					<li class="id"><input type="text" placeholder="ID를 입력하세요" v-model="replyId"></li>
					<li><button class="btn replyWrite" @click="doWriteComment(replyContent, replyId)">댓글쓰기</button></li>
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
import { mapState } from 'vuex' 
 
export default { 
	name: 'boardRead',
	data() {
		return {
			no:'',
			id:'',
			title:'',
			contents:'',
			date:'',

			replyNo: '',
			listNo:'',
			replyId: '', 
			depth : '',
			replyGroup : '',
			replyContent: '',
            reReply : '',
			replyDate:'',   

			showRereCommentList: [],     
		};
	},
//    asyncData(){ 
//         const list = $nuxt.$store.getters.list;  
//        const param = $nuxt.$route.params.no; 
//         const content = await list.filter( l => l.no.toString() === param)[0];  
//       return {param}
//           return { param, content } 
//    }, 
	props:{  
	},
	created() {
		this.getDate();    
	},  
	mounted(){ 
		this.replyList.forEach( (rl, index) => { 
			this.showRereCommentList.push(false);
		});  
	},
	computed:{
		...mapState({ 
			content: state => state.lists.filter(l => l.no.toString() === $nuxt.$route.params.no)[0],
			replyList: state => state.replies.filter( r => r.listNo.toString() === $nuxt.$route.params.no),  
		}),    
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
		showRereply(index){    
			this.showRereCommentList[index] = !this.showRereCommentList[index]; 
			this.$forceUpdate(); 
		},
		getPadding(reply){     
			let depth = reply.depth; 
			if(depth === 0){   
				return 'replyPadding'; 
			}else if(depth === 1){  
				return 'replyPaddingOne';  
			}else if(depth === 2){  
				return  'replyPaddingTwo';  
			}else{
				return 'replyPaddingECT'; 
			}  
		}  ,
		async doWriteComment(replyContent, replyId){ 
			if(replyId.trim() ===''){
				alert("아이디를 입력하세요"); 
			}
			else if(replyContent.trim() ===''){ 
				alert("내용을 입력하세요");
			} 
			else {  
				let replies =  await this.$store.state.replies;  
				const nextReplyNo = replies.length + 1;   
				let nextReplyGroup = 0;
				replies.forEach((reply) => 
				{   
					if( nextReplyGroup < reply.replyGroup){
						nextReplyGroup = reply.replyGroup +1; 
					}}  
				); 

				let newReplyContent ={
					replyNo : nextReplyNo,
					listNo : this.content.no,
					depth : 0,
					replyGroup : nextReplyGroup,
					replyId : this.replyId,
					replyContent : this.replyContent,
                    reReply :"",
					replyDate : this.date
				}      
			  
				this.$store.dispatch("doWriteComment", newReplyContent);  
				this.replyContent ="";
				this.replyId ="";   
			}
		},
		async doWriteRereply(reply, reReplyId , reReply){
				if(this.reReplyId.trim() ===''){
				alert("아이디를 입력하세요"); 
			}
			else if(this.reReply.trim() ===''){ 
				alert("내용을 입력하세요");
			} 
			else {  
				let replies =  await this.$store.state.replies;  
				const nextReplyNo = replies.length + 1;   
   
				let newReplyContent ={
					replyNo : nextReplyNo,
					listNo : this.content.no,
					depth : reply.depth + 1,
					replyId : this.reReplyId,
					replyContent : "",
                    reReply : this.reReply, 
					replyDate : this.date
				}      
			   console.log("newReplyContent",newReplyContent);
				this.$store.dispatch("doWriteComment", newReplyContent);  
				this.reReply ="";
				this.reReplyId ="";   
			}
		},
		orderedLists(replyList){
			this.replyGroup.sort(a,b =>{
				return a.replyGroup -b.replyGroupy
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
input:not(.searchBox input){ 
	border:none;
}  
#readContainer, h3{
  margin-left:10px;
}
#readContainer{
	border-top: 1px solid gray; 
	border-bottom: 1px solid gray;  
	width:1870px;
} 
.readInfo{
	width:1700px;
}
.readInfo li{ 
	padding: 7px 0 6px 20px;
	width:1770px;
	height:32px;
}  
.readInfo li:not(.readInfo li:nth-of-type(5)){
	border-bottom: 1px solid lightgray;
}
.readInfo li:nth-of-type(4){ 
	border-bottom: 1px solid darkgray;
}
.readInfo li:nth-of-type(6){ 
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
.readInfo>li:nth-of-type(5){
	padding: 0 10px; 
	margin-bottom: 30px;
}
.readInfo>li:nth-of-type(6){  
	position: relative;
	top:30px;
	height: 40px;
}
.cancel-btn{ 
	margin-left:30px;
}
/*Read의 댓글*/
#replyBox{
	position: relative;
	top:5px; 
}
.reply-content{
	padding: 5px 0 5px 15px;
	font-size: 14px;
}
.reply-content li{
	margin-bottom: 5px; 
	padding-top:5px; 
	display: inline-block;
}
.reply-content li:first-child{
	width:1300px;
}
.reply-content li:nth-of-type(2){
	width:350px;
	text-align: center;
}
.reply-content:not(.reply-content:last-child){
	border-bottom: lightgray 1px dotted;;
}
#writeRereply{
	border-top:1px lightgray dotted;
	display:inherit !important;
	font-size:12px;
}
#writeReply, #writeRereply{ 
	display: flex;
	height:40px;
	padding-top:2px;
} 
#writeReply:not(#writeReply:last-child), #writeRereply:not(#writeRereply:last-child){ 
	border-bottom: 1px dotted lightgray;
}
#writeReply input:nth-of-type(1){
	width:1340px;
	margin: 5px 30px 0 30px;  
} 
 
#writeReply>li:nth-of-type(2)> input {
	width:170px;
	padding-left:10px;
	margin: 5px 75px 0 30px;     
}
.reReplyId{
	width:170px; 
	position: relative;
	top:15px;
}
#writeRereply>li:nth-of-type(2)> input{
	position: relative;
	top:-13px;	
}
#writeReply button, #writeRereply button{
	margin-top:2px;
	padding: 0.2rem;
	border-radius: 30px;
	font-size:15px;
}
#writeRereply button{
	font-size:13px;
}
.reReply{ 
	width:1400px; 
	margin-left:20px;
}
.reReply input{  
	width:1000px;
	margin: 5px 30px 0 10px;  
	
}
.btn{
  width:80px;
  border:1px solid lightgray !important;
}
.btn:hover{
	background-color: lightgray; 
}
.edit-btn{
	margin-right: 30px;;
}
input[type=submit], .delete-btn, .edit-btn{
  width:100px !important;
  padding:10px 0 10px 0;
  border-radius: 5px;
} 
.small{
	font-size:10px;
	border-radius: 10px;
	width:70px;
	height: 20px;
	margin-left:20px;
}
.replyWrite{ 
	position:relative;
	left:-3px;
}
.reReplyWrite{
	width:100px; 
	position:relative; 
	left:-30px;
}
.replyPadding{
	padding-left:10px;
}
.replyPaddingOne{
	padding-left:40px;
}
.replyPaddingTwo{
	padding-left:60px;
}
.replyPaddingECT{
	padding-left:90px; 
}
input:focus {
	outline:none;
}    
</style>