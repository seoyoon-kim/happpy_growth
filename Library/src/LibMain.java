import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import Library.LibDataSet;
import Library.LibLogin;
import Library.LibVO;
import Library.MemDataSet;
import Library.MemVO;


public class LibMain {
	Scanner sc = new Scanner(System.in);
	//로그인, 로그아웃 
	//회원등록 등 회원관리 기능 //도서관리 시스템 
	//
	//소스, 실행파일, ERD 다이어그램, 실행결과 캡쳐 피피티 
	//1월 15일 2:30 발표
	 
	
	//콘솔에서 문자 입력받아 리턴하는 메서드
public String conInput(String msg) {
	System.out.println(msg+"=");
	return sc.nextLine();
			} 
			
	//아이디와 비밀번호 입력받아 로그인 구현		
public void start() {
	 LibLogin.id = conInput("아이디 입력");
	 LibLogin.pwd = conInput("비밀번호 입력");
	 if(LibLogin.loginCheck()) {
			 
		 LibDataSet.setLibList();
		 MemDataSet.setMemList();
			 
		 //항목 선택
		 do {
		 String menu1 =conInput("알파벳을 입력하세요[A.회원관리, B.도서관리, C.종료]");		 
				if(menu1.equals("A")) {
					String menu2 =conInput("숫자를 입력하세요[1.회원목록 보기 2.회원 등록, 3.회원 검색, 4.회원 수정, 5.회원 삭제, 6.종료]");		
					ResultNotice();	
					if(menu2.equals("1")) {
						 	MemListPrint();	
						 	System.out.println( "success 회원목록 보기가 완료 되었습니다.");
						}
						else if(menu2.equals("2")) {
							MemEnroll();	
							System.out.println( "회원 "+NewMemName +"success님의 등록이 완료 되었습니다.");
						}
						else if(menu2.equals("3")) {
							MemSearch();
							System.out.println( "검색이 완료 되었습니다.");
						}
						else if(menu2.equals("4")) {
							MemEdit();
							System.out.println( "회원 "+ EditName +"success님의 정보 수정이 완료 되었습니다.");
						}
						else if(menu2.equals("5")) {
							MemDel();
							System.out.println( "회원 "+ DelName  +"success님의 회원 정보 삭제가 완료 되었습니다.");
						} else break; 
							}//end 회원관리
							
				 
					
				//도서 관리하기
				else if(menu1.equals("B")) {
					String menu2 =conInput("숫자를 입력하세요[1.도서목록 보기 2.도서 등록, 3.도서 검색, 4.도서 정보 수정, 5.도서 삭제, 6.대여상태 보기 7.종료]");		
					ResultNotice();	
					if(menu2.equals("1")) {
						BookListPrint();		
						}
					else if(menu2.equals("2")) {
						BookEnroll();	
						System.out.println( "도서 "+ NewBookName +"의 등록이 완료 되었습니다.");
					}
					else if(menu2.equals("3")) {
						BookSearch();
						System.out.println( "검색이 완료 되었습니다.");
					}
					else if(menu2.equals("4")) {
						BookEdit();
						System.out.println( "도서 "+ EditISBN  +"의 정보 수정이 완료 되었습니다.");
					}
					else if(menu2.equals("5")) {
						BookDel();
						System.out.println( "도서 "+    +"의 정보 삭제가 완료 되었습니다.");
					}else if(menu2.equals("6")) {
						RentCheck();
						System.out.println( "도서 대여 정보 보기가 완료 되었습니다.");
					} 
					else break; 
						}  //도서관리 종료
				else break;  //C 종료  
		 
					
		}while(true);
		 
	}else {
		System.out.println("로그인에 실패하였습니다");
	}
}//end start	


 




public void ResultNotice() {
	System.out.println("----------------다음과 같이 확인하세요---------------");
}

public void MemListPrint() {
	Set<Integer> MemkeyList = MemDataSet.MemList.keySet();  
	Iterator<Integer> ii= MemkeyList.iterator();
	while(ii.hasNext()) { 
		MemVO mvo = MemDataSet.MemList.get(ii.next()); 
		System.out.printf("%d\t %s\t %s\t %s\t %s\n", 
				mvo.getMemNum(),mvo.getMemName(),mvo.getID(),mvo.getPWD(),mvo.getAddr());
		
	}	
 } 
	
public void BookListPrint() {
	Set<Integer> LibkeyList = LibDataSet.LibList.keySet();  
	Iterator<Integer> iii= LibkeyList.iterator();
	while(iii.hasNext()) { 
		LibVO lvo = LibDataSet.LibList.get(iii.next()); 
		System.out.printf("%d\t %s\t %s\t %s\t %s\n", 
				lvo.getISBN(),lvo.getBookName(),lvo.getAuth(),lvo.getAuth(),lvo.getRent());
	 }
}	 
	 
