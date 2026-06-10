package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDBUtils {
	public void addCustomer(Customer c) {
		
		// Δημιουργία του query με βάση τις 5 στήλες του πίνακα customer
			String sqlQuery = "INSERT INTO customer (customer_ID, name, email, phone, gym_Gym_Code) VALUES ("
		            + c.getCustomerID() + ", '"
		            + c.getName() + "', '"
		            + c.getEmail() + "', '"
		            + c.getPhone() + "', "
		            + c.getGymCode() + ")";
			
			try(Connection conn = SQLConnector.getConnection(); 
			         Statement stm = conn.createStatement()) {
				
				int rowsAffected = stm.executeUpdate(sqlQuery);
		        
				if(rowsAffected > 0) {
					System.out.println("Customer was successfully added to db.");
				} else {
					System.out.println("Something went wrong and the Customer could not be added to db.");
				}
			} catch(SQLException e) {
				System.out.println("Error in adding Customer to db:");
				e.printStackTrace();
			}
	}
	
	
	public ArrayList<Customer> getAllCustomers(){
		// Επιλέγουμε όλα τα πεδία του πελάτη από τη βάση δεδομένων
		String sqlQuery = "SELECT customer_ID, name, email, phone, gym_Gym_Code FROM customer ORDER BY name";
	
		try(Connection conn = SQLConnector.getConnection(); 
			Statement stm = conn.createStatement()){			
			ArrayList<Customer> fetchedCustomers = new ArrayList<>();
			
			ResultSet res = stm.executeQuery(sqlQuery);

			while (res.next()) {
			    Customer currentCustomer = new Customer(
			        res.getInt("customer_ID"),
			        res.getString("name"),
			        res.getString("email"),
			        res.getString("phone"),
			        res.getInt("gym_Gym_Code"));
			    
			    fetchedCustomers.add(currentCustomer);
			}
			
			res.close();
			return fetchedCustomers;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public ResultSet getCustomerByGymCode(int gymCode) {
		// Αναζήτηση με βάση το σωστό όνομα της στήλης (gym_Gym_Code)
		String sqlQuery = "SELECT * FROM customer WHERE gym_Gym_Code = " + gymCode;
		ResultSet res = null;
		try {
			Connection con = SQLConnector.getConnection();
			Statement stm = con.createStatement();
			res = stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
