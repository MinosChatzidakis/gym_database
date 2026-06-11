package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDBUtils {
	
	public void addCustomer(Customer c) {
		
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
	
	
	public ArrayList<Customer> getCustomerByGymCode(int gymCode) {
		String sqlQuery = "SELECT * FROM customer WHERE gym_Gym_Code = " + gymCode;
		

		try (Connection con = SQLConnector.getConnection();
			 Statement stm = con.createStatement()) {
			
			ResultSet res = stm.executeQuery(sqlQuery);
			ArrayList<Customer> fetchedCustomers = new ArrayList<>();
			

			while(res.next()) {
				Customer currentCustomer = new Customer(
				    res.getInt("customer_ID"),
				    res.getString("name"),
				    res.getString("email"),
				    res.getString("phone"),
				    res.getInt("gym_Gym_Code")
				);
				
				fetchedCustomers.add(currentCustomer);
			}
			
			res.close();
			return fetchedCustomers;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int addCustomerAndGetId(Customer c) {
		
		String sqlQuery = "INSERT INTO customer (name, email, phone, gym_Gym_Code) VALUES ('" + c.getName() + "', '" + c.getEmail() + "', '" + c.getPhone() + "', " + c.getGymCode() + ")";
		
		try(Connection conn = SQLConnector.getConnection();
				Statement stm = conn.createStatement()) {
			
			int rowsAffected = stm.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			
			if(rowsAffected > 0) {
				try(ResultSet generatedKeys = stm.getGeneratedKeys()){
					if(generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
						System.out.println("Customer was succesfully added with ID: " + generatedId);
						return generatedId;
					}
				}
			}else {
				System.out.println("The customer could not be added to the DataBase");
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		return -1;
	}
	
}
