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
import mypage.model.ReserveInfo;

public class MypageDao {

	public List<ReserveInfo> getReserveList(Connection con, User user) throws SQLException {
		PreparedStatement prst=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		String sql="select * from reservation,place where f_id= PLACE_ID and MEMBER_ID=?";
		
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
				String reg_time=format1.format(rs.getTimestamp("REG_TIME"));	
				ReserveInfo reserveInfo=new ReserveInfo(rs.getInt("RESERVE_NUM"),rs.getString("MEMBER_ID"),rs.getString("F_NAME"),rs.getString("RESERVE_DATE"),rs.getString("RESERVE_TIME"),rs.getString("STATE"),reg_time);
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

}
