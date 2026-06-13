//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrainerDBUtils {
	
	public static void addTrainer(Trainer t) {
		String sqlQuery = "INSERT INTO trainer (name, surname, specialty, phone, email, gym_Gym_Code) VALUES ('"
	            + t.getName() + "', '"
	            + t.getSurname() + "', '"  
	            + t.getSpecialty() + "', '"
	            + t.getPhone() + "', '"
	            + t.getEmail() + "', "
	            + t.getGymCode() + ")";
		
		try(Connection conn = SQLConnector.getConnection(); 
		    Statement stm = conn.createStatement()) {
			
			int rowsAffected = stm.executeUpdate(sqlQuery);
			if(rowsAffected > 0) {
				System.out.println("Trainer was successfully added to the database.");
			} else {
				System.out.println("Something went wrong and the trainer could not be added.");
			}
		} catch(SQLException e) {
			System.out.println("Error in adding trainer to db:");
			e.printStackTrace();
		}
	}
	
	public static void updateTrainer(Trainer t) {
	    // Δημιουργία του UPDATE query για τον πίνακα trainer
	    String sqlQuery = "UPDATE trainer SET "
	            + "name = '" + t.getName() + "', "
	            + "surname = '" + t.getSurname() + "', "
	            + "specialty = '" + t.getSpecialty() + "', "
	            + "phone = '" + t.getPhone() + "', "
	            + "email = '" + t.getEmail() + "', "
	            + "gym_Gym_Code = " + t.getGymCode() // Το gym code είναι int, δεν θέλει μονά εισαγωγικά
	            + " WHERE trainer_id = " + t.getTrainerID();
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Trainer data updated successfully in the database.");
	        } else {
	            System.out.println("No changes were made (data might be identical).");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating trainer data in db:");
	        e.printStackTrace();
	    }
	}
	
	public static ArrayList<Trainer> getAllTrainers(){
		String sqlQuery = "SELECT * FROM trainer ORDER BY specialty ASC";
		try(Connection conn = SQLConnector.getConnection(); 
			Statement stm = conn.createStatement()){		
				ArrayList<Trainer> fetchedTrainers = new ArrayList<>();
				ResultSet res = stm.executeQuery(sqlQuery);

				while (res.next()) {
				    Trainer currentTrainer = new Trainer();
				    
				    currentTrainer.setTrainerID(res.getInt("trainer_id"));
				    currentTrainer.setName(res.getString("name"));
				    currentTrainer.setSurname(res.getString("surname")); 
				    currentTrainer.setSpecialty(res.getString("specialty"));
				    currentTrainer.setPhone(res.getString("phone"));
				    currentTrainer.setEmail(res.getString("email"));
				    currentTrainer.setGymCode(res.getInt("gym_Gym_Code")); 
				    
				    fetchedTrainers.add(currentTrainer);
				}
				return fetchedTrainers;
		} catch(SQLException e) {
			System.out.println("Error fetching all trainers:");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Trainer getTrainerByID(int id){
		String sql = "SELECT * FROM trainer WHERE trainer_id = " + id + " LIMIT 1";
		try(Connection conn = SQLConnector.getConnection(); 
			Statement stm = conn.createStatement()){		
				Trainer fetchedTrainer = null;
				ResultSet res = stm.executeQuery(sql);

				if (res.next()) { 
					fetchedTrainer = new Trainer(
							res.getInt("trainer_id"), 
							res.getString("name"), 
							res.getString("surname"), 
							res.getString("email"), 
							res.getString("phone"), 
							res.getInt("gym_Gym_Code"), 
							res.getString("specialty")
					);
				}				    
				return fetchedTrainer;
		} catch(SQLException e) {
			System.out.println("Error fetching trainer by ID:");
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<Trainer> getTrainersByGymCode(int gymCode) {
		return getTrainerByGymCode(gymCode);
	}
	
	 
	public static ArrayList<Trainer> getTrainerByGymCode(int gymCode) {
		String sqlQuery = "SELECT * FROM trainer WHERE gym_Gym_code = " + gymCode;
		try (Connection con = SQLConnector.getConnection();
			 Statement stm = con.createStatement()){
			
			ResultSet res = stm.executeQuery(sqlQuery);
			ArrayList<Trainer> fetchedTrainers = new ArrayList<>();
			
			while(res.next()) {
				Trainer currentTrainer = new Trainer();
				
				currentTrainer.setTrainerID(res.getInt("trainer_id"));
				currentTrainer.setName(res.getString("name"));
				currentTrainer.setSurname(res.getString("surname")); 
				currentTrainer.setSpecialty(res.getString("specialty")); 
				currentTrainer.setPhone(res.getString("phone")); 
				currentTrainer.setEmail(res.getString("email")); 
				currentTrainer.setGymCode(res.getInt("gym_Gym_code"));
				
				fetchedTrainers.add(currentTrainer);
			}
			return fetchedTrainers;
			
		} catch(SQLException e) {
			System.out.println("Error fetching trainers by gym code:");
			e.printStackTrace();
		}
		return null;
	}
}