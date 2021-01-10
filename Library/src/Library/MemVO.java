package Library;

public class MemVO { 
		private int MemNum;
		private String MemName; 
		private String ID;
		private String PWD;
		private String Tel;
		private String Addr; 
		
		
		 
		public MemVO(int MemNum,String MemName,String ID, String PWD, String Tel, String Addr) {
			this.MemNum = MemNum;
			this.MemName = MemName;
			this.ID = ID;
			this.PWD = PWD;
			this.Tel = Tel;
			this.Addr = Addr;
		 }



		public int getMemNum() {
			return MemNum;
		} public void setMemNum(int memNum) {
			MemNum = memNum;
		} public String getMemName() {
			return MemName;
		} public void setMemName(String memName) {
			MemName = memName;
		} public String getID() {
			return ID;
		} public void setID(String iD) {
			ID = iD;
		} public String getPWD() {
			return PWD;
		} 	public void setPWD(String pWD) {
			PWD = pWD;
		}
 	public String getTel() {
			return Tel;
		}
 	public void setTel(String tel) {
			Tel = tel;
		}
 	public String getAddr() {
			return Addr;
		} 
		public void setAddr(String addr) {
			Addr = addr;
		}
 		 
 }
 