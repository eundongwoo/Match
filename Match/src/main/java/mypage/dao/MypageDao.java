package mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdbc.JdbcUtil;
import member.service.User;
import mypage.model.CancelInfo;
import mypage.model.ReserveInfo;

public class MypageDao {

	public List<ReserveInfo> getReserveList(Connection con, User user) throws SQLException {
		PreparedStatement prst=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		String sql="SELECT * FROM reservation r,place p  WHERE r.place_id=p.place_id  and member_id=?";
		
		SimpleDateFormat format1=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		String placeName=null;
		try
		{
			prst=con.prepareStatement(sql);
			prst.setString(1, user.getId());
			rs=prst.executeQuery();
			List<ReserveInfo> list=new ArrayList<ReserveInfo>();
			while(rs.next())
			{
				String reg_time=format1.format(rs.getTimestamp("reg_time"));	
				ReserveInfo reserveInfo=new ReserveInfo(rs.getInt("RESERVE_NUM"),rs.getString("MEMBER_ID"),rs.getString("PLACE_NAME"),rs.getString("RESERVE_DATE"),rs.getString("RESERVE_TIME"),rs.getString("STATE"),reg_time);
				list.add(reserveInfo);
			}
			return list;
		}finally
		{
			JdbcUtil.close(prst);
			JdbcUtil.close(rs);
			JdbcUtil.close(con);
		}
	}

	public void delete(Connection con, CancelInfo cancelInfo,int place_id) throws SQLException {
		PreparedStatement prst=null;
		String sql="delete from reservation where member_id=? and place_id=? and reserve_date=? and reserve_time=?";
		
		try
		{
			prst=con.prepareStatement(sql);
			prst.setString(1, cancelInfo.getUser().getId());
			prst.setInt(2, place_id);
			prst.setString(3, cancelInfo.getReserve_date());
			prst.setString(4, cancelInfo.getReserve_tiem());
			prst.executeUpdate();
			
		}finally
		{
			JdbcUtil.close(prst);
		}
	}

}
