package LasInfor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;


public class LasManager {
	
	Scanner scan = new Scanner(System.in);
	
	public LasManager() {
		
	}
// =========================================== Strat 메소드 =================================================================

public void start() { 
	int num = 0;
	Login.userIdlist(); // 회원목록 초기값
	LasLoginData.loginSet(); //회원 아이디 비밀번호 초기값
	LasDataSet.basicLasSet();//도서 초기값
	while(true) {
	num = loop1(); //로그인 회원가입 메소드  3 = 관리자 1 =  사용자
	lasPage1(num); //관리자 or 사용자 페이지 
	}
}

//=========================================== Strat 메소드 ==================================================================


// 아이디 제한 체크

		public String idCheck() {
	
			boolean name1 = false;
								
			String name = "";
								
			while(true) {
				name = "";
				name = inData("아이디");
				name1 = Pattern.matches("^([a-z]+[0-9]*)$", name);
								
									
							if(name.matches("(\\W+[a-z0-9]*\\W*){3,10}")) {
									System.out.println("특수문자 또는 한글은 사용하실수 없습니다.");
							} 
							else if(name.matches("\\W+[a-z0-9]*")) {
									System.out.println("특수문자 또는 한글은 사용하실수 없습니다.");
							}
							else if(name.matches("([0-9]+[a-z]*)")) {
									System.out.println("첫글자로 숫자를 사용하실수 없습니다.");
							}
							else if(name1==true && name.length() >= 4 && name.length() < 10) {
									break;
							}
								
							else if(name.length() < 4) {
									System.out.println("4자리 이상으로 만들어주세요.");
							}
								
							else if(name.length() > 10) {
									System.out.println("10자리 이하로 만들어주세요.");
							} else {
									System.out.println("아이디 생성 양식을 확인해 주세요.");
									System.out.println("영어 숫자 조합 / 특수문자 사용불가 / 첫글자 숫자 사용불가 / 문자와 문자사이 특수문자 숫자 사용불가.");
							}
							}
			return name;	
	}
	
//회원 가입 기능
	
	public void memberIn() {// admin key는 입력불가능하게 설정
	System.out.println();
	System.out.println("==============Las 회원가입===============");
	System.out.println();
	String id = idCheck(); // 4글자 이상 10글자이하 제한 
	String pwd	= inData("비밀번호");
	String email =	inData("이메일");
	String add  = inData("주소");
	String day = inData("등록일");
	System.out.printf("회원가입이 완료\n[아이디:%s]\n",id);
	LasLoginData.hs1.put(id,new LoginVO(id,pwd,email,add,day));
	Login.map.put(id, pwd);
	}	

//회원 정보 전부 출력
	
	public void memberAll() {
		LoginVO.memberPrint();
		Collection<LoginVO> list = LasLoginData.hs1.values();
		Iterator<LoginVO> ii = list.iterator();
		while(ii.hasNext()) {
			LoginVO lo = ii.next();
			lo.loginPrint();
		}
		
	}

//로그인 기능 구현

