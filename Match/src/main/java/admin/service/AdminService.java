package admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import admin.dao.AdminDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import map.model.Place;

public class AdminService {
	
	public void addPlace(Place place, int rowNum, HashMap<Integer, String> hm) {
			
			Connection conn=null;
			try {
				conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				AdminDao adminDao = new AdminDao();
				int place_id = adminDao.insertPlace(conn, place);
				System.out.println("plad_ID"+place_id);
				System.out.println("addPlace종료");
				
			
		
				for(int i=1; i<=rowNum; i++) {
				adminDao.insertTime(conn, i, place_id, hm);	//시간대 넣기
				}
				conn.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					JdbcUtil.close(conn);
				}
			
	}
}
