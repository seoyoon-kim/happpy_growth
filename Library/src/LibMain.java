 package Library;
 
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import Library.LibDataSet;
import Library.LibLogin;
import Library.LibVO;
import Library.MemDataSet;
import Library.MemVO;


public class LibMain {
	Scanner sc = new Scanner(System.in);
	
	//로그인, 로그아웃 
	//회원등록 등 회원관리 기능 //도서관리 시스템  
	//소스, 실행파일,  다이어그램, 실행결과 캡쳐 피피티 
	//1월 15일 2:30 발표 
	
	Set<Integer> memkeyList, libkeyList; 
	String newMemName, editName, delName, newBookName, editISBN, delNum, delBNum,sch, quit, cont, memFind, again, gotName, gotID, gotTel, gotAddr;

	int gotNum;
	int newMemNum, subNum;
	MemVO mvo;
	
 
	//아이디와 비밀번호 입력받아 로그인 구현		
	public void start() {
exit: while(true) {	System.out.println("==============도서 관리 프로그램 실행=================");
	LibLogin.id = conInput("아이디를 입력하세요");
	logOut: while(true) { 
	 LibLogin.pwd = conInput("비밀번호를 입력하세요");
	 if(LibLogin.loginCheck()) {
			 
		 LibDataSet.setLibList();
		 MemDataSet.setMemList();
			 
		 //항목 선택
		 all: while(true) {
		 String menu1 =conInput("원하는 항목의 알파벳을 입력하세요[A.회원관리, B.도서관리, C.로그아웃, D.종료]");	
		 
		  
		 if(menu1.equals("A")||menu1.equals("a")) {
				while(true) {
			 	String menu2 =conInput("숫자를 입력하세요[1.회원목록 보기 2.회원 등록, 3.회원 검색, 4.회원 수정, 5.회원 삭제, 6.회원관리 종료, 7. 프로그램 종료]");		
				 
					if(menu2.equals("1")) {
						 	MemListPrint();	 
						 		while(true) {String cont = conInput("계속 하시겠습니까? Y/N");
						 	 if(cont.equals("Y")||cont.equals("y"))  
							    	break;
						 	 else if(cont.equals("N")||cont.equals("n")) {						 	    	
						 		 System.out.println("프로그램을 종료합니다");   
						 		 break exit;}
						 	 else if ( !cont.equals("Y")|| !cont.equals("y")||!cont.equals("N")|| !cont.equals("n")){	
						 		System.out.println("잘못 입력하셨습니다. 다시 입력하세요");						   
						    }
						 }
						}else if(menu2.equals("2")) {
							MemEnroll();	 while(true) {String cont = conInput("계속 하시겠습니까? Y/N");
						 	 if(cont.equals("Y")||cont.equals("y"))  
							    	break;
						 	 else if(cont.equals("N")||cont.equals("n")) {		    
						 		 break exit;}
						 	 else if ( !cont.equals("Y")|| !cont.equals("y")||!cont.equals("N")|| !cont.equals("n")){	
						 		System.out.println("잘못 입력하셨습니다. 다시 입력하세요");						   
						    }
						 }
						}else if(menu2.equals("3")) {
							MemSearch();     break;
						}else if(menu2.equals("4")) {
							MemEdit(); 
					 	}else if(menu2.equals("5")) {
							MemDel();        break;
						}else if(menu2.equals("6"))	break; 
						 else if(menu2.equals("7")) break all; 
						
						else {	System.out.println("잘못 입력하셨습니다. 다시 입력하세요");
						} }
							}//end if
		
		 
					
				//도서 관리하기
				else if(menu1.equals("B")||menu1.equals("b")) {
					String menu2 =conInput("숫자를 입력하세요[1.도서목록 보기 2.도서 등록, 3.도서 검색, 4.도서 정보 수정, 5.도서 삭제, 6.종료]");		
					ResultNotice();	
					if(menu2.equals("1")) {
						BookListPrint();	
						while(true) {String cont = conInput("계속 하시겠습니까? Y/N");
					 	 if(cont.equals("Y")||cont.equals("y"))  
						    	break;
					 	 else if(cont.equals("N")||cont.equals("n")) {						 	    	
					 		 System.out.println("프로그램을 종료합니다");   
					 		 break exit;}
					 	 else  	System.out.println("잘못 입력하셨습니다. 다시 입력하세요");						   
					 	 }
						}
					else if(menu2.equals("2")) {
						BookEnroll();	
					}
					else if(menu2.equals("3")) {
						BookSearch(); 
					}
					else if(menu2.equals("4")) {
						BookEdit();
					}
					else if(menu2.equals("5")) {
						BookDel();
					} else break; 
				 }  //도서관리 종료
				
				else if(menu1.equals("C")||menu1.equals("c")) {
					System.out.println("로그아웃 되었습니다.\n");
					break logOut;
				}//C 로그아웃
				else break exit;  //D 종료  
		 
					
		} 
		 
	}else {
		System.out.println("로그인에 실패하였습니다");
	}
}	////end logOut
	}//end start
  
	}// start exit
	 
	
	//콘솔에서 문자 입력받아 리턴하는 메서드
	public String conInput(String msg) {
		System.out.println(msg+"=");
		sch=sc.nextLine();
		return sch;
				}  
	

	
	