	public int loginSystem() {
		//로그인 시도 횟수 제한 기능
		int result = 0;
		for(int i = 1; i <= 6; i++) {
		if(i==6) {
			System.out.println("로그인 시도 횟수를 초과하셨습니다 시스템을 종료합니다.");
			System.exit(0);
		}
		System.out.println();
		System.out.println("==============Las Login===============");
		String userid = inData("아이디");
		String userpwd = inData("비밀번호");
		  
		Login log = new Login();
		result = log.loginCheck(userid, userpwd);

		if(result==4) {
			String login;
			System.out.println("존재하지 않는 아이디입니다.");
			login = inData("다시 로그인 하시겠습니까? Y/N");
			if(login.equals("Y") || login.equals("y")) {
				i=1;
			} else if(login.equals("N") || login.equals("n")) {
				break;
			} else {
				
					while(true) {
								  System.out.println("Y or N을 입력하셔야합니다. 다시 입력해주세요");
								  login = inData("다시 로그인 하시겠습니까? Y/N");
								  if(login.equals("Y")|| login.equals("y")) {
									  i=1;
									  break;
								  } else if(login.equals("N") || login.equals("n")) {
									  
									  break;
								  }
								}
			}
		}
		if(result==3) {
			break;
		}
		if(result==2) {
			System.out.printf("비밀번호를 잘못 입력하셨습니다. 5번 실패시 프로그램이 종료됩니다. 실패 횟수:%d\n",i);
		}if(result==1) {
			break;
		}
		}
	if(result == 3) {
		return 3; // 관리자
	}else if(result == 1) {
		return 1; /// 사용자
	}else {
		return 0;
	}
	}
// 로그인 회원가입 반복문
	public int loop1() {
		int i = 0;
	
	while(true) {
		System.out.println("=============도서관리 시스템==============");
		try {
		int st = intData("\t1.로그인 2.회원가입 3.종료\n=========================================\n");
		
		  if(st==1) {
							i = loginSystem(); // 3 관리자 1 사용자
							break;
		} else if(st == 2) {
							memberIn(); // 회원가입
		} else if(st==3){
							System.exit(0); // 종료
		} else {
			System.out.println("1 or 2 or 3중 하나를 입력하셔야 합니다.");
			loop1(); // 반복
		}
		}catch(NumberFormatException e){
			System.out.println(e + "숫자만 입력 하셔야 됩니다.");
		}
	}
	
		return i; //1 == master 2== 
	}
//// 관리자 페이지 or 사용자 페이지 만들기 
	public void lasPage1(int num) {
	LasDataSet.hs2.clear();
	
	breakout: while(true) {
	
	if(num==3) {
			System.out.println();
			System.out.println("\t\t\t\t관리자 페이지");
			System.out.println();
			System.out.println("=====================================================================================================");
			memberAll();
			System.out.println();
			System.out.println("=====================================================================================================");
			System.out.println();
			int result2 = intData("1.회원정보 수정 2.도서관리 페이지 3.로그아웃\n");
   if(result2 == 1) {
	   		System.out.println();
			System.out.println();
			System.out.println("\t\t\t회원정보 수정 페이지");
			System.out.println();	
			memberAll();
			System.out.println();
			System.out.println("=====================================================================================================");
			System.out.println();
	 int code = intData("1.비밀번호  2.이메일  3.주소  4.관리자 페이지 이동");
		 if(code == 1) {
					pwdChange();
		 } else if(code ==2) {
					emailChange();
		 } else if(code == 3) {
					addChange();
		 } else if(code == 4) {
					
		 } else {
					System.out.println("1 or 2 or 3 or 4 를 입력해주세요.");
				}
			} 
  if(result2 == 2) {
	  int set = 0;
	  int set1 =0;
	  	while(true) {
	  		try {
	  			if(set==0) {
					System.out.println();
					System.out.println("\t\t\t\t도서관리 페이지");
					System.out.println();	
					bookPrint();
	  				System.out.println();
					System.out.println("=====================================================================================================");
					System.out.println();
	  			} else if (set==1) {
	  				salSortdown();
	  			} else if (set==2) {
	  				salSortup();
	  			}
	  				System.out.println();
	  				int code = intData("1.도서명  2.가격  3.저자  4.출판사  5.발행일  6.재고  7.모두변경  8.도서추가  9.관리자 페이지  10.가격이 낮은순 정렬\n\n11.가격 높은순 정렬  12.정렬 초기화\n");
	
	 if(code == 1) {
		 			nameChange();
		 			
	 				}
	 
	 if(code == 2) {
		 			
		 			salChange();
	 				
	 	           }
	
	 if(code == 3) {
		 
		 			waiterChange();
		 
	 			   }
	
	 if(code == 4) {
		 
		 			publisherChange();
		 
	 				}	  				
	 
	 if(code == 5) {
		 
		 			publishChange();
		 
	   				}	  	
	 
	 if(code == 6) {
		 			
		 			booksChange();
		 
	   				}
	
	 if(code == 7) {
		 			
		 			bookAllChange();
		 
	   				}	
	
	 if(code == 8) {
		 
		 			bookAdd();
		 			
	   				}
	 
	 if(code == 9) {
		 	break;
		 
					}
	 if(code == 10) {

		 			set = 1;
	 }
	 if(code == 11) {
		 			set = 2;
	 }
	 if(code == 12) {
		 			set = 0;
	 } 
	  					}catch(NumberFormatException e) {
	  													System.out.println("숫자만 입력하셔야 합니다.");
	  													}
	  	}
	  				
  
  	}
  if(result2 == 3) {
	  				break breakout;
  }
           
  else {
				System.out.println("1 또는 2를 입력해 주세요");
			}
			
		} 

if(num==1) {
	int set = 0;
	while(true) {
		if(set ==3){
			break;
		}
		if(set==0) {	
			System.out.println();
			System.out.println("\t\t\t\t상품 구매 페이지");
			System.out.println();
			bookPrint();
			System.out.println();
			System.out.println("=====================================================================================================");
			System.out.println();
					}
		if(set==1) {
					salSortdown1();
					System.out.println();
					System.out.println("=====================================================================================================");
					System.out.println();
					}
		if(set==2) {
					salSortup1();
					System.out.println();
					System.out.println("=====================================================================================================");
					System.out.println();
					}
		
		int result = intData("1.상품 바로 구매 2.장바구니 담기 3.장바구니 이동 4.가격 낮은순으로 보기 5. 가격 높은순으로 보기 6. 정렬 초기화 7. 로그아웃\n");
	if(result == 1) { // 상품 구매
					bookby();
					}
	
	if(result == 2) {
					myBy();
					}
	
	if(result==4) {
		set = 1;
	}
	if(result == 5) {
		set = 2;
	}
	if(result == 6) {
		set = 0;
	}
	if(result == 7) {
		break breakout;

	}
	
	if(result == 3) {
						
			while(true) {	
				// 장바구니 이동 
					myPrint();
					System.out.println();
					System.out.println("=====================================================================================================");
					System.out.println();
					int result1 = intData("1.도서 구매하기  2.장바구니 내보내기  3.구매페이지  4.로그아웃\n");
					if(result1==1) {
									myBy1();
									System.out.println("구매 완료");
					} else if(result1 == 2) {
									myBy2();
											}
					if(result1 == 3) {
										break;
									 }		
					if(result1 == 4) {
										break breakout;
									 }
										}			
			}

}
}	
}
	}
	
