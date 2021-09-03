package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mypage.dao.MypageDao;
import mypage.model.CancelInfo;
import reserve.dao.TimeSearchDAO;

public class CancelReserveService {

	private MypageDao mypagedao=new MypageDao();
	private TimeSearchDAO timeSearchdao=new TimeSearchDAO();
	
	public void delete(CancelInfo cancelInfo) {
		// TODO Auto-generated method stub
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			int place_id=timeSearchdao.getPlaceId(con, cancelInfo.getPlace_name());
			mypagedao.delete(con,cancelInfo,place_id);
			con.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException();
		}finally
		{
			JdbcUtil.close(con);
		}
	}

}
