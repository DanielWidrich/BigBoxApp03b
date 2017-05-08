package bigbox.store_sales;

public interface Store_SalesReader {
		
	public double storeSalesTotal(String inStoreNum, int divID);
	
	public double storeSalesAnnual(int storeId, int year);
	
	public double divSalesTotal(int divID);

}
