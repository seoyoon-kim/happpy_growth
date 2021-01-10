package Library;
 
public class LibLogin {
	public static String id;
	public static String pwd;
	
	public LibLogin() {} 
		public static boolean loginCheck() {
			if(id =="" || pwd =="") {
			System.out.println( "아이디, 비밀번호를 입력 후 로그인하세요");
			return false;
			} 
		    else if(id.equals("master")&&pwd.equals("1234")) {
				System.out.println("로그인 성공하였습니다");
				return true;
			}
			else  return false;
			}
		public static void main(String[] args) {
			loginCheck ();
		}
 }