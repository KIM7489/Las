package LasInfor;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Test {
	Scanner scan = new Scanner(System.in);
	
	public void start() {
							String name = idCheck();
							System.out.println(name);
	}
	
	public Test() {

	}
	
	
	
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
	
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Test cs = new Test();
		cs.start();
	
	}

	
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


}
