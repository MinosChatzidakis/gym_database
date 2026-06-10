//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrainerDBUtils {
	
	public void addTrainer(Trainer t) {
		
		String sqlQuery = "INSERT INTO trainer (trainer_id, name, specialty, phone, email, gym_Gym_Code) VALUES ("
	            + t.getTrainerID() + ", '"
	            + t.getName() + "', '"
	            + t.getSpecialty() + "', '"
	            + t.getPhone() + "', '"
	            + t.getEmail() + "', "
	            + t.getGymCode() + ")";
		
		try(Connection conn = SQLConnector.getConnection(); 
		         Statement stm = conn.createStatement()) {
			
			int rowsAffected = stm.executeUpdate(sqlQuery);
	        
			if(rowsAffected>0) {
				System.out.println("Trainer was successfully added to db.");
			}else {
				System.out.println("Something went wrong and the trainer could not be added to db.");
			}
		}catch(SQLException e) {
			System.out.println("Error in adding trainer to db:");
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Trainer> getAllTrainers(){
		String sqlQuery= "SELECT trainer_id, name, specialty, phone, email, gym_Gym_Code FROM trainer ORDER BY specialty";
		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
				Statement stm= conn.createStatement()){			
				ArrayList<Trainer> fetchedTrainers = new ArrayList<>();

				ResultSet res = stm.executeQuery(sqlQuery);

				while (res.next()) {
				    Trainer currentTrainer = new Trainer();
				    
				    currentTrainer.setTrainerID( res.getInt("trainer_id") );
				    currentTrainer.setName( res.getString("name") );
				    currentTrainer.setSpecialty( res.getString("specialty") );
				    currentTrainer.setPhone( res.getString("phone") );
				    currentTrainer.setEmail( res.getString("email") );
				    currentTrainer.setGymCode( res.getInt("gym_Gym_Code") ); // Σωστό όνομα στήλης
				    
				    fetchedTrainers.add(currentTrainer);
				}

				res.close();
				conn.close(); //connection to database closed
				return fetchedTrainers ;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public ResultSet getTrainerByGymCode(int gymCode) {
		String sqlQuery= "SELECT * FROM trainer WHERE gym_Gym_Code = " + gymCode;
		ResultSet res= null;
		try {
			Connection con= SQLConnector.getConnection();
			Statement stm= con.createStatement();
			res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
