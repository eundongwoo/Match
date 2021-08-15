package member.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection con,String id) throws SQLException
	{
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="select * from member where id=?";
		
		try
		{
			prst=con.prepareStatement(sql);
			prst.setString(1, id);
			rs=	prst.executeQuery();
			Member member=null;
			if(rs.next())
			{
				// 멤버객체 생성
			}
			return member;
		}finally
		{
			JdbcUtil.close(rs);
			JdbcUtil.close(prst);
		}
		
	}
	
	private Date toDate(Timestamp date)
	{
		return date==null? null:new Date(date.getTime());
	}
	
//	public void insert(Connection con, Member member) throws SQLException
//	{
//		String sql="insert into member values(?,?,?,?)";
//		
//		try(PreparedStatement prst=con.prepareStatement(sql))
//		{
//			prst.setString(1, member.getId());
//			prst.setString(2, member.getName());
//			prst.setString(3, member.getPwd());
//			prst.setTimestamp(4, new Timestamp(member.getRegDate().getTime()));
//			prst.executeUpdate();
//		}
//		
//	}
	
}
