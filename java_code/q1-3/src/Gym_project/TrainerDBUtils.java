//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class TrainerDBUtils {
	public ArrayList<Trainer> getAllTrainers(){
		String sqlQuery= "SELECT name, trainer_id, gym_code FROM trainer ORDER BY Specialty";
		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
				Statement stm= conn.createStatement()){		
				ArrayList<Trainer> fetchedTrainers = new ArrayList<>();

				ResultSet res = stm.executeQuery(sqlQuery);

				while (res.next()) {
				    Trainer currentTrainer = new Trainer();
				    
				    currentTrainer.setName( res.getString("name") );
				    currentTrainer.setTrainerID( res.getInt("trainer_id") );
				    currentTrainer.setGymCode( res.getInt("gym_code") );
				    
				    fetchedTrainers.add(currentTrainer);
				}

				return fetchedTrainers ;
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
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
