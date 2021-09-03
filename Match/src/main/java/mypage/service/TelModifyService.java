package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.service.MemberNotFoundException;

public class TelModifyService {

	private MemberDao dao=new MemberDao();
	
	public void changeTel(String id, String newTel) {
		// TODO Auto-generated method stub
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member member=dao.selectById(con, id);
			if(member==null)
			{
				throw new MemberNotFoundException();
			}
			
			member.changeTel(newTel);
			dao.updateTel(con,member);
			con.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		}finally
		{
			JdbcUtil.close(con);
		}	
	}
}
