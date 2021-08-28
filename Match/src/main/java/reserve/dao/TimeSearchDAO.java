package reserve.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jdbc.JdbcUtil;
import reserve.model.SearchTimeRequest;

public class TimeSearchDAO {
	
	//해당 풋살장의 아이디를 구하는 쿼리 메서드
	public int getPlaceId(Connection con, String placeName) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement prst=null;
		ResultSet rs=null;
		int id=0;
		String sql="select place_id from place where  PLACE_NAME=?";
		
		try
		{
			prst=con.prepareStatement(sql);
			prst.setString(1, placeName);
			rs=prst.executeQuery();
			
			if(rs.next())
			{
				id=rs.getInt("place_id");
			}
			
			return id;
		}finally
		{
			JdbcUtil.close(rs);
			JdbcUtil.close(prst);
		}
		
	}

	public List<String> getTimeList(Connection con, int place_id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement prst=null;
		ResultSet rs=null;
		List<String> list=null;
		String sql="select OPERATION_TIME from operation where PLACE_ID=? order by id";
		
		try
		{
			prst=con.prepareStatement(sql);
			prst.setInt(1, place_id);
			rs=prst.executeQuery();
			list=new ArrayList<String>();
			while(rs.next())
			{
				list.add(rs.getString("OPERATION_TIME"));
			}
			return list;
		}finally
		{
			JdbcUtil.close(rs);
			JdbcUtil.close(prst);
		}
	}

	public HashMap<String, Integer> getHashMap(SearchTimeRequest searchTimeRequest, int place_id, Connection con, HashMap<String, Integer> hm) throws SQLException {
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="select count(member_id) , reserve_time from reservation where place_id=? and reserve_date =? group by reserve_time";
		
		try {
			prst=con.prepareStatement(sql);
			prst.setInt(1, place_id);
			prst.setString(2, searchTimeRequest.getDate());
			rs=prst.executeQuery();
			
			while(rs.next()) {
				hm.put(rs.getString(2), rs.getInt(1));
				
			}
			return hm;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(prst);
		}
		
	}

}
