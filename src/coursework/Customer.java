package coursework;
import java.io.IOException;

public class Customer extends User{
	/* This is the Customer subclass of the User superclass
	 * it inherits its parent class's methods and attributes.
	 * It overrides the toString method in order to include the two
	 * additional attributes (credit and usertype) */
	
	private double credit;
	private ShoppingBasket basket;
	
	Customer(int id,String username,String surname,Address address,double credit,UserType usertype, ShoppingBasket basket){
		super(id,username,surname,address,usertype);
		this.credit = credit;
		this.usertype = UserType.customer;
		this.basket = basket;
	}
	
	@Override
	public String toString(){
		return this.id+", "+this.username+", "+this.surname+", "+this.address.toString()+", "+this.credit+", "+this.usertype+"\n";
	}
	
	
	public double getCredit() {
		return this.credit;
	}
	
	public void setCredit(double cr) {
		this.credit = cr;
	}
	
	public void addCredit(double amount) throws IOException {
		this.credit += amount;
		AccountsClass.updateCredit(this);
	}
	
	public ShoppingBasket getBasket() {
		return this.basket;
	}
	
	public void addToBasket(int barcode) throws IOException {
		/* This method adds the book with a corresponding barcode to the
		 * Customer's shopping basket */
		
		this.basket.addToBakset(barcode);
	}
	
	public void emptyBasket() throws IOException {
		/* This method removes all books from the Customer's
		 * shopping basket */
		
		this.basket.emptyBasket();
	}
	
	public String checkOut() throws IOException {
		/* This method allows the Customer to pay for all items in the basket
		 *  using their existing credit. A successful payment updates the stock levels,
		 *  credit balance, empties out the shopping basket, and creates a 
		 *  customer receipt which is displayed on the screen as below:
		 *  "Thank you for the purchase! £[paidAmount] paid and your remaining 
		 *  credit balance is £[remainingCredit]. Your delivery address is 
		 *  [fullAddress]." */
		
		double paidAmount = this.basket.getPrice();
		this.credit -= paidAmount;
		this.basket.checkoutBasket();
		AccountsClass.updateCredit(this);
		return "Thank you for the purchase!\n £"+ paidAmount +" paid and your remaining credit balance is £"+ this.credit +".\n Your delivery address is "+this.address.toString();
		
	}
	
}
