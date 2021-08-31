package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDao;
import notice.model.Notice;

public class ModifyNoticeService {
private NoticeDao noticeDao = new NoticeDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice = noticeDao.selectById(conn, modReq.getNoticeNumber());
			if(notice == null) {
				throw new NoticeNotFoundException();
			}
			if(!canModify(modReq.getUserId(), notice)) { 
				throw new PermissionDeniedException();
			}
			noticeDao.update(conn, modReq.getNoticeNumber(), modReq.getTitle(), modReq.getContent());
			conn.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canModify(String modyfyingUserId, Notice notice) {  //방금요청한 것과 db에있는 것.
		return notice.getWriter().getId().equals(modyfyingUserId);
	}
}
