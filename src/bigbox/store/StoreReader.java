package bigbox.store;

import bigbox.business.Store;

import java.util.ArrayList;



public interface StoreReader {
	//public abstract Store getStoreByDivisionAndStoreNumber(String inDiv, String inStoreNbr);
	
	public abstract ArrayList<Store> getAllStores();
	
	public abstract ArrayList<Store> getAllStoresByDivision(int divId);
	
	public abstract int getStoreId(String storeNum, int divId);
	

}