	public void MemEnroll() {
		int NewNum = Integer.parseInt(conInput("회원번호"));
		 
		do {   //해쉬맵 객체.containsKey(찾고자하는 데이터) 왜 안되지?????????????????????
			if(MemKeyList.containsKey(NewNum)) {
				System.out.println("이미 존재하는 회원 번호입니다. 다른 번호를 입력하세요");
			}else {
				int MemNum = NewNum;	 
				break; 
			}
		}while(true);
	  	 
 	String NewMemName = conInput("이름을 입력하세요");
	String NewID = conInput("아이디를 입력하세요");
	String NewPWD = conInput("비밀번호를 입력하세요");
	String NewTel = conInput("전화번호를 입력하세요");
	String NewAddr = conInput("주소를 입력하세요"); 
	
	MemDataSet.MemList.put(NewNum, new MemVO(NewNum, NewMemName, NewID, NewPWD, NewTel, NewAddr));	
 
	
	}	
	 
	
	
	
	public void BookEnroll(){
		int ISBN2 = Integer.parseInt(conInput("ISBN번호를 입력하세요"));
	 
	do {   //해쉬맵 객체.containsKey(찾고자하는 데이터) 왜 안되지?????????????????????
		if(LibList.containsKey(ISBN2)) {
			System.out.println("이미 존재하는 ISBN 번호입니다. 다른 번호를 입력하세요");
		}else {
		 	break; 
		}
	}while(true);
 	 
String NewBookName = conInput("도서명을 입력하세요");
String NewAuth = conInput("저자");
String NewPubl = conInput("출판사"); 
String NewRent = conInput("대여 여부를 입력하세요"); 

LibDataSet.LibList.put(ISBN2, new LibVO(ISBN2, NewBookName, NewAuth, NewPubl, NewRent));	
}
	
	
	
	
	
