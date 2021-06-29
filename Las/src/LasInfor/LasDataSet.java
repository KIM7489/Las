package LasInfor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LasDataSet {
	
	public static HashMap<Integer, LasVO> hs = new HashMap<Integer,LasVO>();
	public static HashMap<Integer, LasVO> hs2 = new HashMap<Integer,LasVO>();
	public LasDataSet() {

	}
	
	public static void basicLasSet() {
	hs.put(1, new LasVO(001,"노인과 학생",42000,"어니스트 헤밍웨이", "민음사", "2012-01-02",5));
	hs.put(2, new LasVO(002,"여인의 초상",11700,"제인스트 오스틴스", "민음사", "2012-10-26",2));
	hs.put(3, new LasVO(003,"오만과 편견",32000,"제인트시 오베르스", "민음사", "2003-09-20",3));
	hs.put(4, new LasVO(004,"노인과 바다",22000,"에스오드 헤르메스", "민음사", "2012-01-02",4));
	hs.put(5, new LasVO(005,"노인과 바다",43200,"신드라이 럭스리이", "민음사", "2012-01-02",5));
	hs.put(6, new LasVO(006,"참고용 도서",43200,"구매불가 도서조심", "구매불", "2012-01-02",5));
	}
	
	public static void basicMy() {
	
	
	}


}
