export default {
    lists: [
      {
        no: 1,
        id: "이나",
        title: "게시판 제목 1",
        contents: "게시판 내용1",
        date: "2021-09-11"
      },
      {
        no: 2,
        id: "탁우",
        title: "게시판 제목 2",
        contents: "게시판 내용2",
        date: "2021-09-12"
      },
      {
        no: 3,
        id: "지혜",
        title: "게시판 제목 3",
        contents: "게시판 내용3",
        date: "2021-10-13"
      },
      {
        no: 4,
        id: "채우",
        title: "게시판 제목 4",
        contents: "게시판 내용4",
        date: "2021-08-14"
      },
      {
        no: 5,
        id: "예나",
        title: "게시판 제목 5",
        contents: "게시판 내용5",
        date: "2021-02-15"
      }
    ] ,
    Comment: [
      {
        comment_id: 1,
        content_id: 2,
        no: 1,
        contents: "안녕하세요1",
        date: "2021-09-15",
        updated_at: null
      },
      {
        comment_id: 2,
        content_id: 1,
        no: 1,
        contents: "안녕하세요2",
        date: "2021-09-12",
        updated_at: null
      },
      {
        comment_id: 3,
        content_id: 3,
        no: 3,
        contents: "안녕하세요3",
        date: "2021-09-15",
        updated_at: null
      },
      {
        comment_id: 4,
        content_id: 3,
        no: 1,
        contents: "안녕하세요4",
        date: "2021-09-19",
        updated_at: null
      },
      {
        comment_id: 5,
        content_id: 3,
        no: 2,
        contents: "안녕하세요6",
        date: "2021-09-20",
        updated_at: null
      }
    ],
    SubComment: [
      {
        subcomment_id: 1,
        comment_id: 3,
        no: 3,
        contents: "서브 댓글 1",
        date: "2021-09-15",
        updated_at: null
      },
      {
        subcomment_id: 2,
        comment_id: 3,
        no: 1,
        contents: "서브 댓글 2",
        date: "2021-09-16",
        updated_at: null
      }
    ]
  };
  