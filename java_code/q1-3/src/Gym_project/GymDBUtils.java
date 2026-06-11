//Minos
package Gym_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class GymDBUtils {
	public void addGym(Gym g) {
		String sqlQuery= "INSERT INTO gym () VALUES ()";
		try{
			Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement();
			int rowsAffected= stm.executeUpdate(sqlQuery); //run the query on the database and store amount of rows affected by it
			if(rowsAffected>0) {
				System.out.println("Reservation was successfully added to db.");
			}else {
				System.out.println("Something went wrong and reservation could not be added to db.");
			}
		}catch(SQLException e) {
			System.out.println("Error in adding reservation to db:");
			e.printStackTrace();
		}
	}
	
	public ResultSet getAllGyms() {
		String sqlQuery= "SELECT * FROM gym ORDER BY city;";
		try {
			Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Gym> getGymsByCity(String city) {
<<<<<<< HEAD
        
        
        
        String sqlQuery="SELECT g.*, GROUP_CONCAT(s.service_Name SEPARATOR ', ') AS unified_services FROM gym g LEFT JOIN services s ON g.gym_Code = s.gym_Gym_Code WHERE city = '" + city + "' GROUP BY g.gym_Code;";
        
        
        
=======
        String sqlQuery= ("SELECT * FROM gym WHERE city = '" + city + "'");
>>>>>>> 2e717d2 (Pull Request)
        try (Connection con= SQLConnector.getConnection();
                Statement stm= con.createStatement();){
            ResultSet res= stm.executeQuery(sqlQuery);
            ArrayList<Gym> fetchedGyms= new ArrayList<>();
            while(res.next()) {
                Gym currentGym = new Gym(
                        res.getString("city"),
<<<<<<< HEAD
                        res.getString("unified_services"),
=======
                        res.getString("services"),
>>>>>>> 2e717d2 (Pull Request)
                        res.getString("address"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("phone"),
                        res.getInt("gym_code")
                                                );
                fetchedGyms.add(currentGym);
            }
            
            return fetchedGyms;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null; // nothing was found => we return null
    }
	
<<<<<<< HEAD
	public static ArrayList<Gym> getAllGymsSortedByCity() { //returns all gyms ordered by city and attaches their provided services
	    ArrayList<Gym> gyms = new ArrayList<>();
	    
	    // grab the gym info and concatenate all related services into a string
	    String sqlQuery = 
	        "SELECT g.*, GROUP_CONCAT(s.service_Name SEPARATOR ', ') AS unified_services " +
	        "FROM gym g " +
	        "LEFT JOIN services s ON g.gym_Code = s.gym_Gym_Code " + // left join so we keep all gyms even if they offer no services
	        "GROUP BY g.gym_Code " + // allow no duplicates
	        "ORDER BY g.city ASC";
	    
	    try (Connection conn = SQLConnector.getConnection();
	         Statement stmt = conn.createStatement();)
	         {
	    		ResultSet res = stmt.executeQuery(sqlQuery);
	        while (res.next()) {
	            String currentServices = res.getString("unified_services");
	            
	            if (currentServices == null) { 
	                currentServices = "None"; // a gym might offer no services
	            }

	            Gym currentGym = new Gym(
	                res.getString("city"),
	                currentServices,
	                res.getString("address"),
	                res.getString("name"),
	                res.getString("email"),
	                res.getString("phone"),
	                res.getInt("gym_Code")
	            );
	            gyms.add(currentGym);
	        }
	        return gyms;
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null;
=======
	public static ArrayList<Gym> getAllGymsSortedByCity() {
		ArrayList<Gym> gyms = new ArrayList<>();
		
		String sqlQuery = "SELECT * FROM gym ORDER BY city ASC";
		
		try (Connection conn = SQLConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
			ResultSet res = pstmt.executeQuery()){
			
			while (res.next()) {
				Gym currentGym = new Gym(
						res.getString("city"),
						res.getString("services"), 
						res.getString("address"),
						res.getString("name"),
						res.getString("email"),
						res.getString("phone"),
						res.getInt("gym_Code")
					);
				gyms.add(currentGym);
			}
			return gyms;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Gym getGymById(int id) {
		String sqlQuery= ("SELECT * FROM gym WHERE gym_code = '" + id + "'" + "LIMIT 1;");
		try (Connection con= SQLConnector.getConnection();
				Statement stm= con.createStatement();){
			ResultSet res= stm.executeQuery(sqlQuery);
			Gym fetchedGym= null;
			while(res.next()) {
				fetchedGym= new Gym(
						res.getString("city"),
						ServicesDBUtils.getUnifiedServicesByGymCode(id),
						res.getString("address"),
						res.getString("name"),
						res.getString("email"),
						res.getString("phone"),
						res.getInt("Gym_code")
						);
			}
			return fetchedGym;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null; // nothing was found => we return null
>>>>>>> 2e717d2 (Pull Request)
	}



public static Gym getGymById(int id) {
	String sqlQuery= ("SELECT * FROM gym WHERE gym_code = '" + id + "'" + "LIMIT 1;");
	try (Connection con= SQLConnector.getConnection();
			Statement stm= con.createStatement();){
		ResultSet res= stm.executeQuery(sqlQuery);
		Gym fetchedGym= null;
		while(res.next()) {
			fetchedGym= new Gym(
					res.getString("city"),
					ServicesDBUtils.getUnifiedServicesByGymCode(id),
					res.getString("address"),
					res.getString("name"),
					res.getString("email"),
					res.getString("phone"),
					res.getInt("Gym_code")
					);
		}
		return fetchedGym;
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return null; // nothing was found => we return null
}
}