package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDBUtils { // Αφαιρέθηκε το ερωτηματικό/τελεία στο τέλος
	
	public static void addCustomer(Customer c) {
		
		String sqlQuery = "INSERT INTO customer (name, surname, email, phone, gym_Gym_Code) VALUES ('"
	            + c.getName() + "', '"
	            + c.getSurname() + "', '"  
	            + c.getEmail() + "', '"
	            + c.getPhone() + "', "
	            + c.getGymCode() + ")";
		
		try(Connection conn = SQLConnector.getConnection(); 
		    Statement stm = conn.createStatement()) {
			
			int rowsAffected = stm.executeUpdate(sqlQuery);
			if(rowsAffected > 0) {
				System.out.println("Customer was successfully added to the database.");
			} else {
				System.out.println("Something went wrong and the Customer could not be added.");
			}
		} catch(SQLException e) {
			System.out.println("Error in adding Customer to db:");
			e.printStackTrace();
		}
	}
	
	public static void updateCustomer(Customer c) { 
	   
	    String sqlQuery = "UPDATE customer SET "
	            + "name = '" + c.getName() + "', "
	            + "surname = '" + c.getSurname() + "', "
	            + "email = '" + c.getEmail() + "', "
	            + "phone = '" + c.getPhone() + "', "
	            + "gym_Gym_Code = " + c.getGymCode() 
	            + " WHERE customer_id = " + c.getCustomerID(); // Χρήση του ID του πελάτη
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Customer data updated successfully in the database.");
	        } else {
	            System.out.println("No changes were made (data might be identical).");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating customer data in db:");
	        e.printStackTrace();
	    }
	}
	
	public static Customer getCustomerByID(int id) {
	    String sql = "SELECT * FROM customer WHERE customer_id = " + id + " LIMIT 1";
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {		
	        Customer fetchedCustomer = null;
	        ResultSet res = stm.executeQuery(sql);

	        if (res.next()) { 
	            fetchedCustomer = new Customer(
	                    res.getInt("customer_id"), 
	                    res.getString("surname"), 
	                    res.getString("name"), 
	                    res.getString("email"), 
	                    res.getString("phone"), 
	                    res.getInt("gym_Gym_Code")
	            );
	        }				    
	        return fetchedCustomer;
	    } catch (SQLException e) {
	        System.out.println("Error fetching customer by ID:");
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static ArrayList<Customer> getAllCustomers(){
		String sqlQuery = "SELECT customer_ID, name, surname, email, phone, gym_Gym_Code FROM customer ORDER BY name";
	
		try(Connection conn = SQLConnector.getConnection(); 
			Statement stm = conn.createStatement()){			
			ArrayList<Customer> fetchedCustomers = new ArrayList<>();
			ResultSet res = stm.executeQuery(sqlQuery);

			while (res.next()) {
				
			    Customer currentCustomer = new Customer(
			        res.getInt("customer_ID"),
			        res.getString("surname"),
			        res.getString("name"),
			        res.getString("email"),
			        res.getString("phone"),
			        res.getInt("gym_Gym_Code")
			    );
			    fetchedCustomers.add(currentCustomer);
			}
			return fetchedCustomers;
		} catch(SQLException e) {
			System.out.println("Error fetching all customers:");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<Customer> getCustomerByGymCode(int gymCode) {
		String sqlQuery = "SELECT * FROM customer WHERE gym_Gym_Code = " + gymCode;
		
		try (Connection con = SQLConnector.getConnection();
			 Statement stm = con.createStatement()) {
			
			ResultSet res = stm.executeQuery(sqlQuery);
			ArrayList<Customer> fetchedCustomers = new ArrayList<>();
			
			while(res.next()) {
				Customer currentCustomer = new Customer(
				    res.getInt("customer_ID"),
				    res.getString("name"),
				    res.getString("surname"),
				    res.getString("email"),
				    res.getString("phone"),
				    res.getInt("gym_Gym_Code")
				);
				fetchedCustomers.add(currentCustomer);
			}
			return fetchedCustomers; 
			
		} catch(SQLException e) {
			System.out.println("Error fetching customers by gym code:");
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
	
	public static Customer getCustomerByPhone(String phone) {
		String sql = "SELECT * FROM customer WHERE phone = '" + phone + "' LIMIT 1";
		try (Connection conn = SQLConnector.getConnection(); 
		        Statement stm = conn.createStatement()) {		
			Customer fetchedCustomer = null;
		       
			ResultSet res = stm.executeQuery(sql);
			if (res.next()) { 
				fetchedCustomer = new Customer(
						res.getInt("customer_id"), 
						res.getString("surname"), 
		                res.getString("name"), 
		                res.getString("email"), 
		                res.getString("phone"), 
		                res.getInt("gym_Gym_Code")
		            );
		        }				    
			return fetchedCustomer;
		} catch (SQLException e) {
			System.out.println("Error fetching customer by Phone:");
			e.printStackTrace();
			return null;
		    }
		}
	
}
