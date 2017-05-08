package bigbox.divisions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bigbox.db.DBUtil;

public class DivisionDB implements DivisionDAO {
	
	@Override	
	public void getAllDivisions()
	{
		System.out.println();
		String returnDivisions = "SELECT * FROM divisions";
				
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(returnDivisions);
				ResultSet rs = ps.executeQuery())
		{
			while(rs.next())
			{
				System.out.println("ID: " + rs.getInt("ID") + 
			"\tDivision Number: " + rs.getString("DivisionNumber") +
			"\t" + rs.getString("Name") + 
			"\t" + rs.getString("Address") +
			"\t" + rs.getString("City") + 
			"\t" + rs.getString("State") +
			"\t" + rs.getString("ZipCode"));								
			}
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public int getDivisionID(String divNumIn) {
		int divID = -1;
		String returnStoresByDiv = "SELECT ID"
				+ " " + "FROM divisions"
				+ " " + "WHERE DivisionNumber = ?";		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(returnStoresByDiv))
		{
			ps.setString(1, divNumIn);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				divID = rs.getInt(1);							
			}			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
		return divID;
	}	
}





