package coursework;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSurname;
	private JTextField txtAddressInt;
	private JTextField txtAddress1;
	private JTextField txtAddress2;
	private JComboBox comboUserType;
	private List<User> userList;
	
	private JTable tblUser;
	private DefaultTableModel dtmUser;
	private JLabel lbLoginInstruction;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainFrame() throws IOException {
		userList = AccountsClass.getData();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 438, 260);
		contentPane.add(tabbedPane);
		
		//First Panel
		
		JPanel ViewUsersPanel = new JPanel();
		tabbedPane.addTab("View Users", null, ViewUsersPanel, null);
		ViewUsersPanel.setLayout(null);
		
		lbLoginInstruction = new JLabel("To login double click on a user's row");
		lbLoginInstruction.setBounds(92, 192, 239, 16);
		ViewUsersPanel.add(lbLoginInstruction);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 411, 174);
		ViewUsersPanel.add(scrollPane);
		
		tblUser = new JTable();
		scrollPane.setViewportView(tblUser);
		
		dtmUser = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //make all cells immutable
		       return false;
		    }
		};
				
		dtmUser.setColumnIdentifiers(new Object[] {"ID","Username", "Surname", "Address", "Credit", "User Type"});
		tblUser.setModel(dtmUser);
		
		//Login system : double clicks on a user log enables the system user to login to their account
		tblUser.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) {     // to detect double click events
	            	 
	            	 // gets selected row
	            	 int row = tblUser.getSelectedRow();
	            	 
	            	 //creates Customer or Admin object from row info
	            	 int userID = (int) tblUser.getValueAt(row, 0);
	            	 String username = (String) tblUser.getValueAt(row, 1);
	            	 String surname = (String) tblUser.getValueAt(row, 2);
	            	 
	            	 String ad = (String) tblUser.getValueAt(row, 3);
	            	 String[] adDetails = ad.split(",");
	            	 Address address = new Address(Integer.parseInt(adDetails[0].trim()), adDetails[1].trim(), adDetails[2].trim());
	            	 
	            	 String usertypeDetails = (String) tblUser.getValueAt(row, 5);
	            	 
	            	 
	            	 if (usertypeDetails.equalsIgnoreCase("admin")) {
	            		 Admin adm = new Admin(userID,username,surname,address,UserType.admin);
	            		 
	            		 AdminFrame aframe = null;
	            		 try {
	            			 aframe = new AdminFrame(adm);
	            		 } catch (IOException e) {
	            			 e.printStackTrace();
						 }
	            		 aframe.setVisible(true);
	            		 
	            	 } else if (usertypeDetails.equalsIgnoreCase("customer")) {
	            		 
	            		 double credit = (double) tblUser.getValueAt(row, 4);
	            		 List<Book> items = new ArrayList<>();
	            		 ShoppingBasket basket = new ShoppingBasket(items, 0);
	            		 Customer cust = new Customer(userID,username,surname,address,credit, UserType.admin, basket);
	            		 
	            		 CustomerFrame cframe = null;
	            		 
	            		 try {
	            			 cframe = new CustomerFrame(cust);
	            		 } catch (IOException e) {
	            			 // TODO Auto-generated catch block
	            			 
	            			 e.printStackTrace();
	            		 }
	            		 cframe.setVisible(true);
	            		 
	            	 }
	            	 
	             }
	         }
	    });
		
		
		// fill in the table with data automatically
		for (User usr : userList) {
			
			if (usr instanceof Customer) {
				Customer cust = (Customer) usr;
				Object[] rowdata = new Object[] {cust.getId(), cust.getUsername(), cust.getSurname(), cust.getAddress(), cust.getCredit(), cust.getUsertype()} ;
				dtmUser.addRow(rowdata);
				
			} else if (usr instanceof Admin) {
				Admin adm = (Admin) usr;
				Object[] rowdata = new Object[] {adm.getId(), adm.getUsername(), adm.getSurname(), adm.getAddress(), "", adm.getUsertype()} ;
				dtmUser.addRow(rowdata);
				
			}
			
		}
		
	
		// Second Panel
		
		JPanel AddUserPanel = new JPanel();
		tabbedPane.addTab("Add User", null, AddUserPanel, null);
		AddUserPanel.setLayout(null);
		
		
		txtSurname = new JTextField();
		txtSurname.setBounds(83, 34, 258, 26);
		txtSurname.setColumns(10);
		AddUserPanel.add(txtSurname);
		
		txtAddressInt = new JTextField();
		txtAddressInt.setBounds(83, 78, 36, 26);
		txtAddressInt.setColumns(10);
		AddUserPanel.add(txtAddressInt);
		
		txtAddress1 = new JTextField();
		txtAddress1.setBounds(118, 78, 91, 26);
		txtAddress1.setColumns(10);
		AddUserPanel.add(txtAddress1);
		
		txtAddress2 = new JTextField();
		txtAddress2.setBounds(210, 78, 130, 26);
		txtAddress2.setColumns(10);
		AddUserPanel.add(txtAddress2);
		
		comboUserType = new JComboBox(UserType.values());
		comboUserType.setBounds(79, 128, 130, 27);
		AddUserPanel.add(comboUserType);
		
		
		JLabel labelSurname = new JLabel("Surname");
		labelSurname.setBounds(10, 39, 61, 16);
		AddUserPanel.add(labelSurname);
		
		JLabel labelAddress = new JLabel("Address");
		labelAddress.setBounds(10, 83, 61, 16);
		AddUserPanel.add(labelAddress);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(221, 127, 130, 29);
		AddUserPanel.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Verify the user surname
				if (txtSurname.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter a surname value","Error!",JOptionPane.ERROR_MESSAGE);
				} else {
					
					if (txtAddress1.getText().equals("") | txtAddress2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter values for address fields","Error!",JOptionPane.ERROR_MESSAGE);
					} else {
						//Verify the data type of text field txtAddressInt
						
						try {
							//attempt data conversion before User Table is emptied : therefore error handler does not have to reconstruct table.
							int addInt = Integer.parseInt(txtAddressInt.getText());
							
							//Automate the userID and userName field creation
							int nb = userList.size()+1;
							int userID = 100+nb;
							String userName = "user".concat(Integer.toString(nb));
							
							
							//empty User table
							for (int i = dtmUser.getRowCount() - 1; i > -1; i--) {
								dtmUser.removeRow(i);
							}
							
							//create new User entry and add to User List
							Address adr = new Address(Integer.parseInt(txtAddressInt.getText()),txtAddress1.getText(),txtAddress2.getText());
					
							if ((UserType)comboUserType.getSelectedItem() == UserType.admin) {
								Admin adm = new Admin(userID, userName, txtSurname.getText(), adr, (UserType)comboUserType.getSelectedItem());
								
								try {
									AccountsClass.writeData(adm);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								userList.add(adm);
								
							} else {
								ShoppingBasket basket = new ShoppingBasket(null, 0);
								Customer cust = new Customer(userID, userName, txtSurname.getText(), adr, 0.00, (UserType)comboUserType.getSelectedItem(), basket);
								
								try {
									AccountsClass.writeData(cust);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								userList.add(cust);
							}
							
							//refill User table with updated info
							for (User usr : userList) {
								
								if (usr instanceof Customer) {
									Customer cust = (Customer) usr;
									Object[] rowdata = new Object[] {cust.getId(), cust.getUsername(), cust.getSurname(), cust.getAddress(), cust.getCredit(), cust.getUsertype()} ;
									dtmUser.addRow(rowdata);
									
								} else if (usr instanceof Admin) {
									Admin adm = (Admin) usr;
									Object[] rowdata = new Object[] {adm.getId(), adm.getUsername(), adm.getSurname(), adm.getAddress(), "", adm.getUsertype()} ;
									dtmUser.addRow(rowdata);
									
								}
								
							}
							
							//give user feedback that the new entry has worked.
							JOptionPane.showMessageDialog(null, "New User added!","User",JOptionPane.INFORMATION_MESSAGE);
							
						} catch (NumberFormatException exc){
							JOptionPane.showMessageDialog(null, "Field input incorrect!\nPlease enter the first address value (int)","Error!",JOptionPane.ERROR_MESSAGE);
						}
					}
									
				}
				
			}
		});
		
	}
}