	//도서 전체 출력 메소드
	public void bookPrint() {
		new LasVO().titlePrint();
		System.out.println();
		Collection<LasVO> list = LasDataSet.hs.values();
		Iterator<LasVO> ii =list.iterator();
		
		while(ii.hasNext()) {
			LasVO la = ii.next();
			la.lasPrint();
		}
	}
	//장바구니 전체 출력 메소드
	public void myPrint() {
			new LasVO().titlePrint();
			System.out.println();
			Collection<LasVO> list = LasDataSet.hs2.values();
			Iterator<LasVO> ii =list.iterator();
			
			while(ii.hasNext()) {
				LasVO la = ii.next();
				la.lasPrint();
			}
	}
			
//====================================================장바구니 메소드 ========================================================
	
	
	
	
	
	

	
	public void myBy() { //장바구니 이동
		int my = intData("장바구니에 담을 도서번호를 입력하세요.\n");
		LasDataSet.hs2.put(my, LasDataSet.hs.get(my));
	}
	
	
	public void myBy1() {
						int booknumber = intData("구매하실 도서 번호를 입력해 주세요.");
						int books = intData("구매하실 수량을 입력해 주세요.");
						LasVO vo = LasDataSet.hs.get(booknumber);	
	
						if(vo.getBooksData() < books) {
						System.out.println("재고가 부족합니다.");
						} else if( books == 0){
						System.out.println("0개는 구매할수 없습니다.");
						}
						else if(vo.getBooksData() >= books) {
						vo.setBooksminors(books);
						LasDataSet.hs2.remove(booknumber);
							}
					
}
	//장바구니 내보니기
	public void myBy2() {
		int booknumber = intData("도서 번호를 입력해 주세요.");
		LasDataSet.hs2.remove(booknumber);	
		
	}
//----------------------------------------------------도서 구매 메소드 ------------------------------------------------------------------------	
	
	public void bookby() {
		
			int booknumber = intData("구매하실 도서 번호를 입력해 주세요.");
			int books = intData("구매하실 수량을 입력해 주세요.");
			LasVO vo = LasDataSet.hs.get(booknumber);	
			
			if(vo.getBooksData() < books) {
				System.out.println("재고가 부족합니다.");
			} else if( books == 0){
				System.out.println("0개는 구매할수 없습니다.");
			}
			else if(vo.getBooksData() >= books) {
				vo.setBooksminors(books);
			}

}
// -----------------------------------------------변경 메소드 정리 추가----------------------------------------------------------------------		
    //가격 정렬 메소드. 

	
	
