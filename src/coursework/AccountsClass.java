package coursework;
import java.io.File;
import java.io.IOException; 
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AccountsClass {
	
	public static List<User> getData() throws IOException {
		/* This method retrieves all data from the UserAccounts text file
		 * using a file scanner object
		 * and stores it into an Array List of User objects as an output */
		
		File inputFile = new File("UserAccounts.txt");
		Scanner fileScanner = new Scanner(inputFile);
		
		List<User> UserList =  new ArrayList<User>();
		
		while (fileScanner.hasNextLine()) {
			String[] userDetails = fileScanner.nextLine().split(",");
			
			if (userDetails[7].trim().equals("customer")) {
				Address address = new Address(Integer.parseInt(userDetails[3].trim()),userDetails[4].trim(),userDetails[5].trim());
				ShoppingBasket basket = new ShoppingBasket(null, 0);
				Customer cust = new Customer(Integer.parseInt(userDetails[0].trim()),
											userDetails[1].trim(),
											userDetails[2].trim(),
											address,
											Double.parseDouble(userDetails[6].trim()),
											UserType.customer,
											basket);
				UserList.add(cust);
		
			} else if (userDetails[7].trim().equals("admin")) {
				Address address = new Address(Integer.parseInt(userDetails[3].trim()),userDetails[4].trim(),userDetails[5].trim());
				Admin adm = new Admin(Integer.parseInt(userDetails[0].trim()),
										userDetails[1].trim(),
										userDetails[2].trim(),
										address,
										UserType.admin);
				UserList.add(adm);
				
			}
			
		}
	
		fileScanner.close();
		
		return UserList;
	}	
	
	
	public static void writeData(User usr) throws IOException {
		/* This method receives a User variable as a parameter
		 * and writes it to the UserAccounts text file
		 * without any returning any output */
		
		List<User> UserList = getData();
		
		if (usr.getUsertype().equals("customer")) {
			Customer cust = (Customer) usr;
			UserList.add(cust);
			
		} else if (usr.getUsertype().equals("admin")) {
			Admin adm = (Admin) usr;
			UserList.add(adm);
		} 
		
		try {
		      FileWriter myWriter = new FileWriter("UserAccounts.txt");
		      
		      for (User user : UserList) {
					if (user instanceof Customer) {
						Customer cust = (Customer) user;
						myWriter.write(cust.toString());
						
					} else if (user instanceof Admin) {
						Admin adm = (Admin) user;
						myWriter.write(adm.toString());
						
					}
		      }	
		      
		      myWriter.close();
			  
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
	}
	
	public static void updateCredit(Customer customer) throws IOException {
		/* This method receives a Customer variable as a parameter
		 * and updates the relevant Customer's credit levels in the UserAccounts text file
		 * without returning any output */
		
		List<User> UserList = getData();
		
		try {
			FileWriter myWriter = new FileWriter("UserAccounts.txt");
		      
			for (User user : UserList) {
				if (user instanceof Customer) {
					Customer cust = (Customer) user;
					
					if (cust.getId() == customer.getId()) {
						//same log : update the credit
						cust.setCredit(customer.getCredit());
					}
						
					myWriter.write(cust.toString());
						
				} else if (user instanceof Admin) {
					Admin adm = (Admin) user;
					myWriter.write(adm.toString());
						
				}
		    }	
		      
			 myWriter.close();
			  
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}


}
