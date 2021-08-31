package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDao;
import notice.model.Notice;

public class DeleteNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	
	public void delete(ModifyRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice = noticeDao.selectById(conn, delReq.getNoticeNumber());
			if(notice == null) {
				throw new NoticeNotFoundException();
			}
			if(!canDelete(delReq.getUserId(), notice)) { //실제db와 , 요청에 의한 사용자
				throw new PermissionDeniedException();
			}
			
			noticeDao.delete(conn, delReq.getNoticeNumber());
			
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canDelete(String userId, Notice notice) {
		return notice.getWriter().getId().equals(userId);
	}
}
