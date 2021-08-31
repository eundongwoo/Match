package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import notice.dao.NoticeDao;
import notice.model.Notice;

public class ReadNoticeService {
		
	private NoticeDao noticeDao = new NoticeDao();
	
		
	public Notice getNotice(int noticeNum, boolean increaseReadCount) {
		
		try(Connection conn = ConnectionProvider.getConnection()){
			Notice notice = noticeDao.selectById(conn, noticeNum);
			
			if(notice == null) {
				throw new NoticeNotFoundException();
			}

			if(increaseReadCount) {
				noticeDao.increaseReadCount(conn, noticeNum);
			}
			
			return new Notice(notice.getNumber(),notice.getWriter(),
					notice.getTitle(),notice.getRegDate(),
					notice.getModifiedDate(),
					notice.getContent(),notice.getReadCount());
			
		}	catch (SQLException	e) {
			throw new RuntimeException(e);
		}
	}
}
