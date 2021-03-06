package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jdbc.JdbcUtil;
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
			int place_id=dao.getPlaceId(con,obj.getPlaceName()); //풋살장 장소 아이디
			List<String> list=dao.getTimeList(con,place_id); //풋살장의 운영 시간
					
			for(String str:list)
			{
				System.out.println("풋살장 시간"+str);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//placeName, date ->searchTimeRequest
	public HashMap<String, Integer> makeMap(SearchTimeRequest searchTimeRequest, HashMap<String, Integer> hm) {
		Connection con=null;
		
		try {
			con= ConnectionProvider.getConnection();
			int place_id=dao.getPlaceId(con, searchTimeRequest.getPlaceName());
			hm=dao.getHashMap(searchTimeRequest, place_id, con, hm);
			return hm;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(con);
		}	
	}
}
