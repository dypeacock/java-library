package coursework;
import java.io.File;
import java.io.IOException; 
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class StockClass {

	public static List<Book> getData() throws IOException {
		/* This method retrieves all data from the Stock text file
		 * using a file scanner object
		 * and stores it into an Array List of Book objects as an output */
		
		File inputFile = new File("Stock.txt");
		Scanner fileScanner = new Scanner(inputFile);
		
		List<Book> BookList =  new ArrayList<Book>();
		
		while (fileScanner.hasNextLine()) {
			String[] bookDetails = fileScanner.nextLine().split(",");
			
			Language language = null;
			if (bookDetails[3].trim().equals("English")) {
				language = Language.English;
			} else if (bookDetails[3].trim().equals("French")) {
				language = Language.French;
			}
			
			Genre genre = null;
			if (bookDetails[4].trim().equals("Politics")) {
				genre = Genre.Politics;
			} else if (bookDetails[4].trim().equals("Business")) {
				genre = Genre.Business;
			} else if (bookDetails[4].trim().equals("Computer Science")) {
				genre = Genre.CompSci;
			} else if (bookDetails[4].trim().equals("Biography")) {
				genre = Genre.Biography;
			}
			
			
			
			if (bookDetails[1].trim().equals("paperback")) {
				
				
				Paperback pbook = new Paperback(Integer.parseInt(bookDetails[0].trim()),
											BookType.paperback,
											bookDetails[2].trim(),
											language,
											genre,
											bookDetails[5].trim(),
											Integer.parseInt(bookDetails[6].trim()),
											Double.parseDouble(bookDetails[7].trim()),
											Integer.parseInt(bookDetails[8].trim()),
											bookDetails[9].trim());
				BookList.add(pbook);
		
			} else if (bookDetails[1].trim().equals("ebook")) {
				Ebook ebook = new Ebook(Integer.parseInt(bookDetails[0].trim()),
											BookType.ebook,
											bookDetails[2].trim(),
											language,
											genre,
											bookDetails[5].trim(),
											Integer.parseInt(bookDetails[6].trim()),
											Double.parseDouble(bookDetails[7].trim()),
											Integer.parseInt(bookDetails[8].trim()),
											bookDetails[9].trim());
				BookList.add(ebook);
				
			} else if (bookDetails[1].trim().equals("audiobook")) {
				Audiobook abook = new Audiobook(Integer.parseInt(bookDetails[0].trim()),
											BookType.audiobook,
											bookDetails[2].trim(),
											language,
											genre,
											bookDetails[5].trim(),
											Integer.parseInt(bookDetails[6].trim()),
											Double.parseDouble(bookDetails[7].trim()),
											Double.parseDouble(bookDetails[8].trim()),
											bookDetails[9].trim());
				BookList.add(abook);
				
			}
			
		}
	
		fileScanner.close();
		
		return BookList;
	}
		
	
	public static Book getBarcode(int barcode) throws IOException{
		/* This method uses the getData method to retrieve the stock data
		 * and loops through the List checking each book's barcode and 
		 * returns the corresponding Paperback, Ebook or Audiobook object */
		
		List<Book> BookList = getData();
		
		for (Book book : BookList) {
			if (book.barcode == barcode) {
				if (book instanceof Paperback) {
					Paperback paper = (Paperback) book;
					return paper;
					
				} else if (book instanceof Ebook) {
					Ebook ebook = (Ebook) book;
					return ebook;
					
				} else if (book instanceof Audiobook) {
					Audiobook abook = (Audiobook) book;
					return abook;
				}
			}
			
		}
		return null;
	}
	
	public static List<Book> filterQuantity() throws IOException {
		List<Book> BookList = getData();
		
		QuantityCompare qcompare = new QuantityCompare();
		Collections.sort(BookList, qcompare);
		
		return BookList;
	}
	
	public static List<Audiobook> filterAudioHrs(double length) throws IOException{
		/* This method uses the getData method to retrieve the stock data
		 * and creates a new ArrayList of Audiobook objects to which is appended every
		 * Audiobook with a length greater or equal to the one given as a parameter,
		 * and finally sorts the AudioBookList array in ascending order using Collections.sort 
		 * and a lcompare object defined in LengthCompare */
		
		List<Book> BookList = getData();
		List<Audiobook> AudioBookList =  new ArrayList<Audiobook>();
		
		for (Book book : BookList) {
			if (book instanceof Audiobook) {
				Audiobook abook = (Audiobook) book;
				
				if (abook.getLength() >= length) {
					AudioBookList.add(abook);
				
				}
			}
			
		}
		
		LengthCompare lcompare = new LengthCompare();
		Collections.sort(AudioBookList, lcompare);
		
		return AudioBookList;
		
	}
	
	
	public static List<Book> filterPrice() throws IOException{
		/* This method uses the getData method to retrieve the stock data
		 * and sorts the BookList array in ascending order using Collections.sort 
		 * and a pcompare object defined in PriceCompare */
		
		List<Book> BookList = getData();
		
		PriceCompare pcompare = new PriceCompare();
		Collections.sort(BookList, pcompare);
		
		return BookList;
		
	}
	
	
	public static void writeData(Book bk) throws IOException {
		/* This method adds a new line to the Stock text file, corresponding to a new book entry
		 * it takes as parameters all relevant and required information to create the new Book object
		 * and appends this to the existing BookList before writing the data to the Stock text file.
		 * Note: this does not check the database for an identical barcode to the new one */
		
		List<Book> BookList = getData();
		BookList.add(bk);
		
		try {
	      FileWriter myWriter = new FileWriter("Stock.txt");
	      
	      for (Book book : BookList) {
				if (book instanceof Paperback) {
					Paperback pbook = (Paperback) book;
					myWriter.write(pbook.toString());
					
				} else if (book instanceof Ebook) {
					Ebook ebook = (Ebook) book;
					myWriter.write(ebook.toString());
					
				} else if (book instanceof Audiobook) {
					Audiobook abook = (Audiobook) book;
					myWriter.write(abook.toString());
				}
	      }	
	      
	      myWriter.close();
		  
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
		
	}
	
	public static void addStock(int barcode) throws IOException{
		/* This method receives a barcode value as input and loops through
		 * the BookList looking for the corresponding book, and updates its
		 * stock value by incrementing it by one */
		
		List<Book> BookList = getData();
		
		try {
			FileWriter myWriter = new FileWriter("Stock.txt");
		    
		    for (Book book : BookList) {
		    	  
		    	if (book instanceof Paperback) {
					Paperback pbook = (Paperback) book;
					if (book.barcode == barcode) {
		    	  		pbook.stock += 1;
		    	  	}
					myWriter.write(pbook.toString());
						
				} else if (book instanceof Ebook) {
					Ebook ebook = (Ebook) book;
					if (book.barcode == barcode) {
		    	  		ebook.stock += 1;
		    	  	}
					myWriter.write(ebook.toString());
					
				} else if (book instanceof Audiobook) {
					Audiobook abook = (Audiobook) book;
					if (book.barcode == barcode) {
		    	  		abook.stock += 1;
		    	  	}
					myWriter.write(abook.toString());
				}
					
		    }	
		      
		    myWriter.close();
			  
		} catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}

	}
	
	
	public static void removeStock(int barcode) throws IOException{
		/* This method receives a barcode value as input and loops through
		 * the BookList looking for the corresponding book, and updates its
		 * stock value by decrementing it by one */
		
		List<Book> BookList = getData();
		
		try {
			FileWriter myWriter = new FileWriter("Stock.txt");
		    
		    for (Book book : BookList) {
		    	  
		    	if (book instanceof Paperback) {
					Paperback pbook = (Paperback) book;
					if (book.barcode == barcode) {
		    	  		pbook.stock -= 1;
		    	  	}
					myWriter.write(pbook.toString());
						
				} else if (book instanceof Ebook) {
					Ebook ebook = (Ebook) book;
					if (book.barcode == barcode) {
		    	  		ebook.stock -= 1;
		    	  	}
					myWriter.write(ebook.toString());
					
				} else if (book instanceof Audiobook) {
					Audiobook abook = (Audiobook) book;
					if (book.barcode == barcode) {
		    	  		abook.stock -= 1;
		    	  	}
					myWriter.write(abook.toString());
				}
					
		    }	
		      
		    myWriter.close();
			  
		} catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}

	}


}
