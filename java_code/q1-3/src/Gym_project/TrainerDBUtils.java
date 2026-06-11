//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class TrainerDBUtils {
	
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
	
	
	public static ArrayList<Trainer> getAllTrainers(){
		String sqlQuery= "SELECT * FROM trainer ORDER BY specialty ASC";
		
		ArrayList<Trainer> fetchedTrainers = new ArrayList<>();
		
		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
				PreparedStatement stm= conn.prepareStatement(sqlQuery);
				ResultSet res = stm.executeQuery()){	
			
				while (res.next()) {
				    Trainer currentTrainer = new Trainer();
				    
				    currentTrainer.setTrainerID( res.getInt("trainer_ID") );
				    currentTrainer.setName( res.getString("name") );
				    currentTrainer.setSpecialty( res.getString("specialty") );
				    currentTrainer.setPhone( res.getString("phone") );
				    currentTrainer.setEmail( res.getString("email") );
				    currentTrainer.setGymCode( res.getInt("gym_Gym_Code") ); 
				    
				    fetchedTrainers.add(currentTrainer);
				}

				return fetchedTrainers;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Trainer getTrainerByID(int id){
		String sql= "SELECT * FROM trainer WHERE trainer_ID= '" + id + "' LIMIT 1;";
		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
				Statement stm= conn.createStatement()){		
				Trainer fetchedTrainer = null;

				ResultSet res = stm.executeQuery(sql);

				while (res.next()) {
					fetchedTrainer= new Trainer(
							res.getInt("trainer_ID"), 
							res.getString("name"), 
							res.getString("email"), 
							res.getString("phone"), 
							res.getInt("gym_Gym_Code"), 
							res.getString("specialty")
							);
				}				    
				return fetchedTrainer ;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	//change this to return an ArrayList
	public static ResultSet getTrainersByGymCode(int gymCode) {
		String sqlQuery= ("SELECT * FROM trainer WHERE Gym_code = "+ gymCode);
		ResultSet res= null;
		try {
			Connection con= SQLConnector.getConnection();
			Statement stm= con.createStatement();
			res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	public static ArrayList<Trainer> getTrainerByGymCode(int gymCode) {
		String sqlQuery= ("SELECT * FROM trainer WHERE gym_Gym_code = "+ gymCode);
		try (Connection con = SQLConnector.getConnection();
			 Statement stm = con.createStatement();){
			
			ResultSet res = stm.executeQuery(sqlQuery);
			ArrayList<Trainer> fetchedTrainers = new ArrayList<>();
			
			while(res.next()) {
				Trainer currentTrainer = new Trainer();
				
				currentTrainer.setTrainerID(res.getInt("trainer_ID"));
				currentTrainer.setName(res.getString("name"));
				currentTrainer.setGymCode(res.getInt("gym_Gym_code"));
				
				fetchedTrainers.add(currentTrainer);
			}
			
			return fetchedTrainers;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
