package coursework;

public class Book {
	/* Here I have defined the parent class Book to the
	 * Paperback, Ebook and Audiobook subclasses, with one method : 
	 * toString, to be overwritten by the subclasses */
	
	protected int barcode;
	protected BookType booktype;
	protected String title;
	protected Language language;
	protected Genre genre;
	protected String releasedate; //create class?
	protected int stock;
	protected double price;
	
	
	public Book(int barcode,BookType booktype, String title,Language language,Genre genre,String releasedate,int stock,double price){
		this.barcode = barcode;
		this.booktype = booktype;
		this.title = title;
		this.language = language;
		this.genre = genre;
		this.releasedate = releasedate;
		this.stock = stock;
		this.price = price;
	
	}
	
	public int getBarcode() {
		return this.barcode;
	}
	
	public String getBookType() {
		if (this.booktype == BookType.ebook) {
			return "ebook";
		} else if (this.booktype == BookType.paperback) {
			return "paperback";
		} else if (this.booktype == BookType.audiobook) {
			return "audiobook";
		}
		return "";
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getLanguage() {
		if (this.language == Language.English) {
			return "English";
		} else if (this.language == Language.French) {
			return "French";
		}
		return "";
	}
	
	public String getGenre() {
		if (this.genre == Genre.Politics) {
			return "Politics";
		} else if (this.genre == Genre.Business) {
			return "Business";
		} else if (this.genre == Genre.CompSci) {
			return "Computer Science"; // Have to define this function to help output
		} else if (this.genre == Genre.Biography) {
			return "Biography";
		}
		return "";
	}
	
	public String getReleaseDate() {
		return this.releasedate;
	}
	
	public int getStock() {
		return this.stock;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String toString(){
		
		return this.barcode+", "+this.getBookType()+", "+this.title+", "+this.getLanguage()+", "+this.getGenre()+", "+this.releasedate+", "+this.stock+", "+this.price;
	}
}
