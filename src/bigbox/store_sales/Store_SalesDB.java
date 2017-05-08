package bigbox.store_sales;
import bigbox.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Store_SalesDB implements Store_SalesDAO {

	@Override
	public double storeSalesTotal(String inStoreNum, int divID) 
	{
		double salesTotal = -0.1;
		String storeSalesTotal = 
				"Select StoreNumber, concat('$', format(sum(sales), 2)) as TOTAL" 
				+ " " + "from stores s, store_sales ss"
				+ " " + "where s.ID = ss.StoreID"
				+ " " + "AND StoreNumber = ?"
				+ " " + "AND DivisionID = ?"
				+ " " + "GROUP BY (StoreNumber)";	
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(storeSalesTotal))
		{
			ps.setString(1, inStoreNum);
			ps.setInt(2, divID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				salesTotal = rs.getDouble(2);							
			}			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
		return salesTotal;
	}

	@Override
	public double divSalesTotal(int divID) 
	{
		double divSalesTotal = -0.1;
		String divisionSalesTotal = 
				"Select DivisionNumber, concat('$', format(sum(sales), 2)) as TOTAL" 
				+ " " + "from stores s, store_sales ss"
				+ " " + "where s.ID = ss.StoreID"
				+ " " + "AND d.ID = s.DivisionID"
				+ " " + "AND DivisionID = ?"
				+ " " + "GROUP BY (DivisionNumber)";	
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(divisionSalesTotal))
		{
			ps.setInt(1, divID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				divSalesTotal = rs.getDouble(2);							
			}			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
		return divSalesTotal;
	}

	
	@Override
	public double storeSalesAnnual(int storeId, int year) 
	{
		double salesTotal = -0.1;
		String annualSalesTotal = "Select StoreID, sum(sales) as TOTAL" 
				+ " " + "from store_sales"
				+ " " + "where StoreID = ?"
				+ " " + "and Year = ?"
				+ " " + "GROUP BY (StoreID)";	
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(annualSalesTotal))
		{
			ps.setInt(1, storeId);
			ps.setInt(2, year);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				salesTotal = rs.getDouble(2);							
			}			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
		return salesTotal;
	}
		
}





