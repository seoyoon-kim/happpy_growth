package Library;

public class LibVO { 
		private int ISBN;
		private String bookName;
		private String auth;
		private String publ; 
		private String rent; 
		
		
		 
		public LibVO(int ISBN,String bookName,String auth, String publ, String rent ) {
			this.ISBN = ISBN;
			this.bookName = bookName;
			this.auth = auth;
			this.publ = publ;
			this.rent = rent;
		 }



		public int getISBN() {
			return ISBN;
		}
		public void setISBN(int iSBN) {
			ISBN = iSBN;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getAuth() {
			return auth;
		}
		public void setAuth(String auth) {
			this.auth = auth;
		}
		public String getPubl() {
			return publ;
		}
		public void setPubl(String publ) {
			this.publ = publ;
		}
		public String getRent(String rent) {
			return rent;
		}
		public void setRent(String rent) {
			this.rent = rent;
		}
 }
 