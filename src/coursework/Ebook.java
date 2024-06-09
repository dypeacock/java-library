package coursework;

public class Ebook extends Book{
	/* This is the Ebook subclass of the Book superclass
	 * it inherits its parent class's methods and attributes.
	 * It overrides the toString method in order to include the two
	 * additional attributes (pages and format) */
	
	private int pages;
	private String format;
	
	Ebook(int barcode,BookType booktype, String title,Language language,Genre genre,String releasedate,int stock,double price,int pages, String format){
		super(barcode, booktype, title, language, genre, releasedate, stock, price);
		this.booktype = BookType.ebook;
		this.pages = pages;
		this.format = format;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public String getFormat() {
		return this.format;
	}
	
	@Override
	public String toString() {
		return this.barcode+", "+this.getBookType()+", "+this.title+", "+this.getLanguage()+", "+this.getGenre()+", "+this.releasedate+", "+this.stock+", "+this.price+", "+this.pages+", "+this.format+"\n";
	}
	
}
