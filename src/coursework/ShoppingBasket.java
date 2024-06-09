package coursework;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
	
	private List<Book> items;
	private double price;
	
	public ShoppingBasket(List<Book> items,double price) {
		this.items = items;
		this.price = price;
	}
	
	public List<Book> getItems(){
		return this.items;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void addToBakset(int barcode) throws IOException {
		/* This method takes a barcode, and uses the getBarcode method to get the
		 * corresponding book object and appends it to the shopping basket list (items). */
		
		Book book = StockClass.getBarcode(barcode);
	
		this.items.add(book);
		this.price += book.price;
		
	}
	
	public String viewBasket() {
		/* This method takes no parameters and returns a string corresponding to the List
		 * of Books in the shopping basket  */
		
		String basket = "";
		
		for (Book book : this.items) {
			if (book instanceof Paperback) {
				Paperback paper = (Paperback) book;
				basket += paper.toString();
				
			} else if (book instanceof Ebook) {
				Ebook ebook = (Ebook) book;
				basket += ebook.toString();
				
			} else if (book instanceof Audiobook) {
				Audiobook abook = (Audiobook) book;
				basket += abook.toString();
			}
		}
		return basket;
	}
	
	public void emptyBasket() throws IOException {
		/* This method takes no parameters and empties the basket of all Books */
		
		List<Book> emptyList = new ArrayList <>();
		this.items = emptyList;
		this.price = 0.0;
	}
	
	public void checkoutBasket() throws IOException {
		/* This method takes no parameters and empties the basket of all Books,
		 * and adjusts their stock levels accordingly */
		
		for (Book book : this.items) {
			StockClass.removeStock(book.barcode);
		}
		List<Book> emptyList = new ArrayList <>();
		this.items = emptyList;
		this.price = 0.0;
	}
	
	
	
	
}
