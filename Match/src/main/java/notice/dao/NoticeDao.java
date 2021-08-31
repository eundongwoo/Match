package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import notice.model.Notice;
import jdbc.JdbcUtil;

public class NoticeDao {
	public Notice insert(Connection conn, Notice notice) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into notice values ((select nvl(max(notice_no), 0)+1 from notice),?,?,?,?,?,?,0)");
			pstmt.setString(1, notice.getWriter().getId());
			pstmt.setString(2, notice.getWriter().getName());
			pstmt.setString(3, notice.getTitle());
			pstmt.setTimestamp(4, toTimestamp(notice.getRegDate()));                                    
			pstmt.setTimestamp(5, toTimestamp(notice.getModifiedDate()));
			pstmt.setString(6, notice.getContent());
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount >0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select max(notice_no) from notice");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Notice(newNum, notice.getWriter(), notice.getTitle(),
							notice.getRegDate(), notice.getModifiedDate(), notice.getContent(),0 );
				}
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
			
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from notice");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<Notice> select(Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select rownum as rnum, notice_no, member_id, member_name, title, regdate, moddate, content, read_cnt "
					+"from (select * from notice order by notice_no desc) where rownum <= ?) where rnum >= ?");

			pstmt.setInt(1, startRow+size);
			pstmt.setInt(2, startRow+1);
			rs = pstmt.executeQuery();
			List<Notice> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertNotice(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Notice convertNotice(ResultSet rs) throws SQLException{
		return new Notice(rs.getInt("notice_no"),
				new Writer(
						rs.getString("member_id"),
						rs.getString("member_name")),
				rs.getString("title"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getString("content"),
				rs.getInt("read_cnt")
				);
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	public Notice selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			pstmt = conn.prepareStatement("select * from notice where notice_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery(); //쿼리수행
			Notice notice = null;
			if(rs.next()) {
				notice = convertNotice(rs);
			}
			return notice;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException	{
		try(PreparedStatement pstmt =conn.prepareStatement("update notice set read_cnt=read_cnt+1 where notice_no = ?")){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String title, String content) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("update notice set title = ?, moddate =systimestamp, content= ? where notice_no = ?")){
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, no);
			return pstmt.executeUpdate();
		}
	}
	
	public void delete(Connection conn, int no) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("delete from notice where notice_no = ?")){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			throw e;
		}
	}
	
	
}
