package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import reserve.dao.TimeSearchDAO;
import reserve.model.SearchTimeRequest;

public class TimeSearchService {

	private TimeSearchDAO dao=new TimeSearchDAO();

	// 해당 풋살장의 시간들을 가져오는 메서드
	public List<String> getTimeList(SearchTimeRequest obj) {
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			int place_id=dao.getPlaceId(con,obj.getPlaceName());
		//	System.out.println("아아아아악:"+place_id);
			
			List<String> list=dao.getTimeList(con,place_id);
			
//			for(String str:list)
//			{
//				System.out.println("풋살장 시간"+str);
//			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