	public void MemListPrint() {
		 memkeyList = MemDataSet.memList.keySet();  
		Iterator<Integer> ii= memkeyList.iterator();
		while(ii.hasNext()) {                                
			MemVO mvo = MemDataSet.memList.get(ii.next());    
			System.out.printf("%d\t %s\t %s\t %s\t %s\n", 
					mvo.getMemNum(),mvo.getMemName(),mvo.getID(),mvo.getPWD(),mvo.getAddr());
			
		}	
	 	System.out.println( "회원목록 보기가 완료 되었습니다.");
	 } 
		
	public void BookListPrint() {
		libkeyList = LibDataSet.libList.keySet();  
		Iterator<Integer> iii= libkeyList.iterator();
		while(iii.hasNext()) { 
			LibVO lvo = LibDataSet.libList.get(iii.next()); 
			System.out.printf("%d\t %s\t %s\t %s\t %s\n", 
					lvo.getISBN(),lvo.getBookName(),lvo.getAuth(),lvo.getPubl(),lvo.getRent());
		 }
		System.out.println( "도서목록 보기가 완료 되었습니다.----------------------------");
	}	 

	
	
	

	 
	 //회원 등록 메서드
	public void MemEnroll() {  
		Set<Integer> keySet = MemDataSet.memList.keySet();
		Iterator<Integer> i = keySet.iterator();
   while(i.hasNext()) {
				 int key = i.next();
			 }
		int newMemNum = (Collections.max(keySet))+1;
	    System.out.println( "회원번호 "+newMemNum +"번의 회원 등록을 시작합니다");
	   	  
	 	String newMemName = conInput("이름을 입력하세요");  
		String newID = conInput("아이디를 입력하세요");
		String newPWD = conInput("비밀번호를 입력하세요");
		String newTel = conInput("전화번호를 입력하세요");
		String newAddr = conInput("주소를 입력하세요"); 
		
		MemDataSet.memList.put(newMemNum, 
				new MemVO(newMemNum, newMemName, newID, newPWD, newTel, newAddr));	

		System.out.println( "회원 "+ newMemName +"의 등록이 완료 되었습니다.");
		System.out.println("=========================================================================");
		System.out.println("회원번호\t"+"이름\t"+"아이디\t"+"비밀번호\t"+"전화번호\t"+"주소");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println(newMemNum+"\t"+newMemName +"\t"+newID +"\t"+newPWD +"\t"+newTel +"\t"+newAddr);
		 
		}	
				  
	 	
		/////도서 등록 메서드
		public void BookEnroll(){
			
		
			String newBookNum = conInput("ISBN 번호를 입력하세요");   
	out: while(true) {
			for(int i=0; i>newBookNum.length(); i++) {
				char c=(newBookNum.charAt(i));
				int asc =  c;
				if( asc <48 ||  asc>57  )  
				System.out.print("잘못 입력하셨습니다.\n올바른");
				break out;
			} //추가 수정하기
		 
		 	
			String newBookName = conInput("도서명을 입력하세요");  
			String newAuth = conInput("저자 명을 입력하세요");
			String newPubl = conInput("출판사 명을 입력하세요");
			String newRent = conInput("대여 여부를 입력하세요[대여가능/예약가능/대여불가/반납완료]");
	 	 		
			 LibDataSet.libList.put(Integer.parseInt(newBookNum), 
						new LibVO(Integer.parseInt(newBookNum), newBookName, newAuth, newPubl, newRent));	

			System.out.println( "도서 "+ newBookName +"의 등록이 완료 되었습니다.");
			System.out.println("=========================================================================");
			System.out.println("ISBN번호\t"+"도서명\t"+"저자명\t"+"출판사\t"+"대여 여부\t");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(newBookNum+"\t"+newBookName +"\t"+newAuth +"\t"+newPubl +"\t"+newRent);
		
			again=conInput("계속 도서 등록을 하시겠습니까?(Y/N)");
			if(again.equals("N")||again.equals("n")) break;
		}
		}
		
