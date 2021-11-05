export const state = () => ({
    list: []
  })
  
/*
프로젝트에서 공통으로 사용할 변수를 정의
프로젝트 내의 모든 곳에서 참조 및 사용이 가능
각 컴포넌트에서 동일한 값을 사용할 수 있습니다.


*/


  /* ** ACTIONS (비동기처리) */
export const actions = {}; 
/*
Actions : 
Mutations를 실행(->state 변경)
비동기 처리 ->콜백함수로 주로 작성 
: 순서에 상관없이 먼저 종료된 함수의 피드백을 받아 후속 처리
dispatch('setAccount', account ).then(() => { });
함수 형태로 작성
export const actions = {
	setAccount( { commit }, account ) {
		return new Promise( ( resolve, reject ) => {
			setTimeout( () => {
				commit( 'currentUser', account );
				resolve()
			}, 1000 )
		} )
	}
}
*/


  export const mutations = {/* 동기 처리 */
    add (state, text) {
      state.list.push({
        text,
        done: false,
        id: Date.now()
      })
    },
    remove (state, todo) {
      state.list = state.list.filter(item => item.id !== todo.id)
    },
    toggle (state, todo) {
      todo.done = !todo.done
    }
  }
/* 
mutations : 
state를 변경
동기처리 : 위의 함수가 실행되고 종료된 후 그 다음 아래의 함수가 실행됩니다.
mutations 내에 함수 형태로 작성
*/

/*
getters:
export const getters = {
	loginUserInfo( state ) {

		if ( $nuxt.$common.isEmpty( state.loginUser ) ) {
			$nuxt.$store.dispatch( "common/login/loginUser" );
		}

		return state.loginUser;
	}
}
*/