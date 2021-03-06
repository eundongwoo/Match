package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.service.User;
import mypage.dao.MypageDao;
import mypage.model.ReserveInfo;

public class ListReserveService {

	private MypageDao dao=new MypageDao();

	public List<ReserveInfo> getReserveList(User user) throws ParseException {
		// TODO Auto-generated method stub
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			List<ReserveInfo> list=dao.getReserveList(con,user);			
			return list;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally
		{
			JdbcUtil.close(con);
		}
	}
}
