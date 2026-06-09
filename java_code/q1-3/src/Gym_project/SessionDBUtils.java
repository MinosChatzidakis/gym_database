//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDBUtils {
	public ResultSet searchSessions(SessionSearch s) {
		StringBuilder sbQuery= new StringBuilder(); //init a new StringBuilder obj
		//construct mySQL database query
		sbQuery.append("SELECT * FROM session WHERE availability='T'");
		//sbQuery.append(s.getCity().isEmpty() ? "" : " AND city = "+ s.getCity()); // city cannot be null either way -- handle that in data insertion
		sbQuery.append(s.getPreferredGymCode()==-1 ? "" : " AND GYM_Gym_code = '"+ s.getPreferredGymCode() + "'"); // we need to first fetch the code of the gym provided - if provided
		sbQuery.append(s.getTrainingType().isEmpty() ? "" : " AND Session_Type = '"+ s.getTrainingType() + "'");

		sbQuery.append(s.getTrainerId()==-1 ? "" : " AND Trainer_Trainer_id = '"+ s.getTrainerId() + "'");
		
		
		String sqlQuery= sbQuery.toString();
		ResultSet res= null;
		try{
			Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement();
			res = stm.executeQuery(sqlQuery); //run the query on the database and store results
		//	conn.close(); //connection to database closed
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		return res;//this might be null
			//once the user chooses a session => create reservation and add a customer to the session in the db alter the availability field if necessary(taking into consideration the type of training session)

	}

	
}