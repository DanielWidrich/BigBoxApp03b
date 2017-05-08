package bigbox.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bigbox.business.Store;
import bigbox.db.DBUtil;


public class StoreDB implements StoreDAO {
	
	@Override	
	public ArrayList<Store> getAllStores()
	{
		ArrayList<Store> storeArray = new ArrayList<Store>();
		String returnAllStores = "SELECT s.ID, s.Name, s.Address, s.City, S.State, s.ZipCode, StoreNumber, DivisionNumber"
				+ " " + "FROM stores s, divisions d"
				+ " " + "where s.DivisionID = d.ID"
				+ " " + "order by DivisionNumber;";
				
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(returnAllStores);
				ResultSet rs = ps.executeQuery())
		{
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String city = rs.getString(4);
				String state = rs.getString(5);
				String zip = rs.getString(6);
				String storeNum = rs.getString(7);
				String divNum = rs.getString(8);
				Store s = new Store(id, divNum, storeNum, name, address, city, state, zip);
				storeArray.add(s);				
			}
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return storeArray;		
	}

	@Override
	public ArrayList<Store> getAllStoresByDivision(int inDiv) {
		
		ArrayList<Store> storeArray = new ArrayList<Store>();
		String returnStoresByDiv = "SELECT s.ID, s.Name, s.Address, s.City, s.State, s.ZipCode, s.StoreNumber, d.DivisionNumber"
				+ " " + "FROM stores s, divisions d"
				+ " " + "WHERE s.DivisionID = d.ID"
				+ " " + "AND s.DivisionID = ?"
				+ " " + "order by s.ID;";
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(returnStoresByDiv))
		{
			ps.setInt(1, inDiv);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String city = rs.getString(4);
				String state = rs.getString(5);
				String zip = rs.getString(6);
				String storeNum = rs.getString(7);
				String divNum = rs.getString(8);
				Store s = new Store(id, divNum, storeNum, name, address, city, state, zip);
				storeArray.add(s);				
			}
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return storeArray;		
	}
	
	@Override
	public boolean addStore(int divisionID, String storeNum, String storeName, String storeAddress, String storeCity, String storeState, String storeZip) 
	{
		
		String addSQL = "INSERT INTO stores (DivisionID, StoreNumber, Name, Address, City, State, ZipCode) " +
		"Values(?, ?, ?, ?, ?, ?, ?)";
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(addSQL))
		{
			ps.setInt(1, divisionID);
			ps.setString(2, storeNum);			
			ps.setString(3, storeName);
			ps.setString(4, storeAddress);
			ps.setString(5, storeCity);
			ps.setString(6, storeState);
			ps.setString(7, storeZip);			
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteStore(int divIn, String storeIn) {
		int count;
		String deleteSQL = "DELETE FROM stores WHERE" 				
				+ " " + "DivisionID = ?"
				+ " " + "and StoreNumber = ?";
		System.out.println("delete sql = "+deleteSQL);
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(deleteSQL))
		{
			ps.setInt(1, divIn);
			ps.setString(2, storeIn);
			count = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		if(count == 1)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}

	@Override
	public int getStoreId(String storeNum, int divId) 
	{
		int storeId = -1;
		String returnStoresByDiv = "SELECT ID"
				+ " " + "FROM stores"
				+ " " + "WHERE DivisionID = ?"	
				+ " " + "AND StoreNumber= ?";	
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(returnStoresByDiv))
		{
			ps.setInt(1, divId);
			ps.setString(2, storeNum);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				storeId = rs.getInt(1);							
			}			
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}		
		return storeId;		
	}	
}





