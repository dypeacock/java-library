package coursework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;


public class AdminFrame extends JFrame {
	
	private Admin adm;
	private List<Book> bookList;
	
	private JPanel contentPane;
	private JTextField fieldID;
	private JTextField fieldUsername;
	private JTextField fieldSurname;
	private JTextField fieldAddress;
	
	private JTable bookTable;
	private DefaultTableModel dtmBooks;
	private JTextField txtBarcode;
	private JTextField txtTitle;
	private JTextField txtReleaseDate;
	private JTextField txtPrice;
	private JTextField txtAddInfo1;
	private JTextField txtAddInfo2;
	
	
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public AdminFrame(Admin admin) throws IOException {
		this.adm = admin;
		this.bookList = StockClass.filterQuantity(); //gets books sorted by quantity in stock ascending
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 588, 318);
		
		// Main Panel
		
		JPanel MainPanel = new JPanel();
		tabbedPane.addTab("Main", null, MainPanel, null);
		MainPanel.setLayout(null);
		
		fieldID = new JTextField();
		fieldID.setBounds(79, 23, 130, 26);
		fieldID.setColumns(10);
		fieldID.setText(Integer.toString(adm.getId()));
		fieldID.setEditable(false);
		MainPanel.add(fieldID);
		
		fieldUsername = new JTextField();
		fieldUsername.setBounds(79, 49, 130, 26);
		fieldUsername.setColumns(10);
		fieldUsername.setText(adm.getUsername());
		fieldUsername.setEditable(false);
		MainPanel.add(fieldUsername);
		
		fieldSurname = new JTextField();
		fieldSurname.setBounds(79, 77, 130, 26);
		fieldSurname.setColumns(10);
		fieldSurname.setText(adm.getSurname());
		fieldSurname.setEditable(false);
		MainPanel.add(fieldSurname);
		
		fieldAddress = new JTextField();
		fieldAddress.setBounds(79, 115, 130, 26);
		fieldAddress.setColumns(10);
		fieldAddress.setText(adm.getAddress());
		fieldAddress.setEditable(false);
		MainPanel.add(fieldAddress);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(375, 23, 175, 26);
		txtBarcode.setColumns(10);
		MainPanel.add(txtBarcode);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(375, 49, 175, 26);
		txtTitle.setColumns(10);
		MainPanel.add(txtTitle);
		
		txtReleaseDate = new JTextField();
		txtReleaseDate.setBounds(375, 110, 175, 26);
		txtReleaseDate.setColumns(10);
		txtReleaseDate.setText("dd-MM-yyyy");	//give user an indication of format the date is required to be in
		MainPanel.add(txtReleaseDate);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(375, 138, 175, 26);
		txtPrice.setColumns(10);
		MainPanel.add(txtPrice);
		
		txtAddInfo1 = new JTextField();
		txtAddInfo1.setBounds(373, 200, 177, 26);
		txtAddInfo1.setColumns(10);
		MainPanel.add(txtAddInfo1);
		
