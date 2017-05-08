package bigbox.business;

public class Facility {
	// the instance variables
	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	
	// Facility constructors
	public Facility()
	{
		id = 0;
		name = "";
		address = "";
		city = "";
		state = "";
		zipcode = "";
	}
	public Facility(int idIn, String nameIn, String addressIn, String cityIn, String stateIn, String zipcodeIn)
	{
		this.setId(idIn);
		this.setName(nameIn);
		this.setAddress(addressIn);
		this.setCity(cityIn);
		this.setState(stateIn);
		this.setZipcode(zipcodeIn);
	}
	
	@Override
	public String toString()
	{
		return ("id:" + id + ", Name: " + name + ", Address: " + address + ", City: " + city + ", State: " + state + ", Zipcode: " + zipcode + "\n");
	}
	
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	// id of -1 indicates this is a new store and this needs to be populated---some special additions to code can be implemented
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
