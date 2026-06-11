package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicesDBUtils { // Αφαιρέθηκε το abstract

	// Επιστρέφει μια λίστα με αντικείμενα Services (χρήσιμο για γενική διαχείριση)
	public static ArrayList<Services> getServicesByGymCode(int gymCode){
		String sql = "SELECT service_Name FROM services WHERE gym_Gym_code = " + gymCode;
		try (Connection con = SQLConnector.getConnection();
			 Statement stm = con.createStatement();){
			ResultSet res = stm.executeQuery(sql);
			ArrayList<Services> fetchedServices = new ArrayList<>();
			while(res.next()) {
				Services currentService = new Services(
						res.getString("service_Name"),
						gymCode
				);
				fetchedServices.add(currentService);
			}
			return fetchedServices;
		} catch(SQLException e) {
			System.out.println("Error fetching services list:");
			e.printStackTrace();
		}
		return null;
	}

	// FIXED: Καθαρίστηκε από το Git Conflict. 
	// Επιστρέφει String μορφής "PILATES, YOGA" που χρειάζεται η SessionDBUtils
	public static String getUnifiedServicesByGymCode(int gymCode){
		String sql = "SELECT service_Name FROM services WHERE gym_Gym_code = " + gymCode;
		try (Connection con = SQLConnector.getConnection();
			 Statement stm = con.createStatement();){
			ResultSet res = stm.executeQuery(sql);
			StringBuilder fetchedServices = new StringBuilder();
			
			while(res.next()) {
				// Αν δεν είναι το πρώτο service, βάζουμε κόμμα για διαχωρισμό
				if (fetchedServices.length() > 0) {
					fetchedServices.append(", ");
				}
				fetchedServices.append(res.getString("service_Name"));
			}
			return fetchedServices.toString();
		} catch(SQLException e) {
			System.out.println("Error fetching unified services string:");
			e.printStackTrace();
		}
		return ""; 
	}
}