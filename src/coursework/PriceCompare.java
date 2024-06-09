package coursework;

import java.util.Comparator;

public class PriceCompare implements Comparator<Book>{

	@Override
	public int compare(Book b1, Book b2) {
		/* This method compares the price of two book objects 
		 * returning a positive value if the price of the first object is greater than the second
		 * returning a negative value if the price of the second object is greater than the first
		 * or returning a 0 if they are equal */
		
		if (b1.price > b2.price) return 1;
		if (b2.price > b1.price) return -1;
		else return 0;
	}
	
}
