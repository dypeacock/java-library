package coursework;

import java.util.Comparator;

public class LengthCompare implements Comparator<Audiobook>{

	@Override
	public int compare(Audiobook b1, Audiobook b2) {
		/* This method compares the length of two Audiobook objects 
		 * returning a positive value if the length of the first object is greater than the second
		 * returning a negative value if the length of the second object is greater than the first
		 * or returning a 0 if they are equal */
		
		if (b1.getLength() > b2.getLength()) return 1;
		if (b2.getLength() > b1.getLength()) return -1;
		else return 0;
	}

}
