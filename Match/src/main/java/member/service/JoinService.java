package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {

	private MemberDao dao=new MemberDao();
	
	public void join(JoinRequest joinRequest) {
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member member=dao.selectById(con, joinRequest.getId());
			if(member!=null)
			{
				JdbcUtil.rollback(con);
				throw new DuplicateIdException();
			}
			
			dao.insert(con,new Member(joinRequest.getId(), joinRequest.getName(),joinRequest.getPassword(),joinRequest.getTel()));
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
