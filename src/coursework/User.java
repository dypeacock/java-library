package coursework;

public class User {
	/* Here I have defined the parent class User to the
	 * Customer and Admin subclasses, with two methods : toString and
	 * showAllBooks to be overwritten by the subclasses */
	
	protected int id;
	protected String username;
	protected String surname;
	protected Address address;
	protected UserType usertype;
	
	User(int id,String username,String surname,Address address,UserType usertype){
		this.id = id;
		this.username = username;
		this.surname = surname;
		this.address = address;
		this.usertype = usertype;
	}
	
	public String toString(){
		return this.id+", "+this.username+", "+this.surname+", "+this.getAddress()+", "+this.getUsertype();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getAddress() {
		return this.address.toString();
	}
	
	public String getUsertype() {
		if (this.usertype == UserType.customer) {
			return "customer";
		} else if (this.usertype == UserType.admin) {
			return "admin";
		}
		return "";
	}
	
}
