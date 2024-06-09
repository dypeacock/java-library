package coursework;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class CustomerFrame extends JFrame {

	private Customer cst;
	private List<Book> bookList;
	
	private JPanel contentPane;
	private JTextField fieldID;
	private JTextField fieldUsername;
	private JTextField fieldSurname;
	private JTextField fieldAddress;
	private JTextField fieldCredit;
	private JTextField txtAddCredit;
	private JTextField txtBasketPrice;
	private JTable tblBasket;
	private DefaultTableModel dtmBasket;
	
	private JTable bookTable;
	private DefaultTableModel dtmBooks;
	private JTextField txtFilter;
	
	
	

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public CustomerFrame(Customer customer) throws IOException {
		this.cst = customer;
		this.bookList = StockClass.filterPrice();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 603, 335);
		
		// First Panel
		 
		JPanel MainPanel = new JPanel();
		tabbedPane.addTab("Main", null, MainPanel, null);
		MainPanel.setLayout(null);
		
		// initialise all text fields of the panel
		
		fieldID = new JTextField();
		fieldID.setBounds(101, 28, 129, 26);
		fieldID.setColumns(10);
		fieldID.setText(Integer.toString(cst.getId()));
		fieldID.setEditable(false);
		MainPanel.add(fieldID);
		
		fieldUsername = new JTextField();
		fieldUsername.setBounds(100, 56, 130, 26);
		fieldUsername.setColumns(10);
		fieldUsername.setText(cst.getUsername());
		fieldUsername.setEditable(false);
		MainPanel.add(fieldUsername);
		
		fieldSurname = new JTextField();
		fieldSurname.setBounds(101, 113, 129, 26);
		fieldSurname.setColumns(10);
		fieldSurname.setText(cst.getSurname());
		fieldSurname.setEditable(false);
		MainPanel.add(fieldSurname);
		
		fieldAddress = new JTextField();
		fieldAddress.setBounds(101, 85, 129, 26);
		fieldAddress.setColumns(10);
		fieldAddress.setText(cst.getAddress());
		fieldAddress.setEditable(false);
		MainPanel.add(fieldAddress);
		
		fieldCredit = new JTextField();
		fieldCredit.setBounds(101, 187, 130, 26);
		fieldCredit.setColumns(10);
		fieldCredit.setText(Double.toString(cst.getCredit()));
		fieldCredit.setEditable(false);
		MainPanel.add(fieldCredit);
		
		txtBasketPrice = new JTextField();
		txtBasketPrice.setBounds(407, 225, 117, 26);
		txtBasketPrice.setColumns(10);
		txtBasketPrice.setEditable(false);
		MainPanel.add(txtBasketPrice);
		
		txtAddCredit = new JTextField();
		txtAddCredit.setBounds(32, 225, 198, 26);
		txtAddCredit.setColumns(10);
		MainPanel.add(txtAddCredit);
		
		// initialise all Jlabels of the panel
		
		JLabel lblUserID = new JLabel("UserID");
		lblUserID.setBounds(32, 33, 50, 16);
		MainPanel.add(lblUserID);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(32, 61, 69, 16);
		MainPanel.add(lblUsername);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(32, 118, 61, 16);
		MainPanel.add(lblSurname);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(32, 90, 61, 16);
		MainPanel.add(lblAddress);
		
		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setBounds(32, 193, 61, 16);
		MainPanel.add(lblCredit);
		
		JLabel lblShoppingBasket = new JLabel("Shopping Basket");
		lblShoppingBasket.setBounds(343, 6, 104, 16);
		MainPanel.add(lblShoppingBasket);
		
		
		JButton btnAddCredit = new JButton("Add Credit");
		btnAddCredit.setBounds(32, 252, 198, 29);
		MainPanel.add(btnAddCredit);
		btnAddCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Adds Credit to Customer's credit
				try {
					double amount = Double.parseDouble(txtAddCredit.getText());
					try {
						cst.addCredit(amount);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					fieldCredit.setText(Double.toString(cst.getCredit()));
					
					JOptionPane.showMessageDialog(null, "Credit balance updated\nTo see the updated credit on the 'view users' tab,\nreopen the main frame!","Credit",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter Credit value as follows :\n(Double)","Error!",JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check if Customer is attempting to checkout an empty basket
				List<Book> emptyList = new ArrayList <>();
				
				if (cst.getBasket().getItems().equals(emptyList)) {
					JOptionPane.showMessageDialog(null, "Your basket is empty!","Error!",JOptionPane.ERROR_MESSAGE);
				} else {
					
					//Check if Customer can purchase the content of their basket : Error message if not
					if (cst.getBasket().getPrice() > cst.getCredit()) {
						JOptionPane.showMessageDialog(null, "You do not have sufficient credit!","Error!",JOptionPane.ERROR_MESSAGE);
					} else {
						
						//empty Basket : table and Customer attribute
						for (int i = dtmBasket.getRowCount() - 1; i > -1; i--) {
							dtmBasket.removeRow(i);
						}
						
						String receipt = "";
						try {
							receipt = cst.checkOut(); //updates shopping basket and customer credit levels
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						//update Customer Credit field
						fieldCredit.setText(Double.toString(cst.getCredit()));
						txtBasketPrice.setText("0.00");
						
						//send receipt
						JOptionPane.showMessageDialog(null, receipt,"Shopping Basket",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
			}
		});
		btnCheckout.setBounds(274, 252, 117, 29);
		MainPanel.add(btnCheckout);
		
		JButton btnClearBasket = new JButton("Clear Basket");
		btnClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check if Customer is attempting to clear an empty basket
				List<Book> emptyList = new ArrayList <>();
				
				if (cst.getBasket().getItems().equals(emptyList)) {
					JOptionPane.showMessageDialog(null, "Your basket is empty!","Error!",JOptionPane.ERROR_MESSAGE);
				} else {
					
					//Delete items from Basket Table
					for (int i = dtmBasket.getRowCount() - 1; i > -1; i--) {
						dtmBasket.removeRow(i);
					}
					
					//Update Customer's basket
					try {
						cst.emptyBasket();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
					//Update Book Table
					for (int i = dtmBooks.getRowCount() - 1; i > -1; i--) {
	           		 dtmBooks.removeRow(i);
	           	 	}
	           	 
		           	try {
		           		bookList = StockClass.filterPrice();
		           	} catch (IOException e2) {
						e2.printStackTrace();
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
		           	 
		           	
		           	//update Basket Price text field
		           	txtBasketPrice.setText(Double.toString(cst.getBasket().getPrice()));
		    
		           	
		           	//give user feedback
		           	JOptionPane.showMessageDialog(null, "Your Basket has been emptied successfully!","Shopping Basket",JOptionPane.INFORMATION_MESSAGE);
		           	 
				}
				
			}
		});
		btnClearBasket.setBounds(407, 252, 117, 29);
		MainPanel.add(btnClearBasket);
		
		JLabel lblBasketPrice = new JLabel("Total Price :");
		lblBasketPrice.setBounds(284, 230, 107, 16);
		MainPanel.add(lblBasketPrice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(272, 28, 264, 193);
		MainPanel.add(scrollPane);
		
		tblBasket = new JTable();
		scrollPane.setViewportView(tblBasket);
		dtmBasket = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //make all cells immutable
		       return false;
		    }
		};
		dtmBasket.setColumnIdentifiers(new Object[] {"Barcode", "Title", "Price"});
		tblBasket.setModel(dtmBasket);
		
		tblBasket.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) { 
	            	 //get book corresponding to selected row
	            	 int row = tblBasket.getSelectedRow();
	            	 int barcode = (int) tblBasket.getValueAt(row, 0);
	            	 Book book = null;
	            	 try {
						book = StockClass.getBarcode(barcode);
	            	 } catch (IOException e) {
						e.printStackTrace();
	            	 }
	            	 
	            	 //show corresponding book info
	            	 JOptionPane.showMessageDialog(null, book.toString(),"Shopping Basket",JOptionPane.INFORMATION_MESSAGE);
	            	 
	             }
	         }    
		});
	         
		// Second Panel
		
		JPanel ViewBooksPanel = new JPanel();
		tabbedPane.addTab("ViewBooks", null, ViewBooksPanel, null);
		ViewBooksPanel.setLayout(null);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(6, 6, 570, 209);
		bookTable = new JTable();
		scrollPane1.setViewportView(bookTable);
		ViewBooksPanel.add(scrollPane1);
		
		dtmBooks = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //make all cells immutable
		       return false;
		    }
		};
		dtmBooks.setColumnIdentifiers(new Object[] {"Barcode","Booktype", "Title", "Language", "Genre", "Release", "Stock", "Price","Info1","Info2"});
		bookTable.setModel(dtmBooks);
		
		txtFilter = new JTextField();
		txtFilter.setBounds(6, 245, 118, 26);
		txtFilter.setColumns(10);
		ViewBooksPanel.add(txtFilter);
		
		JLabel lblInstruction = new JLabel("To add a book to your basket : double click!");
		lblInstruction.setBounds(215, 227, 288, 16);
		ViewBooksPanel.add(lblInstruction);
		
		
		JButton btnLookUpBarcode = new JButton("Look up barcode");
		btnLookUpBarcode.setBounds(136, 254, 219, 29);
		btnLookUpBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int barcode = Integer.parseInt(txtFilter.getText());
					Book book = null;
	           	 	try {
						book = StockClass.getBarcode(barcode);
	           	 	} catch (IOException e1) {
	           	 		e1.printStackTrace();
	           	 	}
	           	 	
	           	 	if (book == null) {
	           	 		JOptionPane.showMessageDialog(null, "This bookID does not exist!","Error!",JOptionPane.ERROR_MESSAGE);
	           	 	} else {
	           	 		//empty bookTable
						for (int i = dtmBooks.getRowCount() - 1; i > -1; i--) {
							dtmBooks.removeRow(i);
						}
						
						//refill bookTable with the corresponding book
		           	 	
		           	 	if (book instanceof Paperback) {
							Paperback paper = (Paperback) book;
							Object[] rowdata = new Object[] {paper.getBarcode(),paper.getBookType(),paper.getTitle(),paper.getLanguage(),paper.getGenre(),paper.getReleaseDate(),paper.getStock(),paper.getPrice(),paper.getPages(),paper.getCondition()};
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
					
					
	           	 	 	
				} catch (NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter bookID as follows :\n(Int)","Error!",JOptionPane.ERROR_MESSAGE);
				}	
				
			}
		});
		ViewBooksPanel.add(btnLookUpBarcode);
		
		
		JButton btnFilterAudioHrs = new JButton("Filter Audio Hrs");
		btnFilterAudioHrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double hrs = Double.parseDouble(txtFilter.getText());
					if (hrs < 0) {
						JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter a positive value!","Error!",JOptionPane.ERROR_MESSAGE);
					} else {
						
						List<Audiobook> ABookList =  new ArrayList<Audiobook>();
						try {
							ABookList = StockClass.filterAudioHrs(hrs);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						//empty bookTable
						for (int i = dtmBooks.getRowCount() - 1; i > -1; i--) {
							dtmBooks.removeRow(i);
						}
						
						//refill bookTable
						for (Audiobook abook : ABookList) {
							Object[] rowdata = new Object[] {abook.getBarcode(),abook.getBookType(),abook.getTitle(),abook.getLanguage(),abook.getGenre(),abook.getReleaseDate(),abook.getStock(),abook.getPrice(),abook.getLength(),abook.getFormat()};
		       	 			dtmBooks.addRow(rowdata);
						}
						
					}
				
				} catch (NumberFormatException exc){
					JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter Audio Hours :\n(Double)","Error!",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		btnFilterAudioHrs.setBounds(357, 254, 219, 29);
		ViewBooksPanel.add(btnFilterAudioHrs);
		
		
		
		
		
		
		
		//add functionality for adding book to basket
		bookTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) { 
	            	 
	            	 //get book corresponding to selected row
	            	 int row = bookTable.getSelectedRow();
	            	 int barcode = (int) bookTable.getValueAt(row, 0);
	            	 int stock = (int) bookTable.getValueAt(row, 6);
	            	 Book book = null;
	            	 try {
						book = StockClass.getBarcode(barcode);
	            	 } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	            	 }
	            	 
	            	 //ensure stock is non-zero
	            	 if (stock == 0) {
	            		 JOptionPane.showMessageDialog(null, "Error !\nBook is out of Stock","Error!",JOptionPane.ERROR_MESSAGE);
	            	 } else {
	            		 
	            		//add Book to Customer's basket
		            	 try {
		            		 cst.addToBasket(barcode);
		            	 } catch (IOException e) {
		            		 e.printStackTrace();
		            	 }
		            	 
		            	 
		            	 //empty Shopping Basket Table
		            	 for (int i = dtmBasket.getRowCount() - 1; i > -1; i--) {
							dtmBasket.removeRow(i);
						 }
		            	 
		            	 
		            	 //refill shopping basket table
		            	
		            	 List<Book> items = cst.getBasket().getItems();
		            	  
		            	 for (Book bk : items) {
		            		 Object[] rowdata = new Object[] {bk.getBarcode(),bk.getTitle(),bk.getPrice()};
		            		 dtmBasket.addRow(rowdata);
		            	 }
		            	 
		            	//update Basket Price text field
		            	 txtBasketPrice.setText(Double.toString(cst.getBasket().getPrice()));
		            	 
		            	 
		            	 //update the bookTable 
		            	 bookTable.setValueAt(stock-1, row, 6);
		          
		            	 
		            	 //give user feedback
		            	 JOptionPane.showMessageDialog(null, "Book has been added to the basket:\n"+book.toString(),"Added",JOptionPane.INFORMATION_MESSAGE);
		            	 
	            	 }
	            	  
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
		
		
		// Close Tab Button (Main Panel)
		
		JButton btnCloseTab = new JButton("Close Tab");
		btnCloseTab.setBounds(156, 339, 282, 29);
		btnCloseTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame.this.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(tabbedPane);
		contentPane.add(btnCloseTab);
	}
}
