//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GymDBUtils { 
	
	public static void addGym(Gym g) {
	    String sqlQuery = "INSERT INTO gym (name, address, city, phone, email) VALUES ('"
	            + g.getName() + "', '"
	            + g.getAddress() + "', '"
	            + g.getCity() + "', '"
	            + g.getPhone() + "', '"
	            + g.getEmail() + "')";
	 
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("The Gym was successfully added to the database.");
	        } else {
	            System.out.println("Something went wrong and the gym could not be added.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in adding gym to db:");
	        e.printStackTrace();
	    }
	}
	
	public static void updateGym(Gym g) {
	    String sqlQuery = "UPDATE gym SET "
	            + "name = '" + g.getName() + "', "
	            + "address = '" + g.getAddress() + "', "
	            + "city = '" + g.getCity() + "', "
	            + "phone = '" + g.getPhone() + "', "
	            + "email = '" + g.getEmail() + "' "
	            + "WHERE gym_code = " + g.getGymCode();
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Gym data updated successfully in the database.");
	        } else {
	            System.out.println("No changes were made (data might be identical).");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating gym data in db:");
	        e.printStackTrace();
	    }
	}
	
	
	public static ArrayList<Gym> getAllGyms() {
		String sqlQuery = "SELECT * FROM gym ORDER BY city;";
		ArrayList<Gym> gyms = new ArrayList<>();
		try (Connection conn = SQLConnector.getConnection();
			 Statement stm = conn.createStatement();
			 ResultSet res = stm.executeQuery(sqlQuery)) {
			
			while (res.next()) {
				Gym currentGym = new Gym(
	                res.getString("city"),
	                "None",
	                res.getString("address"),
	                res.getString("name"),
	                res.getString("email"),
	                res.getString("phone"),
	                res.getInt("gym_code")
	            );
	            gyms.add(currentGym);
			}
			return gyms;
		} catch(SQLException e) {
			System.out.println("Error fetching all gyms:");
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<Gym> getGymsByCity(String city) {
        String sqlQuery = "SELECT g.*, GROUP_CONCAT(s.service_Name SEPARATOR ', ') AS unified_services FROM gym g LEFT JOIN services s ON g.gym_Code = s.gym_Gym_Code WHERE city = '" + city + "' GROUP BY g.gym_Code;";
        try (Connection con = SQLConnector.getConnection();
                Statement stm = con.createStatement();){
            ResultSet res = stm.executeQuery(sqlQuery);
            ArrayList<Gym> fetchedGyms = new ArrayList<>();
            while(res.next()) {
                Gym currentGym = new Gym(
                        res.getString("city"),
                        res.getString("unified_services"),
                        res.getString("address"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("phone"),
                        res.getInt("gym_code")
                );
                fetchedGyms.add(currentGym);
            }
            return fetchedGyms;
        } catch(SQLException e) {
            System.out.println("Error fetching gyms by city:");
            e.printStackTrace();
        }
        return null; 
    }
	
	public static ArrayList<Gym> getAllGymsSortedByCity() { 
	    ArrayList<Gym> gyms = new ArrayList<>();
	    //get all gyms with the services they provide
	    String sqlQuery = 
	        "SELECT g.*, GROUP_CONCAT(s.service_Name SEPARATOR ', ') AS unified_services " +
	        "FROM gym g " +
	        "LEFT JOIN services s ON g.gym_Code = s.gym_Gym_Code " + 
	        "GROUP BY g.gym_Code " + 
	        "ORDER BY g.city ASC";
	    
	    try (Connection conn = SQLConnector.getConnection();
	         Statement stmt = conn.createStatement();) {
	    	
	    	ResultSet res = stmt.executeQuery(sqlQuery);
	        while (res.next()) {
	            String currentServices = res.getString("unified_services");
	            
	            if (currentServices == null) { 
	                currentServices = "None"; 
	            }

	            Gym currentGym = new Gym(
	                res.getString("city"),
	                currentServices,
	                res.getString("address"),
	                res.getString("name"),
	                res.getString("email"),
	                res.getString("phone"),
	                res.getInt("gym_code") 
	            );
	            gyms.add(currentGym);
	        }
	        return gyms;
	        
	    } catch(SQLException e) {
	        System.out.println("Error fetching sorted gyms:");
	        e.printStackTrace();
	    }
	    return null;
	}

	public static Gym getGymById(int id) {
		String sqlQuery = "SELECT * FROM gym WHERE gym_code = " + id + " LIMIT 1";
		try (Connection con = SQLConnector.getConnection();
				Statement stm = con.createStatement();){
			ResultSet res = stm.executeQuery(sqlQuery);
			Gym fetchedGym = null;
			if (res.next()) { 
				fetchedGym = new Gym(
					res.getString("city"),
					ServicesDBUtils.getUnifiedServicesByGymCode(id),
					res.getString("address"),
					res.getString("name"),
					res.getString("email"),
					res.getString("phone"),
					res.getInt("gym_code")
				);
			}
			return fetchedGym;
		} catch(SQLException e) {
			System.out.println("Error fetching gym by ID:");
			e.printStackTrace();
		}
		return null; 
	}
}