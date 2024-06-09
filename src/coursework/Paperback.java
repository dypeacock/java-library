package coursework;

public class Paperback extends Book{
	/* This is the Paperback subclass of the Book superclass
	 * it inherits its parent class's methods and attributes.
	 * It overrides the toString method in order to include the two
	 * additional attributes (pages and condition) */
	
	private int pages;
	private String condition;
	
	Paperback(int barcode,BookType booktype, String title,Language language,Genre genre,String releasedate,int stock,double price,int pages, String condition){
		super(barcode, booktype, title, language, genre, releasedate, stock, price);
		this.booktype = BookType.paperback;
		this.pages = pages;
		this.condition = condition;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	@Override
	public String toString() {
		return this.barcode+", "+this.getBookType()+", "+this.title+", "+this.getLanguage()+", "+this.getGenre()+", "+this.releasedate+", "+this.stock+", "+this.price+", "+this.pages+", "+this.condition+"\n";
	}
}
