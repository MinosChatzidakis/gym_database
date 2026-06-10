//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class SessionDBUtils {
	public static ArrayList<Session> searchSessions(SessionSearch s) {
		StringBuilder sbQuery= new StringBuilder(); //init complex sql query
		//construct mySQL database query

		sbQuery.append("SELECT * FROM session WHERE availability = 1");
		//sbQuery.append(s.getCity().isEmpty() ? "" : " AND city = "+ s.getCity()); // city cannot be null either way -- handle that in data insertion
		sbQuery.append(s.getPreferredGymCode()==-1 ? "" : " AND gym_Gym_Code = '" + s.getPreferredGymCode() +"'"); // we need to first fetch the code of the gym provided - if provided
		sbQuery.append(s.getTrainingType().isEmpty() ? "" : " AND session_Type = '" + s.getTrainingType() +"'");
		sbQuery.append(s.getDate().isEmpty() ? "" : " AND date_And_Time LIKE '%" +  s.getDate() + "%'");
		sbQuery.append(s.getTrainerId()==-1 ? "" : " AND trainer_Trainer_id = '" + s.getTrainerId() + "'"); //check trainer id initialization
		//sbQuery.append(s.getAdditionalServices().isEmpty() ? "" : "services = "+ s.getAdditionalServices()); //this is probably an array - need to be careful -- right now a string
		//sbQuery.append(s.getInvoiceNeeded()==true ? "" : " AND invoiceNeeded = TRUE");
		
		
		String sqlQuery= sbQuery.toString();


		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement()){			
			ArrayList<Session> availableSessions = new ArrayList<>();
			ResultSet res = stm.executeQuery(sqlQuery);

			while (res.next()) {
			    Session currentSession = new Session(
			    		res.getInt("session_code"),
			    		res.getString("session_type"),
			    		res.getString("description"),
			    		res.getInt("max_participants"),
			    		res.getInt("duration"),
			    		res.getInt("price"),
			    		res.getBoolean("availability"),
			    		res.getInt("trainer_trainer_id"),
			    		res.getInt("gym_Gym_Code"),
			    		res.getString("date_And_Time")
			    		);
			    
			    availableSessions.add(currentSession);
			}

			res.close();
			conn.close(); //connection to database closed
			return availableSessions ;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null; // an error must have occurred -- return nothing
		}
	}
}



