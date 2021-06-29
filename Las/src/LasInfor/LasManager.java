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
// =========================================== Strat �޼ҵ� =================================================================

public void start() { 
	int num = 0;
	Login.userIdlist(); // ȸ����� �ʱⰪ
	LasLoginData.loginSet(); //ȸ�� ���̵� ��й�ȣ �ʱⰪ
	LasDataSet.basicLasSet();//���� �ʱⰪ
	while(true) {
	num = loop1(); //�α��� ȸ������ �޼ҵ�  3 = ������ 1 =  �����
	lasPage1(num); //������ or ����� ������ 
	}
}

//=========================================== Strat �޼ҵ� ==================================================================


// ���̵� ���� üũ

		public String idCheck() {
	
			boolean name1 = false;
								
			String name = "";
								
			while(true) {
				name = "";
				name = inData("���̵�");
				name1 = Pattern.matches("^([a-z]+[0-9]*)$", name);
								
									
							if(name.matches("(\\W+[a-z0-9]*\\W*){3,10}")) {
									System.out.println("Ư������ �Ǵ� �ѱ��� ����ϽǼ� �����ϴ�.");
							} 
							else if(name.matches("\\W+[a-z0-9]*")) {
									System.out.println("Ư������ �Ǵ� �ѱ��� ����ϽǼ� �����ϴ�.");
							}
							else if(name.matches("([0-9]+[a-z]*)")) {
									System.out.println("ù���ڷ� ���ڸ� ����ϽǼ� �����ϴ�.");
							}
							else if(name1==true && name.length() >= 4 && name.length() < 10) {
									break;
							}
								
							else if(name.length() < 4) {
									System.out.println("4�ڸ� �̻����� ������ּ���.");
							}
								
							else if(name.length() > 10) {
									System.out.println("10�ڸ� ���Ϸ� ������ּ���.");
							} else {
									System.out.println("���̵� ���� ����� Ȯ���� �ּ���.");
									System.out.println("���� ���� ���� / Ư������ ���Ұ� / ù���� ���� ���Ұ� / ���ڿ� ���ڻ��� Ư������ ���� ���Ұ�.");
							}
							}
			return name;	
	}
	
//ȸ�� ���� ���
	
	public void memberIn() {// admin key�� �ԷºҰ����ϰ� ����
	System.out.println();
	System.out.println("==============Las ȸ������===============");
	System.out.println();
	String id = idCheck(); // 4���� �̻� 10�������� ���� 
	String pwd	= inData("��й�ȣ");
	String email =	inData("�̸���");
	String add  = inData("�ּ�");
	String day = inData("�����");
	System.out.printf("ȸ�������� �Ϸ�\n[���̵�:%s]\n",id);
	LasLoginData.hs1.put(id,new LoginVO(id,pwd,email,add,day));
	Login.map.put(id, pwd);
	}	

//ȸ�� ���� ���� ���
	
	public void memberAll() {
		LoginVO.memberPrint();
		Collection<LoginVO> list = LasLoginData.hs1.values();
		Iterator<LoginVO> ii = list.iterator();
		while(ii.hasNext()) {
			LoginVO lo = ii.next();
			lo.loginPrint();
		}
		
	}

