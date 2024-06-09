package coursework;
import java.util.Comparator;

public class QuantityCompare  implements Comparator<Book>{

	public int compare(Book b1, Book b2) {
		/* This method compares the stock of two book objects 
		 * returning a positive value if the stock of the first object is greater than the second
		 * returning a negative value if the stock of the second object is greater than the first
		 * or returning a 0 if they are equal */
		
		if (b1.stock > b2.stock) return 1;
		if (b2.stock > b1.stock) return -1;
		else return 0;
	}
}
