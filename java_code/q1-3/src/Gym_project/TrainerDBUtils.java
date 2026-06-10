//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrainerDBUtils {
	public ArrayList<Trainer> getAllTrainers(){
		String sqlQuery= "SELECT name, trainer_ID, gym_Gym_code FROM trainer ORDER BY Specialty";
		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
				Statement stm= conn.createStatement()){			
				ArrayList<Trainer> fetchedTrainers = new ArrayList<>();


				ResultSet res = stm.executeQuery(sqlQuery);

				while (res.next()) {
				    Trainer currentTrainer = new Trainer();
				    
				    currentTrainer.setName( res.getString("name") );
				    currentTrainer.setTrainerID( res.getInt("trainer_ID") );
				    currentTrainer.setGymCode( res.getInt("gym_Gym_code") );
				    
				    // Add that finished page to your Notepad
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
