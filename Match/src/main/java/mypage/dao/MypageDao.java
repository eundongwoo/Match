package mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jdbc.JdbcUtil;
import member.service.User;
import mypage.model.CancelInfo;
import mypage.model.ReserveInfo;
import reserve.model.ReserveRequest;

public class MypageDao {
	//인자의 state로 바꿔준다.
	
	
	public void updateState(Connection conn, int place_id, ReserveRequest reserveRequest, User user) throws SQLException {
		PreparedStatement prst=null;
		String sql="update reservation set STATE=? where PLACE_ID=? and RESERVE_DATE=? and RESERVE_TIME=?";
		
		try
		{
			prst=conn.prepareStatement(sql);
			prst.setString(1, "매칭실패");
			prst.setInt(2, place_id);
			prst.setString(3, reserveRequest.getDate());
			prst.setString(4, reserveRequest.getTime());
			prst.executeUpdate();
		}finally
		{
			JdbcUtil.close(prst);
		}
		
	}
	
	
	public List<ReserveInfo> getReserveList(Connection con, User user) throws SQLException, ParseException {
		PreparedStatement prst=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		String sql="SELECT * FROM reservation r,place p  WHERE r.place_id=p.place_id  and member_id=? order by reserve_num";
		
		SimpleDateFormat format1=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
		String placeName=null;
		Calendar cal=Calendar.getInstance();	//이 시점의 달력
		
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1; 
		int date = cal.get(Calendar.DATE);
		 
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy년MM월dd일");
		String nowDateString = year+"년"+month+"월"+date+"일";
		Date nowDate= new Date(sdf.parse(nowDateString).getTime());
		
		try
		{
			prst=con.prepareStatement(sql);
			prst.setString(1, user.getId());
			rs=prst.executeQuery();
			List<ReserveInfo> list=new ArrayList<ReserveInfo>();
			while(rs.next())
			{	
				Date reserveDate = new Date(sdf.parse(rs.getString("RESERVE_DATE")).getTime());
				//nowDate가 reserveDate보다 나중after이면 속성변경해줘야됨
				//after는 두개 날짜 같으면 false반환
				boolean a = nowDate.after(reserveDate);	//a가 true이면 매칭실패로 state변경해줘야됨.
				String reg_time=format1.format(rs.getTimestamp("reg_time"));
				String state = rs.getString("STATE");
				ReserveRequest resereveRequest = new ReserveRequest(user, rs.getString("PLACE_NAME"),rs.getString("RESERVE_DATE"), rs.getString("RESERVE_TIME"));
				if(a==true) {
					if(state.equals("예약대기")) {
						state="매칭실패";
						updateState(con, rs.getInt("place_id"), resereveRequest, user);	//db state속성 변경해준다.
					}
				} 
				
				ReserveInfo reserveInfo=new ReserveInfo(rs.getInt("RESERVE_NUM"),rs.getString("MEMBER_ID"),rs.getString("PLACE_NAME"),rs.getString("RESERVE_DATE"),rs.getString("RESERVE_TIME"), state, reg_time);
				
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
