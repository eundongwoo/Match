package reserve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import reserve.model.ReserveRequest;

public class ReserveDao {
	
	//(풋살장이름으로 부터 풋살장id가져오는 메소드)
	public int selectF_id(Connection conn, ReserveRequest reserveRequest) throws SQLException {
		String place = reserveRequest.getPlace();	//풋살장 이름
		int place_id = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("여기1");
		String sql = "select f_id from place where f_name= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, place);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				place_id = rs.getInt(1);		//f_id(풋살장id)반환
			}System.out.println("place_id"+place_id);
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
		System.out.println("=>>>>>>>"+member_id);
		
		String sql = "insert into reservation values(reserve_num.NEXTVAL, ?, ?, ?, ?)";
		
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

}
