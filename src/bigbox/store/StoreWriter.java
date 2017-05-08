package bigbox.store;


public interface StoreWriter {
	public abstract boolean addStore(int divID, String storeNumIn, String storeNameIn, String addressIn, String cityIn, String stateIn, String zipIn);
	public abstract boolean deleteStore(int divIdIn, String storeNumIn);
}
