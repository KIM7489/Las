package LasInfor;

public class LoginVO {
	
	private String username;
	private String userpwed;
	private String useremail;
	private String useradd;
	private String userenum;
	
	
	public void loginPrint() {
		System.out.printf("%s\t %s\t %s\t %s\t %s \n",username,userpwed,useremail,useradd,userenum);
	}
	public static void memberPrint() {
		System.out.printf("%5s\t%7s\t%2s\t%7s\t%12s\t\n","아이디","비밀번호","이메일","주소","등록일");
		System.out.println("=====================================================================================================");
	}
	
	
	public LoginVO(String username,String userpwed, String useremail, String useradd, String userenum){
	this.username = username; this.userpwed = userpwed; this.useremail = useremail; this.useradd = useradd; this.userenum= userenum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwed() {
		return userpwed;
	}
	public void setUserpwed(String userpwed) {
		this.userpwed = userpwed;
	}
	public String getUserEmail() {
		return useremail;
	}
	public void setUserEmail(String useremail) {
		this.useremail = useremail;
	}
	public String getUseradd() {
		return useradd;
	}
	public void setUseradd(String useradd) {
		this.useradd = useradd;
	}
	public String getUserenum() {
		return userenum;
	}
	public void setUsernum(String userenum) {
		this.userenum = userenum;
	}
	
	public LoginVO() {

	}

}