		//회원 정보 검색 메서드
	  public void memSch() {
					
		exit: while(true) {	
			memFind = conInput("입력하세요");
			if (memFind.equals(String.valueOf("0"))) break exit;
			Set<Integer> keySet = MemDataSet.memList.keySet();
			Iterator i = keySet.iterator();
			
			out: while(i.hasNext()) {
				Integer key =   (Integer) i.next();
				MemVO mvo = MemDataSet.memList.get(key);
					
				if(memFind.equals(String.valueOf(mvo.getMemNum())) || memFind.equals(mvo.getMemName()) ||
							memFind.equals(mvo.getID())            || memFind.equals(mvo.getTel())     || 
							memFind.equals(mvo.getAddr()) ) {
							System.out.println("======================================================================= \n"
					 		+ "회원번호  \t"+"이름\t"+"아이디\t"+"\t전화번호\t"+"\t주소\n"
							+("----------------------------------------------------------------------- \n")	 );
							System.out.printf("%d\t %s\t %s\t %s\t%s\n", 
					 		mvo.getMemNum(), mvo.getMemName(), mvo.getID(), mvo.getTel(), mvo.getAddr()+"\n" );
							System.out.println( "검색이 완료 되었습니다.\n-----------------------------------------------------------------------"); 
							
							 
							again = conInput("다른 회원 정보를 검색하시겠습니까?(Y/N)");
							if((again.equals("N")||again.equals("n"))) break exit; 
							else { System.out.print("검색할 회원 정보를 다시 한번 "); break;
							}
				}   while(!(i.hasNext())) {	
					if(!memFind.equals(String.valueOf(mvo.getMemNum())) || !memFind.equals(mvo.getMemName()) ||
							!memFind.equals(mvo.getID())            || !memFind.equals(mvo.getTel())     || 
							!memFind.equals(mvo.getAddr()) ) {
					 System.out.println("해당 정보가 없습니다.");   
						System.out.print("종료는 0을 입력, 재시도하려면 회원 정보를 다시 "); break out;
							  }   
					
				 }
			}      
			}
		} 
				
				

				 
				
				 
			   	 
				
				public void MemSearch() {
			while(true) {
				
				String num = conInput("검색하고자 하는 회원 항목을 입력하세요 [1.회원번호, 2.이름, 3.아이디, 4.전화번호, 5. 종료]");
				if(num.equals(String.valueOf(1))) {
				 	System.out.print("회원 번호를 ");
				 	memSch();  
				}
				else if(num.equals(String.valueOf(2))) { 
					System.out.print("회원 이름을 ");
					memSch();  
				}
				
				else if(num.equals(String.valueOf(3))) {
					System.out.print("회원 아이디를 ");
					memSch();  
				
				}else if(num.equals(String.valueOf(4))) { 
					System.out.print("회원 전화번호를 ");
					memSch();  
				} else if(num.equals(String.valueOf(5))) { 
						System.out.println("회원 검색을 종료합니다 "); break;
						} 
				 else {
					System.out.println("잘못 입력하셨습니다."); break;
				}	 
				}
				} 
				
