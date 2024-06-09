package coursework;

public class Address {
	/* This class is used to implement the address attribute
	 * of the User (Customer and Admin) classes. */
	
	int housenum;
	String postcode;
	String city;
	
	Address(int housenum,String postcode,String city){
		this.housenum = housenum;
		this.postcode = postcode;
		this.city = city;
	}
	
	public String toString() {
		return this.housenum+", "+this.postcode+", "+this.city;
	}
}
