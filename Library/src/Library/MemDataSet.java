package Library;
import java.util.HashMap;

public class MemDataSet {
	
	public static HashMap<Integer, MemVO> MemList = new HashMap<Integer, MemVO>();
	
	public static void setMemList() {
		MemList.put(1, new MemVO(1, "박은영","park245","eifjk12","010-000-0000","서울시 동작구"));
		MemList.put(2, new MemVO(2, "김지용","GDragon","bigbang2","010-564-2456", "서울시 영등포구"));
		MemList.put(3, new MemVO(3, "김지용","GU1037gug","gugudan99","010-6987-1357","서울시 마포구"));
		MemList.put(4, new MemVO(4, "오석진","fivestone","five55five","010-9423-4235", "서울시 상암동"));
		MemList.put(5, new MemVO(5, "김잔디","grass09","plant0321","019-468-3246", "서울시 강남구"));
	}
//??????????????????????????
	public static boolean containsKey(int NewNum) {
	  	return false;
	  //??????????????????????????
	} 
}  
 