			//도서 정보 검색 메서드
			public void bookSearch() {
				exit: while(true) {	
					memFind = conInput("입력하세요");
					if (memFind.equals(String.valueOf("0"))) break exit;
					Set<Integer> keySet = LibDataSet.libList.keySet();
					Iterator i = keySet.iterator();
					
					out: while(i.hasNext()) {
						Integer key =   (Integer) i.next();
						LibVO lvo = LibDataSet.libList.get(key);
							
						if(memFind.equals(String.valueOf(lvo.getISBN())) || memFind.equals(lvo.getBookName()) ||
									memFind.equals(lvo.getAuth())            || memFind.equals(lvo.getPubl())     || 
									memFind.equals(lvo.getRent()) ) {
									System.out.println("======================================================================= \n"
							 		+ "회원번호  \t"+"이름\t"+"아이디\t"+"\t전화번호\t"+"\t주소\n"
									+("----------------------------------------------------------------------- \n")	 );
									System.out.printf("%d\t %s\t %s\t %s\t%s\n", 
											lvo.getISBN(), lvo.getBookName(), lvo.getAuth(), lvo.getPubl(), lvo.getRent()+"\n" );
									System.out.println( "검색이 완료 되었습니다.\n-----------------------------------------------------------------------"); 
									
						 			again = conInput("다른 도서 정보를 검색하시겠습니까?(Y/N)");
									if((again.equals("N")||again.equals("n"))) break exit; 
									else { System.out.print("검색할 도서 정보를 다시 한번 "); break;
									}
						}   while(!(i.hasNext())) {	
							if(!memFind.equals(String.valueOf(lvo.getISBN())) || !memFind.equals(lvo.getBookName()) ||
									!memFind.equals(lvo.getAuth())            || !memFind.equals(lvo.getPubl())     || 
									!memFind.equals(lvo.getRent()) ) {
							 System.out.println("해당 정보가 없습니다.");   
								System.out.print("종료는 0을 입력, 재시도하려면 도서 정보를 다시 "); break out;
									  }   
							
						 }
					}      
					}
						 } 
			  
			  
			public void	BookSearch(){
				int num2 = Integer.parseInt(conInput("검색하고자 하는 도서 정보 항목을 입력하세요 [1.ISBN번호, 2.도서명, 3.저자, 4.출판사, 5.대여 여부]"));
				if(num2==1) {
					System.out.print("도서 번호를");
					bookSearch(); 
					System.out.println( "검색이 완료 되었습니다.");
				 }else if(num2==2) { 
					System.out.print("도서 이름을");
					bookSearch(); 
					System.out.println( "검색이 완료 되었습니다.");
				}else if(num2==3) { 
					System.out.print("저자 이름을");
					bookSearch(); 
					System.out.println( "검색이 완료 되었습니다.");
				}else if(num2==4) { 
					System.out.print("출판사를");
					bookSearch(); 
					System.out.println( "검색이 완료 되었습니다.");
				}else if(num2==5) { 
					System.out.print("도서 상황[대여가능/예약가능/반납완료/대여불가]을");
					bookSearch();  
					System.out.println( "검색이 완료 되었습니다.");
				}
		 	}
			
				

			 
			 
