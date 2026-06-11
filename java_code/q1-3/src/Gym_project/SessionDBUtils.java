//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class SessionDBUtils {
	public static ArrayList<Session> searchSessions(SessionSearch s) {
	    String gymServices = ServicesDBUtils.getUnifiedServicesByGymCode(s.getPreferredGymCode()); // get the services this specific gym offers
	    String requestedServices = s.getAdditionalServices(); // get the services the customer is requesting

	    if (requestedServices != null && !requestedServices.isEmpty()) {
	        if (gymServices == null || gymServices.isEmpty()) {
	        	System.out.println("Fail1");
	        	return new ArrayList<>(); // Gym offers no services => return empty ArrayList
	        }
	        // split strings into Lists so we can compare the words
	        List<String> offeredList = java.util.Arrays.asList(gymServices.split(","));
	        for (int i = 0; i < offeredList.size(); i++) 
	        {
	        	offeredList.set(i, offeredList.get(i).trim());
	        }
	        List<String> neededList = java.util.Arrays.asList(requestedServices.split(","));
	        for (int i = 0; i < neededList.size(); i++) 
	        {
	        	neededList.set(i, neededList.get(i).trim());
	        }

	        // if the gym we care about doesn't have all the needed services No query needed
	        if (!offeredList.containsAll(neededList)) {
	            System.out.println("Fail2");
	        	return new ArrayList<>(); // ------------- the crash is here
	        }
	    }
	    System.out.println("Starting sb building");
	    StringBuilder sbQuery = new StringBuilder();
	    sbQuery.append("SELECT * FROM session WHERE availability = 1");
	    if (s.getPreferredGymCode() != -1) {
	        sbQuery.append(" AND gym_Gym_Code = ").append(s.getPreferredGymCode());
	        System.out.println("Appended: " + s.getPreferredGymCode());
	    }
	    if (!s.getTrainingType().isEmpty()) {
	        sbQuery.append(" AND session_Type = '").append(s.getTrainingType()).append("'");
	        System.out.println("Appended: " + s.getTrainingType());
	    }
	    if (!s.getDate().isEmpty()) {
	        sbQuery.append(" AND date_And_Time LIKE '%").append(s.getDate()).append("%'");
	        System.out.println("Appended: " + s.getDate());
	    }
	    if (s.getTrainerId() != -1) {
	        sbQuery.append(" AND trainer_Trainer_ID = ").append(s.getTrainerId());
	        System.out.println("Appended: " + s.getTrainerId());
	    }
	    sbQuery.append(";");
	    ArrayList<Session> availableSessions = new ArrayList<>();
	    
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement();
	    		ResultSet res = stm.executeQuery(sbQuery.toString())) { // Try-with-resources auto-closes everything
	        
	    	System.out.println("final query: "+ sbQuery.toString());
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
	        
	        return availableSessions;
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return null; 
	    }
	}
	
	
	public static void testQuery() {
		String sqlQuery= "SELECT * FROM session WHERE availability = 1 AND gym_Gym_Code = 7516 AND session_Type = 'PILATES' AND date_And_Time LIKE '%16/06/2026%' AND trainer_Trainer_ID = 2;";
		try {
			Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sqlQuery);
			while(res.next()) {
				System.out.println(res.getInt("session_Code"));
			}
			System.out.println("Done");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	}



