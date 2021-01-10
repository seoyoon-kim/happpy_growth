package Library;
 
import java.util.HashMap; 

public class LibDataSet {

	 
   	public static HashMap<Integer, LibVO> LibList = new HashMap<Integer, LibVO>();
		
		 
	public static void setLibList() {
		LibList.put(1326580, new LibVO(1326580, "트렌드 코리아","김난도","미래의 창","반납완료"));
		LibList.put(4598610, new LibVO(4598610, "마음의 시","류잔용","생각나루","대여가능"));
		LibList.put(9765103, new LibVO(9765103, "과학자의 미술관","최진화","비상","대여가능"));
		LibList.put(4698016, new LibVO(4698016, "뉴 프로그래밍","이태영","길벗","대여불가"));
		LibList.put(7943059, new LibVO(7943059, "테슬라와 미래","주은석","토네이도","예약가능"));
	}
	
	 
}
 
