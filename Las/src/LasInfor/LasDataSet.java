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
	hs.put(1, new LasVO(001,"���ΰ� �л�",42000,"��Ͻ�Ʈ ��ֿ���", "������", "2012-01-02",5));
	hs.put(2, new LasVO(002,"������ �ʻ�",11700,"���ν�Ʈ ����ƾ��", "������", "2012-10-26",2));
	hs.put(3, new LasVO(003,"������ ���",32000,"����Ʈ�� ��������", "������", "2003-09-20",3));
	hs.put(4, new LasVO(004,"���ΰ� �ٴ�",22000,"�������� �츣�޽�", "������", "2012-01-02",4));
	hs.put(5, new LasVO(005,"���ΰ� �ٴ�",43200,"�ŵ���� ��������", "������", "2012-01-02",5));
	hs.put(6, new LasVO(006,"����� ����",43200,"���źҰ� ��������", "���ź�", "2012-01-02",5));
	}
	
	public static void basicMy() {
	
	
	}


}
