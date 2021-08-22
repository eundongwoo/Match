package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reserve.dao.ReserveDao;
import reserve.model.ReserveRequest;

public class ReserveService {
	ReserveDao reserveDao = new ReserveDao();
	Connection conn = null;
	public void reserve(ReserveRequest reserveRequest) throws SQLException {
		
		try {
			conn= ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int place_id = reserveDao.selectF_id(conn, reserveRequest);		//int place_id
			System.out.println("여기2");
			System.out.println("여기2 place_id"+place_id);
			System.out.println(reserveRequest.getDate());
			System.out.println("userid"+reserveRequest.getUser().getId());
			reserveDao.insert(conn, place_id, reserveRequest);		//예약테이블에 값 삽입
			System.out.println("여기3");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("여기 rollback");
			conn.rollback();
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	
	
}