	//비밀번호 변경 메소드
	public void pwdChange() {
		
		System.out.println("비밀번호를 변경할 아이디를 입력해 주세요.");
		String id = inData("아이디:"); //강제 예외
		System.out.println("변경할 비밀번호를 입력해주세요");
		String pwd = inData("비밀번호:");
		Login.map.replace(id, pwd);
		LoginVO vo = LasLoginData.hs1.get(id);
		vo.setUserpwed(pwd);
	}
	
	//이메일 변경 메소드
	public void emailChange() {
		System.out.println("이메일을 변경할 아이디를 입력해 주세요.");
		String id = inData("아이디:"); // 강제 예외
		System.out.println("변경할 이메일을 입력해주세요");
		String email = inData("이메일:");
		LoginVO vo = LasLoginData.hs1.get(id);
		vo.setUserEmail(email);
	}
	
	//주소 변경 메소드
	public void addChange() {
		System.out.println("주소를 변경할 아이디를 입력해 주세요.");
		String id = inData("아이디:"); // 강제 예외
		System.out.println("변경할 주소를 입력해주세요");
		String add = inData("주소:");
		LoginVO vo = LasLoginData.hs1.get(id);
		vo.setUseradd(add);
	}
	//도서명 변경 메소드
	public void nameChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int id = intData("도서번호:"); // 강제 예외
		System.out.println("변경할 도서명을 입력해 주세요.");
		String add = inData("도서명:");
		LasVO la = LasDataSet.hs.get(id);
		la.setBookName(add);
	}
	//도서가격 변경 메소드
	public void salChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int id = intData("도서번호:"); // 강제 예외
		System.out.println("변경할 도서 가격을 입력해 주세요.");
		int add = intData("가격:");
		LasVO la = LasDataSet.hs.get(id);
		la.setBookSal(add);
	}
	//도서 저자 변경 메소드
	public void waiterChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int id = intData("도서번호:"); // 강제 예외
		System.out.println("변경할 저자명을 입력해 주세요.");
		String add = inData("저자:");
		LasVO la = LasDataSet.hs.get(id);
		la.setWaiter(add);
	}
	//도서 출판사 변경 메소드
	public void publisherChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int id = intData("도서번호:"); // 강제 예외
		System.out.println("변경할 출판사명을 입력해 주세요.");
		String add = inData("출판사:");
		LasVO la = LasDataSet.hs.get(id);
		la.setPublisher(add);
	}
	//도서 발행일 변경 메소드
	public void publishChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int id = intData("도서번호:"); // 강제 예외
		System.out.println("변경할 출판사명을 입력해 주세요.");
		String add = inData("출판사:");
		LasVO la = LasDataSet.hs.get(id);
		la.setPublish(add);
	}
	//재고 변경
	public void booksChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int id = intData("도서번호:"); // 강제 예외
		System.out.println("변경할 출판사명을 입력해 주세요.");
		String add = inData("출판사:");
		LasVO la = LasDataSet.hs.get(id);
		la.setBooks(id);
	}
	
	//전부 변경
	public void bookAllChange() {
		System.out.println("변경할 도서번호를 입력해 주세요.");
		int number = intData("도서번호:"); 
		System.out.println("변경할 도서명을 입력해 주세요.");
		String name = inData("도서명:");
		System.out.println("변경할 가격을 입력해 주세요.");
		int sal = intData("가격:");
		System.out.println("변경할 저자명 입력해 주세요.");
		String waiter = inData("저자:");
		System.out.println("변경할 출판사명을 입력해 주세요.");
		String publisher = inData("출판사:");
		System.out.println("변경할 발행일을 입력해 주세요.");
		String publish = inData("발행일:");
		System.out.println("수정할 재고를 입력해 주세요.");
		int books = intData("재고:");
		
		LasVO la = LasDataSet.hs.get(number);
		la.booksAllchange(name,sal, waiter, publisher,publish,books);
		
	}
	//도서 추가
	public void bookAdd() {
		System.out.println("도서번호를 입력해 주세요.");
		int number = intData("도서번호:"); 
		System.out.println("도서명을 입력해 주세요.");
		String name = inData("도서명:");
		System.out.println("가격을 입력해 주세요.");
		int sal = intData("가격:");
		System.out.println("저자명 입력해 주세요.");
		String waiter = inData("저자:");
		System.out.println("출판사명을 입력해 주세요.");
		String publisher = inData("출판사:");
		System.out.println("발행일을 입력해 주세요.");
		String publish = inData("발행일:");
		System.out.println("재고를 입력해 주세요.");
		int books = intData("재고:");

		LasDataSet.hs.put(number,new LasVO(number,name,sal, waiter, publisher,publish,books));
	}
