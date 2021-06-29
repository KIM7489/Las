package LasInfor;

public class LasVO {
	private int booknumber;
	private String bookname;
	private int sal;
	private String waiter;
	private String publisher;
	private String publish;
	private int books;
	
	public void lasPrint() {
		System.out.printf("%4d\t%8s\t%d\t %s\t %s\t %s\t%4d \n",booknumber,bookname,sal,waiter,publisher,publish,books);
	}
	
	public static void titlePrint1() {
		System.out.printf("%5s%7s%7s%14s%16s%7s%11s\n","도서번호","도서명","가격","저자","출판사","발행일","수량");
		System.out.println("=====================================================================================================");
		System.out.println();
	}
	
	public static void titlePrint() {
		System.out.printf("%5s%7s%7s%14s%16s%7s%11s\n","도서번호","도서명","가격","저자","출판사","발행일","재고");
		System.out.println("=====================================================================================================");
	}
	
	public LasVO(int booknumber, String bookname, int sal, String waiter, String publisher, String publish, int books) {
		this.booknumber = booknumber;
		this.bookname = bookname;
		this.sal = sal;
		this.waiter = waiter;
		this.publisher = publisher;
		this.publish = publish;
		this.books = books;
	}
	public LasVO() {
		// TODO Auto-generated constructor stub
	}

	public void booksAllchange(String bookname, int sal, String waiter, String publisher, String publish, int books) {
		this.bookname = bookname;
		this.sal = sal;
		this.waiter = waiter;
		this.publisher = publisher;
		this.publish = publish;
		this.books = books;
	}
	public void setBookNumber(int booknumber) {
		this.booknumber = booknumber;
	}
	public int getBookNumber() {
		return this.booknumber;
	}
	public void setBookName(String bookname) {
		this.bookname = bookname;
	}
	public String gerBookName() {
		return this.bookname;
	}
	public void setBookSal(int sal) {
		this.sal = sal;
	}
	public int getBookSal() {
		return this.sal;
	}
	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}
	public String getWaiter() {
		return this.waiter;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublisehr() {
		return this.publisher;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getPublish() {
		return this.publish;
	}
	public void setBooks(int books) {
		this.books = books;
	}
	public void setBooks1(int books) {
		this.books = this.books - books;
	}
	public void setBooksminors(int books) {
		this.books = this.books-books;
	}
	public int getBooks(int books) {
		return this.books;
	}
	public int getBooksData() {
		return this.books;
	}
	public String toString() {
		String memberData = "   "+booknumber +"	  " + bookname +"	" + sal + "	 "+ waiter + "	 " + publisher + "	 " + publish + "	   "+ books;
		return memberData;
	}

	private int books1;
}
