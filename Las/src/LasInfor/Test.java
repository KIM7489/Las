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
	
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Test cs = new Test();
		cs.start();
	
	}

	
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


}