		txtAddInfo2 = new JTextField();
		txtAddInfo2.setBounds(373, 226, 177, 26);
		txtAddInfo2.setColumns(10);
		MainPanel.add(txtAddInfo2);
		
		
		JLabel lblUserID = new JLabel("UserID");
		lblUserID.setBounds(6, 28, 61, 16);
		MainPanel.add(lblUserID);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 54, 72, 16);
		MainPanel.add(lblUsername);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(6, 82, 61, 16);
		MainPanel.add(lblSurname);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(6, 120, 61, 16);
		MainPanel.add(lblAddress);
		
		JLabel lblAddNewBook = new JLabel("Add new book");
		lblAddNewBook.setBounds(416, 6, 97, 16);
		MainPanel.add(lblAddNewBook);
		
		JLabel lblBarcode = new JLabel("Barcode");
		lblBarcode.setBounds(275, 28, 61, 16);
		MainPanel.add(lblBarcode);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(275, 54, 38, 16);
		MainPanel.add(lblTitle);
		
		JLabel lblLanguage = new JLabel("Language");
		lblLanguage.setBounds(250, 82, 61, 16);
		MainPanel.add(lblLanguage);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(323, 82, 38, 16);
		MainPanel.add(lblGenre);
		
		JLabel lblReleaseDate = new JLabel("Release Date");
		lblReleaseDate.setBounds(275, 115, 88, 16);
		MainPanel.add(lblReleaseDate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(275, 143, 61, 16);
		MainPanel.add(lblPrice);
		
		JLabel lblAddInfo1 = new JLabel("Pages");
		lblAddInfo1.setBounds(242, 205, 119, 16);
		MainPanel.add(lblAddInfo1);
		
		JLabel lblAddInfo2 = new JLabel("Condition");
		lblAddInfo2.setBounds(242, 231, 119, 16);
		MainPanel.add(lblAddInfo2);
		
		JLabel lblBookType = new JLabel("Book Type");
		lblBookType.setBounds(275, 177, 86, 16);
		MainPanel.add(lblBookType);
		
		
		JComboBox comboLanguage = new JComboBox(Language.values());
		comboLanguage.setBounds(375, 78, 88, 27);
		MainPanel.add(comboLanguage);
		
		JComboBox comboGenre = new JComboBox(Genre.values());
		comboGenre.setBounds(462, 78, 88, 27);
		MainPanel.add(comboGenre);
		
		JComboBox comboBookType = new JComboBox(BookType.values());
		comboBookType.setBounds(400, 176, 117, 19);
		MainPanel.add(comboBookType);
		
		
		//automatically change values of labels corresponding to Additional Info
		//depending on what book type is selected
		comboBookType.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent arg0) {
		    	BookType type = (BookType)comboBookType.getSelectedItem();
		    	if (type == BookType.paperback) {
		    		lblAddInfo1.setText("Pages");
		    		lblAddInfo2.setText("Condition");
		    		
		    	} else if (type == BookType.ebook) {
		    		lblAddInfo1.setText("Pages");
		    		lblAddInfo2.setText("Format");
		    		
		    	} else if (type == BookType.audiobook) {
		    		lblAddInfo1.setText("Length");
		    		lblAddInfo2.setText("Format");
		    		
		    	}
		    }
		});
	
		
		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtTitle.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Error!\nPlease enter a title value!","Error!",JOptionPane.ERROR_MESSAGE);
				} else {
					String title = txtTitle.getText();
					
					if (txtReleaseDate.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Error!\nPlease enter a date value!","Error!",JOptionPane.ERROR_MESSAGE);
					} else {
						String date = txtReleaseDate.getText();
						
						if (txtAddInfo1.getText().equals("") | txtAddInfo2.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Error!\nPlease enter values to additional info :"+lblAddInfo1.getText()+", "+lblAddInfo2.getText(),"Error!",JOptionPane.ERROR_MESSAGE);
						} else {
							
							//retrieve all info corresponding to the new Book
							
							Language language = (Language)comboLanguage.getSelectedItem();
							Genre genre = (Genre)comboGenre.getSelectedItem();
							
							//initialise the stock, barcode and price variables
							int stock = 1;
							int barcode = 0;
							double price = 0.0;
							try {
								//try & catch for parsing the String : create error handler for if user enters wrong data type
								barcode = Integer.parseInt(txtBarcode.getText());	
								price = Double.parseDouble(txtPrice.getText());
								
								//create a null book to test if barcode has been used previously
								Book bookTest = null;
								try {
									bookTest = StockClass.getBarcode(barcode);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								if (bookTest == null) {
									//barcode has not been used before
									
									BookType type = (BookType)comboBookType.getSelectedItem();
									
									//verify the Additional info is in the correct format
									if (type == BookType.paperback) {
										int pages = 0;
										String condition = "";
										try {
											pages = Integer.parseInt(txtAddInfo1.getText());
											condition = txtAddInfo2.getText();
											
											//all data is in the correct format : create new object and write to the stock text file
											Paperback pback = new Paperback(barcode, type, title, language, genre, date, stock, price, pages, condition);
											StockClass.writeData(pback);
											
											JOptionPane.showMessageDialog(null, "Book has been added:\n"+pback.toString(),"Added",JOptionPane.INFORMATION_MESSAGE);
											
										} catch (NumberFormatException | IOException exc){
											// the additional info is in the wrong format : give the user feedback
											JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter "+lblAddInfo1.getText()+" value (int)\n"+lblAddInfo2.getText()+" value (String)","Error!",JOptionPane.ERROR_MESSAGE);
										}
										
									} else if (type == BookType.ebook) {
										int pages = 0;
										String format = "";
										try {
											pages = Integer.parseInt(txtAddInfo1.getText());
											format = txtAddInfo2.getText();
											
											//all data is in the correct format : create new object and write to the stock text file
											Ebook ebook = new Ebook(barcode, type, title, language, genre, date, stock, price, pages, format);
											StockClass.writeData(ebook);
											
											JOptionPane.showMessageDialog(null, "Book has been added:\n"+ebook.toString(),"Added",JOptionPane.INFORMATION_MESSAGE);
											
										} catch (NumberFormatException | IOException exc){
											// the additional info is in the wrong format : give the user feedback
											JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter "+lblAddInfo1.getText()+" value (int)\n"+lblAddInfo2.getText()+" value (String)","Error!",JOptionPane.ERROR_MESSAGE);
										}
										
										
									} else if (type == BookType.audiobook) {
										double length = 0.0;
										String format = "";
										try {
											length = Integer.parseInt(txtAddInfo1.getText());
											format = txtAddInfo2.getText();
											
											//all data is in the correct format : create new object and write to the stock text file
											Audiobook abook = new Audiobook(barcode, type, title, language, genre, date, stock, price, length, format);
											StockClass.writeData(abook);
											
											JOptionPane.showMessageDialog(null, "Book has been added:\n"+abook.toString(),"Added",JOptionPane.INFORMATION_MESSAGE);
											
										} catch (NumberFormatException | IOException exc){
											// the additional info is in the wrong format : give the user feedback
											JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter "+lblAddInfo1.getText()+" value (double)\n"+lblAddInfo2.getText()+" value (String)","Error!",JOptionPane.ERROR_MESSAGE);
										}
										
									}
									
								} else {
									//book exists already : give user feedback
									JOptionPane.showMessageDialog(null, "Error!\nThis barcode is already in use!","Error!",JOptionPane.ERROR_MESSAGE);
								}
								
								
							} catch (NumberFormatException exc){
								//price or barcode values are in wrong format : give user feedback
								JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter Barcode value (int)\nPrice value (double)","Error!",JOptionPane.ERROR_MESSAGE);
							}
							
							
							//update book table
							
							try {
								bookList = StockClass.filterQuantity();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							for (int i = dtmBooks.getRowCount() - 1; i > -1; i--) {
								dtmBooks.removeRow(i);	//removes all rows in the book table
				           	}
				           	 
							//rebuild book table with latest data
							for (Book bk : bookList) {
								if (bk instanceof Paperback) {
									Paperback pbook = (Paperback) bk;
									Object[] rowdata = new Object[] {pbook.getBarcode(),pbook.getBookType(),pbook.getTitle(),pbook.getLanguage(),pbook.getGenre(),pbook.getReleaseDate(),pbook.getStock(),pbook.getPrice(),pbook.getPages(),pbook.getCondition()} ;
									dtmBooks.addRow(rowdata);
				        				
				           		 } else if (bk instanceof Ebook) {
				           			Ebook ebook = (Ebook) bk;
				           			Object[] rowdata = new Object[] {ebook.getBarcode(),ebook.getBookType(),ebook.getTitle(),ebook.getLanguage(),ebook.getGenre(),ebook.getReleaseDate(),ebook.getStock(),ebook.getPrice(),ebook.getPages(),ebook.getFormat()};
				           			dtmBooks.addRow(rowdata);
				        				
				           		 } else if (bk instanceof Audiobook) {
				           			Audiobook abook = (Audiobook) bk;
				           			Object[] rowdata = new Object[] {abook.getBarcode(),abook.getBookType(),abook.getTitle(),abook.getLanguage(),abook.getGenre(),abook.getReleaseDate(),abook.getStock(),abook.getPrice(),abook.getLength(),abook.getFormat()};
				           			dtmBooks.addRow(rowdata);
				           		 }
								
				           	 }
							
						}
						
					}
					
				}
				
			}
		});
		btnAddBook.setBounds(42, 218, 117, 29);
		MainPanel.add(btnAddBook);
		
		
		// View Books Panel
		
		JPanel ViewBooksPanel = new JPanel();
		tabbedPane.addTab("ViewBooks", null, ViewBooksPanel, null);
		
		JLabel lblinstruction = new JLabel("To add stock, double click on the book log!");
		lblinstruction.setBounds(148, 250, 275, 16);
		ViewBooksPanel.add(lblinstruction);
		
		dtmBooks = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //make all cells immutable
		       return false;
		    }
		};
		
		dtmBooks.setColumnIdentifiers(new Object[] {"Barcode","Booktype", "Title", "Language", "Genre", "Release", "Stock", "Price","Info1","Info2"});
		ViewBooksPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 555, 232);
		ViewBooksPanel.add(scrollPane);
		
		bookTable = new JTable();
		scrollPane.setViewportView(bookTable);
		bookTable.setModel(dtmBooks);
		
		bookTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) { 
	            	 //add functionality for adding stock for book when a row is double clicked
	            	 
	            	 //get book 
	            	 int row = bookTable.getSelectedRow();
	            	 int barcode = (int) bookTable.getValueAt(row, 0);
	            	 Book book = null;
	            	 try {
						book = StockClass.getBarcode(barcode);
	            	 } catch (IOException e) {
	            		 e.printStackTrace();
	            	 }
	            	 
	            	 //update Stock text file
	            	 try {
						StockClass.addStock(book.getBarcode());
	            	 } catch (IOException e) {
						e.printStackTrace();
	            	 }
	            	 
	            	 //update book table
	            	 for (int i = dtmBooks.getRowCount() - 1; i > -1; i--) {
	            		 dtmBooks.removeRow(i);
	            	 }
	            	 
	            	 
	            	 try {
	            		 bookList = StockClass.filterQuantity();
	            	 } catch (IOException e) {
	            		 e.printStackTrace();
	            	 }
	            	 
	            	 for (Book bk : bookList) {
	            		 if (bk instanceof Paperback) {
	            			 Paperback pbook = (Paperback) bk;
	            			 Object[] rowdata = new Object[] {pbook.getBarcode(),pbook.getBookType(),pbook.getTitle(),pbook.getLanguage(),pbook.getGenre(),pbook.getReleaseDate(),pbook.getStock(),pbook.getPrice(),pbook.getPages(),pbook.getCondition()} ;
	            			 dtmBooks.addRow(rowdata);
	         				
	            		 } else if (bk instanceof Ebook) {
	            			 Ebook ebook = (Ebook) bk;
	            			 Object[] rowdata = new Object[] {ebook.getBarcode(),ebook.getBookType(),ebook.getTitle(),ebook.getLanguage(),ebook.getGenre(),ebook.getReleaseDate(),ebook.getStock(),ebook.getPrice(),ebook.getPages(),ebook.getFormat()};
	            			 dtmBooks.addRow(rowdata);
	         				
	            		 } else if (bk instanceof Audiobook) {
	            			 Audiobook abook = (Audiobook) bk;
	            			 Object[] rowdata = new Object[] {abook.getBarcode(),abook.getBookType(),abook.getTitle(),abook.getLanguage(),abook.getGenre(),abook.getReleaseDate(),abook.getStock(),abook.getPrice(),abook.getLength(),abook.getFormat()};
	            			 dtmBooks.addRow(rowdata);
	            		 }
	            	 }
	            	 
	            	 JOptionPane.showMessageDialog(null, "Book stock has been added:\n"+book.toString(),"Added",JOptionPane.INFORMATION_MESSAGE);
	            	
	             }
	         }
		});
		
		// fill in the table with data automatically
		
		for (Book book : bookList) {
			if (book instanceof Paperback) {
				Paperback pbook = (Paperback) book;
				Object[] rowdata = new Object[] {pbook.getBarcode(),pbook.getBookType(),pbook.getTitle(),pbook.getLanguage(),pbook.getGenre(),pbook.getReleaseDate(),pbook.getStock(),pbook.getPrice(),pbook.getPages(),pbook.getCondition()} ;
				dtmBooks.addRow(rowdata);
				
			} else if (book instanceof Ebook) {
				Ebook ebook = (Ebook) book;
				Object[] rowdata = new Object[] {ebook.getBarcode(),ebook.getBookType(),ebook.getTitle(),ebook.getLanguage(),ebook.getGenre(),ebook.getReleaseDate(),ebook.getStock(),ebook.getPrice(),ebook.getPages(),ebook.getFormat()};
				dtmBooks.addRow(rowdata);
				
			} else if (book instanceof Audiobook) {
				Audiobook abook = (Audiobook) book;
				Object[] rowdata = new Object[] {abook.getBarcode(),abook.getBookType(),abook.getTitle(),abook.getLanguage(),abook.getGenre(),abook.getReleaseDate(),abook.getStock(),abook.getPrice(),abook.getLength(),abook.getFormat()};
				dtmBooks.addRow(rowdata);
			}
		}
		
		// Main tab
		
		JButton btnCloseTab = new JButton("Close Tab");
		btnCloseTab.setBounds(240, 336, 117, 29);
		btnCloseTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFrame.this.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCloseTab);
		contentPane.add(tabbedPane);
	}
}
