//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class SessionDBUtils {
	//search for sessions that match the search criteria	
	public static ArrayList<Session> searchSessions(SessionSearch s) {
	    String gymServices = ServicesDBUtils.getUnifiedServicesByGymCode(s.getPreferredGymCode());
	    String requestedServices = s.getAdditionalServices();

	    if (requestedServices != null && !requestedServices.isEmpty()) {
	        if (gymServices == null || gymServices.isEmpty()) {
	        	System.out.println("Fail1");
	        	return new ArrayList<>();
	        }
	        List<String> offeredList = java.util.Arrays.asList(gymServices.split(","));
	        for (int i = 0; i < offeredList.size(); i++) {
	        	offeredList.set(i, offeredList.get(i).trim());
	        }
	        List<String> neededList = java.util.Arrays.asList(requestedServices.split(","));
	        for (int i = 0; i < neededList.size(); i++) {
	        	neededList.set(i, neededList.get(i).trim());
	        }

	        if (!offeredList.containsAll(neededList)) {
	            System.out.println("Fail2");
	        	return new ArrayList<>();
	        }
	    }
	    
	    System.out.println("Starting sb building");
	    StringBuilder sbQuery = new StringBuilder();
	    sbQuery.append("SELECT * FROM session WHERE availability = 1");
	    if (s.getPreferredGymCode() != -1) {
	        sbQuery.append(" AND gym_Gym_Code = ").append(s.getPreferredGymCode());
	    }
	    if (!s.getTrainingType().isEmpty()) {
	        sbQuery.append(" AND session_Type = '").append(s.getTrainingType()).append("'");
	    }
	    if (!s.getDate().isEmpty()) {
	        sbQuery.append(" AND date_And_Time LIKE '%").append(s.getDate()).append("%'");
	    }
	    if (s.getTrainerId() != -1) {
	        sbQuery.append(" AND trainer_Trainer_ID = ").append(s.getTrainerId());
	    }
	    sbQuery.append(";");
	    
	    ArrayList<Session> availableSessions = new ArrayList<>();
	    
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement();
	 		 ResultSet res = stm.executeQuery(sbQuery.toString())) {
	        
	    	System.out.println("final query: " + sbQuery.toString());
	        while (res.next()) {
	            // ΔΙΟΡΘΩΘΗΚΕ: Προστέθηκε η 11η παράμετρος (amount_of_participants) στο τέλος του Constructor
	            Session currentSession = new Session(
	                res.getInt("session_code"),
	                res.getString("session_type"),
	                res.getString("description"),
	                res.getInt("max_participants"),
	                res.getInt("duration"),
	                res.getFloat("price"),
	                res.getInt("availability"),
	                res.getInt("trainer_trainer_id"),
	                res.getInt("gym_Gym_Code"),
	                res.getObject("date_And_Time", LocalDateTime.class),
	                res.getInt("amount_Of_Participants")
	            );
	            availableSessions.add(currentSession);
	        }
	        return availableSessions;
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return null; 
	    }
	}

	public static void checkAndUpdateAvailability(Session session) {
		String countQuery = "SELECT COUNT(*) FROM reservation WHERE session_Session_Code = " + session.getSessionCode() 
        + " AND reservation_Status != 'CANCELLED';";
		
		try (Connection conn = SQLConnector.getConnection();
		         Statement stm = conn.createStatement()) {
		        
		        try (ResultSet res = stm.executeQuery(countQuery)) {
		            if (res.next()) {
		                int realParticipantsCount = res.getInt(1);
		                
		                session.setAmountOfParticipants(realParticipantsCount);
		                if (session.getAmountOfParticipants() >= session.getMaxParticipants()) {
		                    session.setAvailability(0);
		                    String updateQuery = "UPDATE session SET availability = 0 WHERE session_code = " + session.getSessionCode() + ";";
		                    stm.executeUpdate(updateQuery);
		                }
		            }
		        }
	
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//remove a participant and check availability	
	public static void freeUpSpaceInMultipleSessions(String sessionIds) throws SQLException{
		//if no IDs were passed => return
		if (sessionIds == null || sessionIds.length() == 0) {
	        System.out.println("No IDs provided.");
	        return;
	    }
		//remove one participant from each session and then check if it's still available
		String sqlQuery = "UPDATE sessionn SET amount_Of_Participants= amount_Of_Participants -1, availability = CASE WHEN amount_Of<Participants < max_Participants THEN 1 WHEN amount_Of<Participants = max_Participants THEN 0 END WHERE session_Code IN (" + sessionIds + ");";
	    try (Connection conn = SQLConnector.getConnection();
	         Statement stmt = conn.createStatement()) {
	        	
	        int rowsAffected = stmt.executeUpdate(sqlQuery);
	        if(rowsAffected>0) {
	        	System.out.println("Updated " + rowsAffected + " sessions successfully.");	        	
	        }else System.out.println("No rows matches the criteria");
		    }
	}
}