	public void MemSearch() {
		int Num = Integer.parseInt(conInput("검색하고자 하는 회원 항목을 입력하세요 [1.회원번호, 2.이름, 3.아이디, 4.전화번호]"));
		if(Num==1) {
		 	do {
				int NumNum2 = Integer.parseInt(conInput("회원 번호를 입력하세요")); 
				if(!MemKeyList.containsKey(NumNum2)) {
					System.out.println("존재하지 않는 회원 번호입니다. 다른 번호를 입력하세요");
				}else { 
					return MemList.gettValue(NumMem2);
					break;
				} 
			}while(true);
		}
		else if(Num==2) { //이름으로 검색하는 법
	
		}else if(Num==3) { //아이디로 검색하는 법
		
		}else if(Num==4) { //전화번호로 검색하는 법
			
		}
				 
	
	
	public void	BookSearch(){
		int Num2 = Integer.parseInt(conInput("검색하고자 하는 도서 정보 항목을 입력하세요 [1.ISBN번호, 2.도서명, 3.저자, 4.출판사, 5.대여 여부]"));
		if(Num2==1) {
		 	do {
				int ISBN2 = Integer.parseInt(conInput("도서 번호를 입력하세요")); 
				if(!LibKeyList.containsKey(ISBN2)) {
					System.out.println("존재하지 않는 도서 번호입니다. 다른 번호를 입력하세요");
				}else { 
					return LibList.gettValue(ISBN2);
					break;
				} 
			}while(true);
		}else if(Num==2) { //도서명으로 검색하는 법
		
		}else if(Num==3) { //저자로 검색하는 법
	
		}else if(Num==4) { //출판사로 검색하는 법
		
		}else if(Num==5) { //대여 여부로 검색하는 법
		
		}
	}
	
	
	
	
	
				
		 
	public void MemEdit() {
		do {
			String EditName = conInput("수정할 회원 이름을 입력하세요");	
	        MemVO mvo = MemDataSet.MemList.get(EditName);
		
		if(!MemList.containsKey(EditName)) {
			System.out.println("존재하지 않는 회원 이름입니다");
		} else {
			String subMenu = conInput("수정할 항목 선택 [1.비밀번호, 2.연락처, 3.주소]");
			if(subMenu.equals("1")) {
				String EditPWD = conInput("새로운 비밀번호를 입력하세요");
				mvo.setPWD(EditPWD);  
				System.out.println("비밀번호가 변경되었습니다");
				break;
			} else if(subMenu.equals("2")) {
				String EditTel = conInput("새로운 연락처를 입력하세요");
				mvo.setTel(EditTel);  
				System.out.println("연락처가 변경되었습니다");
				break;
			} else if(subMenu.equals("3")) {
				String EditAddr = conInput("새로운 주소를 입력하세요");
				mvo.setAddr(EditAddr);  
				System.out.println("주소가 변경되었습니다");
				break;
			} 		
		} 	 
	}while(true);
	}
	
	
	
	
	public void BookEdit(){
		do {
			String EditISBN = conInput("수정할 도서의 ISBN 번호를 입력하세요");	
	        LibVO lvo = LibDataSet.LibList.get(EditISBN);
		
		if(!LibList.containsKey(EditISBN)) {
			System.out.println("존재하지 않는 도서 ISBN 번호입니다");
		} else {
			String subMenu = conInput("수정할 항목 선택 [1.도서명, 2.저자, 3.출판사, 4. 대여 여부]");
			if(subMenu.equals("1")) {
				String EditBName = conInput("새로운 도서명을 입력하세요");
				lvo.setBookName(EditBName);  
				System.out.println("도서명이 변경되었습니다");
				break;
			} else if(subMenu.equals("2")) {
				String EditAuth = conInput("새로운 저자를 입력하세요");
				lvo.setAuth(EditAuth);  
				System.out.println("연락처가 변경되었습니다");
				break;
			} else if(subMenu.equals("3")) {
				String EditPbl = conInput("새로운 출판사를 입력하세요");
				lvo.setPubl(EditPbl);  
				System.out.println("출판사가 변경되었습니다");
				break;
			} else if(subMenu.equals("4")) {
				String EditRent = conInput("변경할 대여 정보를 입력하세요");
				lvo.setRent(EditRent);  
				System.out.println("대여 정보가 변경되었습니다");
				break;
			} 		
		} 	 
	}while(true);
	}
	
	
	
	
	public void RentCheck() {
	 //대여 상태로 검색해 보여주기 구현
	}
	
	
	
	
	
	public void MemDel() {
		String DelName = conInput("삭제할 회원 명을 입력하세요");
		if( ) { 		
		 //회원명 중복시
		}
		else if(!MemList.containsKey(DelName)) {
		System.out.println("존재하지 않는 회원 이름입니다");
		}
		else if(MemList.gettValue(DelName)) {
			MemDataset.MemList.remove(DelName);	
		} //정상 작동 삭제
		}
	 

 
	public void BookDel() {
		String DelBName = conInput("삭제할 도서의 ISBN 번호를 입력하세요");
		if(LibList.getValue(DelBName ) { 		
			LibDataset.LibList.remove(DelBName);	 
		}
		else if(!LibList.containsKey(DelBName)) {
		System.out.println("존재하지 않는 회원 이름입니다");
		} 	
		}   
    		
    	
	
	
	


public static void main(String[] args) { 
 new LibMain().start();
 System.out.println("프로그램이 종료되었습니다");
}

}//class end
  
 
