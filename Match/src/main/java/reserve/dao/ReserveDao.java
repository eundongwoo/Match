package reserve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import reserve.model.ReserveRequest;

public class ReserveDao {
	
	//(풋살장이름으로 부터 풋살장id가져오는 메소드)
	public int selectF_id(Connection conn, ReserveRequest reserveRequest) throws SQLException {
		String place = reserveRequest.getPlace();	//풋살장 이름
		int place_id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select place_id from place where place_name= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				place_id = rs.getInt(1);		//f_id(풋살장id)반환
			}
			return place_id;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	public void insert(Connection conn, int place_id, ReserveRequest reserveRequest) {
		PreparedStatement pstmt = null;
		String date = reserveRequest.getDate();
		String time = reserveRequest.getTime();
		String member_id = reserveRequest.getUser().getId();
		
		String sql = "insert into reservation(RESERVE_NUM,MEMBER_ID,PLACE_ID,RESERVE_DATE,RESERVE_TIME) values(reserve_num.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, place_id);
			pstmt.setString(3, date);
			pstmt.setString(4, time);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
	}

	// 매칭하기 위한 insert 하려는 정보가 예약테이블에 몇개 있는지
	public int getReserveCount(Connection conn, int place_id, ReserveRequest reserveRequest) throws SQLException {
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="select count(*) from reservation where PLACE_ID=? and RESERVE_DATE=? and RESERVE_TIME=?";
		int count=0;
		try
		{
			prst=conn.prepareStatement(sql);
			prst.setInt(1, place_id);
			prst.setString(2, reserveRequest.getDate());
			prst.setString(3, reserveRequest.getTime());
			rs=prst.executeQuery();
			if(rs.next())
			{
				count=rs.getInt(1);
			}
			return count;
		}finally
		{
			JdbcUtil.close(rs);
			JdbcUtil.close(prst);
		}
	}

	public void confirm(Connection conn, int place_id, ReserveRequest reserveRequest) throws SQLException {
		PreparedStatement prst=null;
		String sql="update reservation set STATE=? where PLACE_ID=? and RESERVE_DATE=? and RESERVE_TIME=?";
		
		try
		{
			prst=conn.prepareStatement(sql);
			prst.setString(1, "예약확정");
			prst.setInt(2, place_id);
			prst.setString(3, reserveRequest.getDate());
			prst.setString(4, reserveRequest.getTime());
			prst.executeUpdate();
		}finally
		{
			JdbcUtil.close(prst);
		}
		
	}

	public String checkDoubleUser(Connection conn, ReserveRequest reserveRequest, int place_id) throws SQLException {
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="SELECT member_id FROM reservation WHERE place_id=? and reserve_date=? and reserve_time=? and member_id=?";
		String result=null;
		try
		{
			prst=conn.prepareStatement(sql);
			prst.setInt(1, place_id);
			prst.setString(2, reserveRequest.getDate());
			prst.setString(3, reserveRequest.getTime());
			prst.setString(4, reserveRequest.getUser().getId());
			rs=prst.executeQuery();
			
			if(rs.next())
			{
				result=rs.getString("member_id");
			}
			
			return result;
		}finally
		{
			JdbcUtil.close(rs);
			JdbcUtil.close(prst);
		}
		
		
	}

}
