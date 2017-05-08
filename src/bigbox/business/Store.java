package bigbox.business;



public class Store extends Facility {
	
	private String storeNumber;
	private String divNumber;
	
	
	public Store() {
		super();
		storeNumber = "";
		divNumber = "";		
	}
	

	public Store(int idIn, String divNumberIn, String storeNumberIn, String nameIn, String addressIn, String cityIn, String stateIn, String zipcodeIn) 
    { 
        super(idIn, nameIn, addressIn, cityIn, stateIn, zipcodeIn);
        this.setStoreNumber(storeNumberIn); 
        this.setDivNumber(divNumberIn); 
         
                 
    } 
	
	@Override
	public String toString()
	{
		return ("Store# " + storeNumber + ", Division# " + divNumber + " ") + super.toString();
	}
	

	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getDivNumber() {
		return divNumber;
	}

	public void setDivNumber(String divNumber) {
		this.divNumber = divNumber;
	}

	

	
	
	

	
}
