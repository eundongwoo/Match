package map.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import map.model.Place;

public class MapDao {

	public List<Place> getPlaceInfo(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
		String sql="select * from place";
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(sql);
			List<Place> list=new ArrayList<Place>();
			System.out.println(rs);
			while(rs.next())
			{	
				Place place=new Place(rs.getInt("place_id"), rs.getString("place_name"), rs.getString("place_addr"), rs.getString("place_tel"));
				list.add(place);
			}
			
			return list;
			
		}finally
		{
			JdbcUtil.close(rs);
			JdbcUtil.close(st);
		}
	}

}
