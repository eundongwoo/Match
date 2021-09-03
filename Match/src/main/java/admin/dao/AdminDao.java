package admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import article.model.Article;
import jdbc.JdbcUtil;
import map.model.Place;

public class AdminDao {
	
	public int insertPlace(Connection conn, Place place) throws SQLException {
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2=null;
		ResultSet rs = null;
		int place_id = 0;
		try {
			pstmt = conn.prepareStatement("insert into place values(place_num.NEXTVAL,?,?,?) ");
			pstmt.setString(1, place.getF_name());
			pstmt.setString(2, place.getF_addr());
			pstmt.setString(3, place.getF_tel());
			
			pstmt.executeUpdate();
						
			pstmt2 = conn.prepareStatement("select * from place where place_addr=?");
			pstmt2.setString(1, place.getF_addr());
			rs=pstmt2.executeQuery();
			if(rs.next()) {
				place_id = rs.getInt(1);
				
			}
			return place_id;	//place_id반환
			
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(pstmt2);
			
		}	
		
	}
	
	public void insertTime(Connection conn, int rowNum, int place_id, HashMap<Integer, String> hm) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("insert into operation values(operation_num.NEXTVAL,?,?)");
			pstmt.setInt(1, place_id);
			pstmt.setString(2, hm.get(rowNum));
			pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
}	
