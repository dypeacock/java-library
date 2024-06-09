package coursework;
import java.io.IOException;

public class Admin extends User {
	/* This is the Adimn subclass of the User superclass
	 * it inherits its parent class's methods and attributes.
	 * It overrides the toString method in order to include the
	 * additional attribute (usertype) and defines a two new methods*/
	
	Admin(int id,String username,String surname,Address address,UserType usertype){
		super(id,username,surname,address,usertype);
		this.usertype = UserType.admin;
	}
	
	@Override
	public String toString(){
		return this.id+", "+this.username+", "+this.surname+", "+this.address.toString()+", , "+this.usertype+"\n";
	}
	
	
	public static void addBookToSystem(Book bk) throws IOException {
		/* Adds a new book to the current book list (stock).
		 * This method receives a Book variable as a parameter 
		 * and returns no output  */
		
		StockClass.writeData(bk);
		
	}
}