 public void MemEdit() {
	 		System.out.print("수정하려는 회원의 현재 회원번호, 이름, 아이디, 주소 중 하나를 ");
 exit: while(true) {	
		  	memFind = conInput("입력하세요");
	   if (memFind.equals(String.valueOf("0"))) break exit;
							
		 	Set<Integer> keySet = MemDataSet.memList.keySet();
		 	Iterator i = keySet.iterator();
							
 out: while(i.hasNext()) {
		 	Integer key =   (Integer) i.next();
		 	MemVO mvo = MemDataSet.memList.get(key);
									
	 	if(memFind.equals(String.valueOf(mvo.getMemNum())) || memFind.equals(mvo.getMemName()) ||
			 	   	memFind.equals(mvo.getID())            || memFind.equals(mvo.getTel())     || 
				 	memFind.equals(mvo.getAddr()) ) {
			 	
	 		String subMenu = conInput("수정할 항목을 선택하세요 [1.이름, 2.비밀번호, 3.연락처, 4.주소]");
	 	    	if(subMenu.equals("1")) { 
		 		   String newMemName = conInput("새로운 이름을 입력하세요");
			 	   mvo.setMemName(newMemName);  
			 	   System.out.println("이름이 변경되었습니다."); 
	 		   } else if(subMenu.equals("2")) {
			     	String newPWD = conInput("새로운 비밀번호를 입력하세요");
			    	mvo.setPWD(newPWD);  
			 		System.out.println("비밀번호가 변경되었습니다."); 
			   } else if(subMenu.equals("3")) {
			 		String newTel = conInput("새로운 연락처를 입력하세요");
			    	mvo.setTel(newTel);  
		 	        System.out.println("연락처가 변경되었습니다."); 
	           } else if(subMenu.equals("4")) {
		        	String newAddr = conInput("새로운 주소를 입력하세요");
			 		mvo.setAddr(newAddr);  
		 	    	System.out.println("주소가 변경되었습니다."); 
	 	       }   else {
		 	    	System.out.println("잘못 입력하셨습니다. 회원 검색으로 돌아갑니다.");  
										 
		 	}
	   		System.out.println("======================================================================= \n"
				   	+ "회원번호  \t"+"이름\t"+"아이디\t"+"\t전화번호\t"+"\t주소\n"
				 	+("----------------------------------------------------------------------- \n")	 );
				     System.out.printf("%d\t %s\t %s\t %s\t%s\n", 
				 	mvo.getMemNum(), mvo.getMemName(), mvo.getID(), mvo.getTel(), mvo.getAddr()+"\n" );
				     System.out.print("회원 수정을 종료하려면 0을 입력,\n계속하시려면 회원정보를 다시 ");
				     break;
				     	} 								
			 							 
  while(!(i.hasNext())) {	
		 if(!memFind.equals(String.valueOf(mvo.getMemNum())) || !memFind.equals(mvo.getMemName()) ||
			         !memFind.equals(mvo.getID())            || !memFind.equals(mvo.getTel())     || 
			 		 !memFind.equals(mvo.getAddr()) ) {
				      System.out.println("해당 정보가 없습니다.");   
			         System.out.print("종료는 0을 입력, 재시도하려면 회원 정보를 다시 "); break out;
			 		  }   
			 	 	 }
			 	}      
	 	}   	
 }
						 
					 	  
		  
		 
			