//�α��� ��� ����

	public int loginSystem() {
		//�α��� �õ� Ƚ�� ���� ���
		int result = 0;
		for(int i = 1; i <= 6; i++) {
		if(i==6) {
			System.out.println("�α��� �õ� Ƚ���� �ʰ��ϼ̽��ϴ� �ý����� �����մϴ�.");
			System.exit(0);
		}
		System.out.println();
		System.out.println("==============Las Login===============");
		String userid = inData("���̵�");
		String userpwd = inData("��й�ȣ");
		  
		Login log = new Login();
		result = log.loginCheck(userid, userpwd);

		if(result==4) {
			String login;
			System.out.println("�������� �ʴ� ���̵��Դϴ�.");
			login = inData("�ٽ� �α��� �Ͻðڽ��ϱ�? Y/N");
			if(login.equals("Y") || login.equals("y")) {
				i=1;
			} else if(login.equals("N") || login.equals("n")) {
				break;
			} else {
				
					while(true) {
								  System.out.println("Y or N�� �Է��ϼž��մϴ�. �ٽ� �Է����ּ���");
								  login = inData("�ٽ� �α��� �Ͻðڽ��ϱ�? Y/N");
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
			System.out.printf("��й�ȣ�� �߸� �Է��ϼ̽��ϴ�. 5�� ���н� ���α׷��� ����˴ϴ�. ���� Ƚ��:%d\n",i);
		}if(result==1) {
			break;
		}
		}
	if(result == 3) {
		return 3; // ������
	}else if(result == 1) {
		return 1; /// �����
	}else {
		return 0;
	}
	}
// �α��� ȸ������ �ݺ���
	public int loop1() {
		int i = 0;
	
	while(true) {
		System.out.println("=============�������� �ý���==============");
		try {
		int st = intData("\t1.�α��� 2.ȸ������ 3.����\n=========================================\n");
		
		  if(st==1) {
							i = loginSystem(); // 3 ������ 1 �����
							break;
		} else if(st == 2) {
							memberIn(); // ȸ������
		} else if(st==3){
							System.exit(0); // ����
		} else {
			System.out.println("1 or 2 or 3�� �ϳ��� �Է��ϼž� �մϴ�.");
			loop1(); // �ݺ�
		}
		}catch(NumberFormatException e){
			System.out.println(e + "���ڸ� �Է� �ϼž� �˴ϴ�.");
		}
	}
	
		return i; //1 == master 2== 
	}
//// ������ ������ or ����� ������ ����� 
	public void lasPage1(int num) {
	LasDataSet.hs2.clear();
	
	breakout: while(true) {
	
	if(num==3) {
			System.out.println();
			System.out.println("\t\t\t\t������ ������");
			System.out.println();
			System.out.println("=====================================================================================================");
			memberAll();
			System.out.println();
			System.out.println("=====================================================================================================");
			System.out.println();
			int result2 = intData("1.ȸ������ ���� 2.�������� ������ 3.�α׾ƿ�\n");
   if(result2 == 1) {
	   		System.out.println();
			System.out.println();
			System.out.println("\t\t\tȸ������ ���� ������");
			System.out.println();	
			memberAll();
			System.out.println();
			System.out.println("=====================================================================================================");
			System.out.println();
	 int code = intData("1.��й�ȣ  2.�̸���  3.�ּ�  4.������ ������ �̵�");
		 if(code == 1) {
					pwdChange();
		 } else if(code ==2) {
					emailChange();
		 } else if(code == 3) {
					addChange();
		 } else if(code == 4) {
					
		 } else {
					System.out.println("1 or 2 or 3 or 4 �� �Է����ּ���.");
				}
			} 
  if(result2 == 2) {
	  int set = 0;
	  int set1 =0;
	  	while(true) {
	  		try {
	  			if(set==0) {
					System.out.println();
					System.out.println("\t\t\t\t�������� ������");
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
	  				int code = intData("1.������  2.����  3.����  4.���ǻ�  5.������  6.���  7.��κ���  8.�����߰�  9.������ ������  10.������ ������ ����\n\n11.���� ������ ����  12.���� �ʱ�ȭ\n");
	
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
	  													System.out.println("���ڸ� �Է��ϼž� �մϴ�.");
	  													}
	  	}
	  				
  
  	}
  if(result2 == 3) {
	  				break breakout;
  }
           
  else {
				System.out.println("1 �Ǵ� 2�� �Է��� �ּ���");
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
			System.out.println("\t\t\t\t��ǰ ���� ������");
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
		
		int result = intData("1.��ǰ �ٷ� ���� 2.��ٱ��� ��� 3.��ٱ��� �̵� 4.���� ���������� ���� 5. ���� ���������� ���� 6. ���� �ʱ�ȭ 7. �α׾ƿ�\n");
	if(result == 1) { // ��ǰ ����
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
				// ��ٱ��� �̵� 
					myPrint();
					System.out.println();
					System.out.println("=====================================================================================================");
					System.out.println();
					int result1 = intData("1.���� �����ϱ�  2.��ٱ��� ��������  3.����������  4.�α׾ƿ�\n");
					if(result1==1) {
									myBy1();
									System.out.println("���� �Ϸ�");
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
	
	//���� ��ü ��� �޼ҵ�
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
	//��ٱ��� ��ü ��� �޼ҵ�
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
			
//====================================================��ٱ��� �޼ҵ� ========================================================
	
	
	
	
	
	

	
	public void myBy() { //��ٱ��� �̵�
		int my = intData("��ٱ��Ͽ� ���� ������ȣ�� �Է��ϼ���.\n");
		LasDataSet.hs2.put(my, LasDataSet.hs.get(my));
	}
	
	
	public void myBy1() {
						int booknumber = intData("�����Ͻ� ���� ��ȣ�� �Է��� �ּ���.");
						int books = intData("�����Ͻ� ������ �Է��� �ּ���.");
						LasVO vo = LasDataSet.hs.get(booknumber);	
	
						if(vo.getBooksData() < books) {
						System.out.println("��� �����մϴ�.");
						} else if( books == 0){
						System.out.println("0���� �����Ҽ� �����ϴ�.");
						}
						else if(vo.getBooksData() >= books) {
						vo.setBooksminors(books);
						LasDataSet.hs2.remove(booknumber);
							}
					
}
	//��ٱ��� �����ϱ�
	public void myBy2() {
		int booknumber = intData("���� ��ȣ�� �Է��� �ּ���.");
		LasDataSet.hs2.remove(booknumber);	
		
	}
//----------------------------------------------------���� ���� �޼ҵ� ------------------------------------------------------------------------	
	
	public void bookby() {
		
			int booknumber = intData("�����Ͻ� ���� ��ȣ�� �Է��� �ּ���.");
			int books = intData("�����Ͻ� ������ �Է��� �ּ���.");
			LasVO vo = LasDataSet.hs.get(booknumber);	
			
			if(vo.getBooksData() < books) {
				System.out.println("��� �����մϴ�.");
			} else if( books == 0){
				System.out.println("0���� �����Ҽ� �����ϴ�.");
			}
			else if(vo.getBooksData() >= books) {
				vo.setBooksminors(books);
			}

}
// -----------------------------------------------���� �޼ҵ� ���� �߰�----------------------------------------------------------------------		
    //���� ���� �޼ҵ�. 

	
	
	//��й�ȣ ���� �޼ҵ�
	public void pwdChange() {
		
		System.out.println("��й�ȣ�� ������ ���̵� �Է��� �ּ���.");
		String id = inData("���̵�:"); //���� ����
		System.out.println("������ ��й�ȣ�� �Է����ּ���");
		String pwd = inData("��й�ȣ:");
		Login.map.replace(id, pwd);
		LoginVO vo = LasLoginData.hs1.get(id);
		vo.setUserpwed(pwd);
	}
	
	//�̸��� ���� �޼ҵ�
	public void emailChange() {
		System.out.println("�̸����� ������ ���̵� �Է��� �ּ���.");
		String id = inData("���̵�:"); // ���� ����
		System.out.println("������ �̸����� �Է����ּ���");
		String email = inData("�̸���:");
		LoginVO vo = LasLoginData.hs1.get(id);
		vo.setUserEmail(email);
	}
	
	//�ּ� ���� �޼ҵ�
	public void addChange() {
		System.out.println("�ּҸ� ������ ���̵� �Է��� �ּ���.");
		String id = inData("���̵�:"); // ���� ����
		System.out.println("������ �ּҸ� �Է����ּ���");
		String add = inData("�ּ�:");
		LoginVO vo = LasLoginData.hs1.get(id);
		vo.setUseradd(add);
	}
	//������ ���� �޼ҵ�
	public void nameChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int id = intData("������ȣ:"); // ���� ����
		System.out.println("������ �������� �Է��� �ּ���.");
		String add = inData("������:");
		LasVO la = LasDataSet.hs.get(id);
		la.setBookName(add);
	}
	//�������� ���� �޼ҵ�
	public void salChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int id = intData("������ȣ:"); // ���� ����
		System.out.println("������ ���� ������ �Է��� �ּ���.");
		int add = intData("����:");
		LasVO la = LasDataSet.hs.get(id);
		la.setBookSal(add);
	}
	//���� ���� ���� �޼ҵ�
	public void waiterChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int id = intData("������ȣ:"); // ���� ����
		System.out.println("������ ���ڸ��� �Է��� �ּ���.");
		String add = inData("����:");
		LasVO la = LasDataSet.hs.get(id);
		la.setWaiter(add);
	}
	//���� ���ǻ� ���� �޼ҵ�
	public void publisherChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int id = intData("������ȣ:"); // ���� ����
		System.out.println("������ ���ǻ���� �Է��� �ּ���.");
		String add = inData("���ǻ�:");
		LasVO la = LasDataSet.hs.get(id);
		la.setPublisher(add);
	}
	//���� ������ ���� �޼ҵ�
	public void publishChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int id = intData("������ȣ:"); // ���� ����
		System.out.println("������ ���ǻ���� �Է��� �ּ���.");
		String add = inData("���ǻ�:");
		LasVO la = LasDataSet.hs.get(id);
		la.setPublish(add);
	}
	//��� ����
	public void booksChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int id = intData("������ȣ:"); // ���� ����
		System.out.println("������ ���ǻ���� �Է��� �ּ���.");
		String add = inData("���ǻ�:");
		LasVO la = LasDataSet.hs.get(id);
		la.setBooks(id);
	}
	
	//���� ����
	public void bookAllChange() {
		System.out.println("������ ������ȣ�� �Է��� �ּ���.");
		int number = intData("������ȣ:"); 
		System.out.println("������ �������� �Է��� �ּ���.");
		String name = inData("������:");
		System.out.println("������ ������ �Է��� �ּ���.");
		int sal = intData("����:");
		System.out.println("������ ���ڸ� �Է��� �ּ���.");
		String waiter = inData("����:");
		System.out.println("������ ���ǻ���� �Է��� �ּ���.");
		String publisher = inData("���ǻ�:");
		System.out.println("������ �������� �Է��� �ּ���.");
		String publish = inData("������:");
		System.out.println("������ ��� �Է��� �ּ���.");
		int books = intData("���:");
		
		LasVO la = LasDataSet.hs.get(number);
		la.booksAllchange(name,sal, waiter, publisher,publish,books);
		
	}
	//���� �߰�
	public void bookAdd() {
		System.out.println("������ȣ�� �Է��� �ּ���.");
		int number = intData("������ȣ:"); 
		System.out.println("�������� �Է��� �ּ���.");
		String name = inData("������:");
		System.out.println("������ �Է��� �ּ���.");
		int sal = intData("����:");
		System.out.println("���ڸ� �Է��� �ּ���.");
		String waiter = inData("����:");
		System.out.println("���ǻ���� �Է��� �ּ���.");
		String publisher = inData("���ǻ�:");
		System.out.println("�������� �Է��� �ּ���.");
		String publish = inData("������:");
		System.out.println("��� �Է��� �ּ���.");
		int books = intData("���:");

		LasDataSet.hs.put(number,new LasVO(number,name,sal, waiter, publisher,publish,books));
	}
// -----------------------------------------------���� �޼ҵ� ----------------------------------------------------------------------	

// -----------------------------------------------�Է� �޼ҵ� ����--------------------------------------------------------		
	//�ܼ� ��Ʈ �Է� �޼ҵ�
	public int intData(String msg) {
		System.out.print(msg);
		return Integer.parseInt(scan.nextLine());
	}

	//�ܼ� ���ڿ� �Է� �޼ҵ�
	public String inData(String msg) {
		System.out.println(msg+"");
		return scan.nextLine();
	}
//-----------------------------------------------�Է� �޼ҵ�--------------------------------------------------------------------	

	
	//���� ���� �޼ҵ�
	public void salSortdown() {
		ArrayList<LasVO> vd = new ArrayList<LasVO>();
		Collection<LasVO> va = LasDataSet.hs.values();
		Iterator<LasVO> ii = va.iterator();
	
		while(ii.hasNext()) {
			vd.add(ii.next());
		}
		
		Collections.sort(vd , new SortSal());
		System.out.println();
		System.out.println("\t\t\t\t�������� ������");
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
		System.out.println("\t\t\t\t��ǰ ���� ������");
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
		System.out.println("\t\t\t\t�������� ������");
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
		System.out.println("\t\t\t\t��ǰ ���� ������");
		System.out.println();
		
		new LasVO().titlePrint1();
		for(LasVO vo : vd) {
			System.out.println(vo.toString());
		}
	}


//----------------------------------------------���� Ŭ����----------------------------------------------------------------------

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

