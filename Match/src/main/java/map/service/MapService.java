package map.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import map.dao.MapDao;
import map.model.Place;

public class MapService {

	private MapDao dao=new MapDao();
	
	public List<Place> initMap()
	{
		Connection con=null;
		
		try {
			con=ConnectionProvider.getConnection();
			List<Place> list=dao.getPlaceInfo(con);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally
		{
			JdbcUtil.close(con);
		}
	}
	
	
}
