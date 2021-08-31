package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.service.WriteRequest;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDao;
import notice.model.Notice;

public class WriteNoticeService {
	
private NoticeDao noticeDao=new NoticeDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice Notice = toNotice(req);
			Notice savedNotice = noticeDao.insert(conn, Notice);
			if(savedNotice == null) {
				throw new RuntimeException("fail to insert Notice");
			}

			conn.commit();
			return savedNotice.getNumber();
			
		}catch(SQLException	e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private Notice toNotice(WriteRequest req) {
		Date now = new Date();
		return new Notice(null, req.getWriter(), req.getTitle(), now, now, req.getContent(),0);
	}
}
