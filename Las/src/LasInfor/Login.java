package LasInfor;
import java.util.HashMap;
import java.util.Set;

public class Login {
	public static HashMap<String , String> map = new HashMap<String , String>();
	private final String USERID =  "admin";
	private final String USERPWD = "1234";

	public Login() {
	}
	
	public static void userIdlist() {
		map.put("kimcha","1234" );
		
	}
	
	public int loginCheck(String userid, String userpwd) {
		
		if(userpwd.equals(map.get(userid))){		
			return 1;	
		} else if(map.containsKey(userid)) {
			return 2;
		} else if(userid.equals(USERID)&&userpwd.equals(USERPWD)){
			return 3;
		} else {
			return 4;
		}
			
		} 

}

