package bigbox.db;

import bigbox.store.StoreDAO;
import bigbox.store.StoreDB;
import bigbox.divisions.DivisionDAO;
import bigbox.divisions.DivisionDB;
import bigbox.store_sales.Store_SalesDAO;
import bigbox.store_sales.Store_SalesDB;


public class DAOFactory {
	
	public static StoreDAO createStoreDAO()
	{
		StoreDAO sDAO = new StoreDB();
		return sDAO;
	}
	
	public static DivisionDAO createDivisionsDAO()
	{
		DivisionDAO dDAO = new DivisionDB();
		return dDAO;
	}
	
	public static Store_SalesDAO createStore_SalesDAO()
	{
		Store_SalesDAO ssDAO = new Store_SalesDB();
		return ssDAO;
	}

}
