package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;

public class PwdModifyService {

	private MemberDao dao=new MemberDao();
	
	public void changePassword(String id, String newPwd) {
		// TODO Auto-generated method stub
		Connection con=null;
		try
		{
			con=ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member member=dao.selectById(con, id);
			if(member==null)
			{
				throw new MemberNotFoundException();
			}
			
			member.changePassword(newPwd);
			dao.update(con,member);
			con.commit();
		}catch (SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException();
		}finally
		{
			JdbcUtil.close(con);
		}
		
	}

}