// -----------------------------------------------변경 메소드 ----------------------------------------------------------------------	

// -----------------------------------------------입력 메소드 정리--------------------------------------------------------		
	//콘솔 인트 입력 메소드
	public int intData(String msg) {
		System.out.print(msg);
		return Integer.parseInt(scan.nextLine());
	}

	//콘솔 문자열 입력 메소드
	public String inData(String msg) {
		System.out.println(msg+"");
		return scan.nextLine();
	}
//-----------------------------------------------입력 메소드--------------------------------------------------------------------	

	
	//가격 정렬 메소드
	public void salSortdown() {
		ArrayList<LasVO> vd = new ArrayList<LasVO>();
		Collection<LasVO> va = LasDataSet.hs.values();
		Iterator<LasVO> ii = va.iterator();
	
		while(ii.hasNext()) {
			vd.add(ii.next());
		}
		
		Collections.sort(vd , new SortSal());
		System.out.println();
		System.out.println("\t\t\t\t도서관리 페이지");
		System.out.println();
		
		new LasVO().titlePrint1();
		for(LasVO vo : vd) {
			System.out.println(vo.toString());
		}
	
	
	}
	
	public void salSortdown1() {
		ArrayList<LasVO> vd = new ArrayList<LasVO>();
		Collection<LasVO> va = LasDataSet.hs.values();
		Iterator<LasVO> ii = va.iterator();
	
		while(ii.hasNext()) {
			vd.add(ii.next());
		}
		
		Collections.sort(vd , new SortSal());
		System.out.println();
		System.out.println("\t\t\t\t상품 구매 페이지");
		System.out.println();
		
		new LasVO().titlePrint1();
		for(LasVO vo : vd) {
			System.out.println(vo.toString());
		}
	
	
	}
	public void salSortup() {
		
		ArrayList<LasVO> vd = new ArrayList<LasVO>();
		Collection<LasVO> va = LasDataSet.hs.values();
		Iterator<LasVO> ii = va.iterator();
	
		while(ii.hasNext()) {
			vd.add(ii.next());
		}
		
		Collections.sort(vd , new SortSalUp());
		System.out.println();
		System.out.println("\t\t\t\t도서관리 페이지");
		System.out.println();
		
		new LasVO().titlePrint1();
		System.out.println();
		for(LasVO vo : vd) {
			System.out.println(vo.toString());
		}
		}
	
	public void salSortup1() {
		
		ArrayList<LasVO> vd = new ArrayList<LasVO>();
		Collection<LasVO> va = LasDataSet.hs.values();
		Iterator<LasVO> ii = va.iterator();
	
		while(ii.hasNext()) {
			vd.add(ii.next());
		}
		
		Collections.sort(vd , new SortSalUp());
		System.out.println();
		System.out.println("\t\t\t\t상품 구매 페이지");
		System.out.println();
		
		new LasVO().titlePrint1();
		for(LasVO vo : vd) {
			System.out.println(vo.toString());
		}
	}


//----------------------------------------------내부 클래스----------------------------------------------------------------------

class SortSal implements Comparator<LasVO> {
		public int compare(LasVO v1, LasVO v2) {
			return (v1.getBookSal() < v2.getBookSal()) ? -1 :(v1.getBookSal() > v2.getBookSal()) ? 1: 0;
		}
	}

class SortSalUp implements Comparator<LasVO> {
	public int compare(LasVO v1, LasVO v2) {
		return (v1.getBookSal() > v2.getBookSal()) ? -1 :(v1.getBookSal() < v2.getBookSal()) ? 1: 0;
	}
}

}

