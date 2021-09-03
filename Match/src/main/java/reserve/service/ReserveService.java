package reserve.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import reserve.dao.ReserveDao;
import reserve.model.ReserveRequest;

public class ReserveService {
	ReserveDao reserveDao = new ReserveDao();
	Connection conn = null;

	public String reserve(ReserveRequest reserveRequest,HttpServletRequest request) throws SQLException {
		
		String message=null;
		
		try {
			conn= ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int place_id = reserveDao.selectF_id(conn, reserveRequest);		//int place_id
			int preCount=reserveDao.getReserveCount(conn,place_id,reserveRequest);
			String checkUser=reserveDao.checkDoubleUser(conn,reserveRequest,place_id); // 한 사람이 똑같은 장소 똑같은 시간 날짜에 중복 예약 불가능하게.
			
			if(checkUser!=null)
			{
				message="이미 예약 되었습니다.";
			}
			else if(preCount<=2)
			{
				
				reserveDao.insert(conn, place_id, reserveRequest);		//예약테이블에 값 삽입
				request.setAttribute("info", "예약되었습니다.");
				//예약되었습니다 메세지
				message="예약되었습니다.";
				//예약확정시켜주기
				int postCount=reserveDao.getReserveCount(conn,place_id,reserveRequest);
				if(postCount==3)
				{
					reserveDao.confirm(conn,place_id,reserveRequest);
					//예약확정되었다는 메세지
					message="예약이 확정되었습니다.";
				}
				
			}else
			{
				//예약인원이 다찼으면 어떻게? 해당시간에 예약이 불가능하다는 메세지
				message="예약이 불가능 합니다.";
			}
			conn.commit();
			return message;
		} catch (SQLException e) {
			conn.rollback();
		} finally {
			JdbcUtil.close(conn);
		}	
		return null;
	}
}
