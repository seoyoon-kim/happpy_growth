 <template>   
	<div> 
		<nav>    
			<NuxtLink to="/">Home</NuxtLink>
		</nav> 
		<div class="container">
			<h3>List</h3>
			<ul id="boardSubject">
				<li class="no">번호</li>
				<li>제목</li>
				<li>작성자</li>
				<li>작성일</li> 
				<li>댓글</li> 
			</ul>
			<div>
				<ul id="boardContent" v-for="list in getLists" :key="list.no">
					<li class="no">{{list.no}}</li>  
					<NuxtLink :to="'/board/'+list.no"><li>{{list.title}}</li></NuxtLink>
					<li>{{list.id}}</li>
					<li>{{list.date}}</li> 
					<li>{{list.replyCount}}</li> 
				</ul> 
			</div>  
		</div> 
		<div class="searchBox"> 
			<input type="submit" value="글쓰기" class="btn list-btn" @click="goWrite"/>
		</div>   
		<div class="list-pagination"> 
			<v-pagination :length= "numofpage" v-model="curpagenum" class="pagination"></v-pagination>    
		</div> 
	</div>  
</template>

<script>     
	import { mapGetters } from 'vuex'    
 
	export default {   
		name:'boardList',    
	 
		data(){
			return{
				curpagenum : 1,
      			datapage : 5
			}
		}, 
		
		props: { 
		},
		components:{
		},
		computed: {
			...mapGetters({
				lists: 'list' 
			}), 
			startOffset() {
				return ((this.curpagenum - 1) * this.datapage);
			},
			endOffset() {
				return (this.startOffset + this.datapage);
			},
			numofpage() {
				return Math.ceil(this.lists.length / this.datapage);
			},
			getLists() {
				let lists = this.lists.reverse();
				lists = this.lists.slice(this.startOffset, this.endOffset);
				return lists;
			}
		}, 
		methods: { 
			goWrite () {
				this.$router.push({
						path:'/board/write'
				})
			}  ,   
			
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
		margin-left:1100px;
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
		margin-left:40px; 
	}
	.no{
		width:40px;
		text-align: center; 
	}
	#boardContent a{/*제목*/
		width:1160px; 
		padding-left:40px;
	} 
	#boardContent>a>li:hover{ 
		color:royalblue;
	}
	#boardContent li:nth-of-type(2){/*작성자*/
		margin-right:110px; 
		width:110px;
		text-align: center;
	}
	#boardContent li:nth-of-type(3){/*작성일*/
		margin-right:48px;
	}

	/*검색*/
	.searchBox{
		margin-top:20px;
		display: flex; 
		padding-left:300px;
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
		background-color: lightgray;
		font-weight: bold; 
	}
	input{
	  border:1px black solid;
	  border-radius: 5px;
	  padding:2px; 
	}  

	/*페이지네이션*/
	.list-pagination{
		text-align: center; 
	}
	.theme--light, .v-pagination, .v-pagination__item--active{
		background-color: aqua;
	}
	.pagination-container {
		background: #fff;
		padding: 32px 16px;
	}
	.pagination-container.hidden {
		display: none;
	}  
	.theme--light.v-pagination .v-pagination__item--active {
		color: black !important;
	} 
</style>