			public void BookEdit(){
			all:	while(true) {
				System.out.print("수정하려는 도서의 현재 ISBN번호, 도서명, 저자명, 출판사, 대여 상황 중 하나를 ");
				 exit: while(true) {	
						  	memFind = conInput("입력하세요");
					again :while(true) {
						  	if (memFind.equals(String.valueOf("0"))) break all;
											
						 	Set<Integer> keySet = LibDataSet.libList.keySet();
						 	Iterator i = keySet.iterator();
											
				 while(i.hasNext()) {
						 	Integer key =   (Integer) i.next();
						 	LibVO lvo = LibDataSet.libList.get(key);
							 
						 	
					 	if(memFind.equals(String.valueOf(lvo.getISBN())) || memFind.equals(lvo.getBookName()) ||
							 	   	memFind.equals(lvo.getAuth())        || memFind.equals(lvo.getPubl())     || 
								 	memFind.equals(lvo.getRent()) ) {
							 	
					 		String subMenu = conInput("수정할 항목을 선택하세요 [1.도서명, 2.저자명, 3.출판사명, 4.대여상황]");
					 	    	if(subMenu.equals("1")) { 
						 		   String newBookName = conInput("새로운 도서명을 입력하세요");
							 	   lvo.setBookName(newBookName);  
							 	   System.out.println("도서명이 변경되었습니다."); 
					 		   } else if(subMenu.equals("2")) {
							     	String newAuth = conInput("새로운 저자명을 입력하세요");
							    	lvo.setAuth(newAuth);  
							 		System.out.println("저자명이 변경되었습니다."); 
							   } else if(subMenu.equals("3")) {
							 		String newPbl = conInput("새로운 출판사명을 입력하세요");
							    	lvo.setPubl(newPbl);  
						 	        System.out.println("출판사명이 변경되었습니다."); 
					           } else if(subMenu.equals("4")) {
						        	String newRent = conInput("변경된 대여상황을 입력하세요[대여가능,예약가능,반납완료,대여불가]");
							 		lvo.setRent(newRent);  
						 	    	System.out.println("대여상황이 변경되었습니다."); 
					 	       }   else {
						 	    	System.out.println("잘못 입력하셨습니다. 도서 검색으로 돌아갑니다.");  
														 
						 	}
					   		System.out.println("======================================================================= \n"
								   	+ "ISBN번호\t"+"도서명\t"+"저자명\t"+" 출판사명\t"+"\t대여상황\n"
								 	+("----------------------------------------------------------------------- \n")	 );
								     System.out.printf("%d\t %s\t %s\t %s\t \t%s\n", 
								 	lvo.getISBN(), lvo.getBookName(), lvo.getAuth(), lvo.getPubl(), lvo.getRent()+"\n" );
								     System.out.print("도서 정보 수정을 종료하려면 0을 입력,\n계속하시려면 ");
								     break exit;
								     	} else System.out.print("일치하는 정보가 없습니다. 다시");	break again;							
				 
							 	}      
					 	} 
			}}
			}	

public void ResultNotice() {
	System.out.println("----------------다음과 같이 확인하세요---------------");
}

			
			

//회원 삭제하기
public void MemDel() {
all : while(true) {
		String delNum =  conInput("삭제할 회원의 회원번호를 입력하세요");
		
		Set<Integer> keySet = MemDataSet.memList.keySet(); 
		Iterator<Integer> i = keySet.iterator();
		Vector<String> delList = new Vector<String>();
		 
		while(i.hasNext()) {
			Integer key = (Integer)i.next();
			MemVO mvo = MemDataSet.memList.get(key);
		if(String.valueOf(mvo.getMemNum()).equals(delNum)) {
			i.remove( );
			System.out.println("회원 삭제가 완료되었습니다.");
				int keep= Integer.parseInt(conInput("회원 삭제를 계속 하시겠습니까?(0:종료, 1:계속)"));
			if(keep==0 )break all;
		 	} 
		}
} 
		
}
//도서 삭제하기
public void BookDel() {
	all : while(true) {
		String delNum =  conInput("삭제할 도서의 ISBN 번호를 입력하세요");
		
		Set<Integer> keySet = LibDataSet.libList.keySet(); 
		Iterator<Integer> i = keySet.iterator();
		Vector<String> delList = new Vector<String>();
		 
		while(i.hasNext()) {
			Integer key = (Integer)i.next();
			LibVO lvo = LibDataSet.libList.get(key);
		if(String.valueOf(lvo.getISBN()).equals(delNum)) {
			i.remove( );
			System.out.println("도서 삭제가 완료되었습니다.");
				int keep= Integer.parseInt(conInput("도서 삭제를 계속 하시겠습니까?(0:종료, 1:계속)"));
			if(keep==0 )break all;
		 	} 
		}
} 
		
 	 
	 
 	 }

			
			
			
				
				
				
				
				
				
				
				
				
				
	
	
	
	
	
	
	
	
	
	
	
	
	

public static void main(String[] args) { 
 new LibMain().start();
 System.out.println("프로그램이 종료되었습니다");
}

}//class end
  
 
