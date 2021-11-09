export const state = () => ( {   
  lists:[
    {
      no: 1,
      id: "이나",
      title: "게시판 제목 1",
      contents: "게시판 내용1",
      date: "2021/09/11",
      replyCount:0
    },
    {
      no: 2,
      id: "탁우",
      title: "게시판 제목 2",
      contents: "게시판 내용2",
      date: "2021/09/12",
      replyCount:0
    },
    {
      no: 3,
      id: "지혜",
      title: "게시판 제목 3",
      contents: "게시판 내용3",
      date: "2021/10/13",
      replyCount:0
    },
    {
      no: 4,
      id: "채우",
      title: "게시판 제목 4",
      contents: "게시판 내용4",
      date: "2021/08/14",
      replyCount:0
    },
    {
      no: 5,
      id: "예나",
      title: "게시판 제목 5",
      contents: "게시판 내용5",
      date: "2021/02/15",
      replyCount:0
    }
  ],
  replies:[
    {
      replyNo: 1,
      listNo:1,
      replyId: "user1",
      replyContent: "게시글1의 리플1",
      replyDate: "2021/09/11"
    },
    {
      replyNo: 2,
      listNo:1,
      replyId: "user1",
      replyContent: "게시글1의 리플2",
      replyDate: "2021/09/11"
    },
    {
      replyNo: 3,
      listNo:3,
      replyId: "user1",
      replyContent: "게시글3의 리플3",
      replyDate: "2021/09/11"
    },
    {
      replyNo: 4,
      listNo:2,
      replyId: "user1",
      replyContent: "게시글2의 리플2",
      replyDate: "2021/09/11"
    },
    {
      replyNo: 5,
      listNo:3,
      replyId: "user1",
      replyContent: "게시글3의 리플1",
      replyDate: "2021/09/11"
    },
    {
      replyNo: 6,
      listNo:3,
      replyId: "user1",
      replyContent: "게시글3의 리플2",
      replyDate: "2021/09/11"
    },
    
    {
      replyNo: 7,
      listNo:4,
      replyId: "user1",
      replyContent: "게시글4의 리플1",
      replyDate: "2021/09/11"
    },
    
    {
      replyNo: 8,
      listNo:4,
      replyId: "user1",
      replyContent: "게시글4의 리플2",
      replyDate: "2021/09/11"
    },
    
    {
      replyNo: 9,
      listNo:5,
      replyId: "user1",
      replyContent: "게시글5의 리플1",
      replyDate: "2021/09/11"
    },
    
    {
      replyNo: 10,
      listNo:5,
      replyId: "user1",
      replyContent: "게시글5의 리플2",
      replyDate: "2021/09/11"
    },
  ]
})

 
 
export const mutations = {

  getLists(state, param){
    state.no = param.no,
    state.id = param.id,
    state.title = param.title,
    state.contents = param.contents
    state.replyCount = param.replyCount
  },   
  getReplyLists(state, param){
    state.replyNo = param.no,
    state.listNo = param.listNo,
    state.replyId = param.id,
    state.replyContent = param.replyContent,
    state.replyDate = state.date
  }, 
  doWrite(state, param){  
    state.lists.push(param)  
    console.log(state.lists);
  },
  doUpdate(state, param){    
    state.lists[param.no-1] = param;
    // console.log("새로운 param", param); 
    // console.log("기존의 state.list",state.lists[param.no-1]);
  }, 
  doDelete(state, param){
    const index = state.lists.findIndex(l => l.no === param);
    state.lists.splice(index, 1);
    console.log(state.lists);
  },  
  doWriteComment(state, newReplyContent){  
    state.replies.push(newReplyContent)   
    console.log(state.replies);
  }  
}
   
export const actions = {

  doWrite({ commit, state }, param) { 

  function getToday(){
    const today = new Date();
    
    // yyyy-MM-dd;
    
    let year =  today.getFullYear();
    let month = today.getMonth() + 1;
    let day =  today.getDate();
 
    function getDigit( num ){ 
      if( 9 < parseInt(num) ){
        return num;
      } 
      return "0" + num;
    } 
    return [year, getDigit(month), getDigit(day)].join("/");
   } 
    param.no = state.lists.length + 1;

    const today = getToday();
    // console.log("today", today);
    param.date = today;

    commit('doWrite', param);  
  } , 

  doUpdate({ commit, state }, param){
    commit('doUpdate', param);  
  },
  doDelete({commit}, param){ 
    commit('doDelete', param.no);
  },
  doWriteComment({ commit, state }, param) {  
      commit('doWriteComment', param);  
    }
}

export const getters = {
  list (state){   
   let list = JSON.parse(JSON.stringify( state.lists)) ;
   const replies = state.replies;

   list.forEach(c  => {
     const reply = replies.filter( r => r.listNo === c.no);
     c.replyCount = reply.length;
   });

    return list;
  },
  replies(state){ 
    return state.replies;
  }
}

