package coursework;

public class Audiobook extends Book {
	/* This is the Audiobook subclass of the Book superclass
	 * it inherits its parent class's methods and attributes.
	 * It overrides the toString method in order to include the two
	 * additional attributes (length and format) */
	
	private double length;
	String format;
	
	Audiobook(int barcode,BookType booktype, String title,Language language,Genre genre,String releasedate,int stock,double price,double length, String format){
		super(barcode, booktype, title, language, genre, releasedate, stock, price);
		this.booktype = BookType.audiobook;
		this.length = length;
		this.format = format;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public String getFormat() {
		return this.format;
	}
	
	@Override
	public String toString() {
		return this.barcode+", "+this.getBookType()+", "+this.title+", "+this.getLanguage()+", "+this.getGenre()+", "+this.releasedate+", "+this.stock+", "+this.price+", "+this.length+", "+this.format+"\n";
	}
	
}
