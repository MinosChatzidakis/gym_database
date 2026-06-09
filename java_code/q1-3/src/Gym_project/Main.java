package Gym_project;

import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//		Connection conn;
//		try {
//			conn = SQLConnector.getConnection();
//			if (conn != null) {
//            System.out.println("[ΕΠΙΤΥΧΙΑ] Η βάση είναι συνδεδεμένη και έτοιμη να δεχτεί δεδομένα!");
//            try { 
//                conn.close();
//            } catch (Exception e) {}
//        }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		Scanner scanner = new Scanner(System.in);
		boolean isRunning = true;
		
		
		while (isRunning) {
			System.out.println("\nMenu: ");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("0. Exit System");
			System.out.println("Select an option (0-2): ");
			
			int  choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1: 
					adminMenu(scanner);
					break;
					
				case 2:
					customerMenu(scanner);
					break;
					
			}
		}
	}
	
	
	private static void adminMenu(Scanner scanner) {
		boolean adminRunning = true;
		while(adminRunning) {
			System.out.println("1. Insert new Data");
			System.out.println("2. Search Gyms");
			System.out.println("3. Search Trainers");
			System.out.println("4. View Reserved Sessions");
			System.out.println("5. View Pending Reservations");
			System.out.println("6. Search Available Sessions");
			System.out.println("7. Excecute New Reservation");
			System.out.println("8. Update Reservations/Payments");
			System.out.println("9. Check Unpaid Reservations");
			System.out.println("10. Manage Cancelled Reservations");
			System.out.println("0. Back to Main Menu");
			
			int choice2 = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice2) {
				case 1:
					insertDataMenu(scanner);
					break;
			}
		}
	}
	
	private static void customerMenu(Scanner scanner) {
		boolean customerRunning = true;
		
		while(customerRunning) {
			System.out.println("1. Search Gyms");
			System.out.println("2. Search Trainers");
			System.out.println("3. Search Available Sessions");
			System.out.println("4. Excecute New Reservation");
			System.out.println("0. Logout / Back to Main Menu");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					System.out.println("\n Search Gyms");
					
				case 2:
					System.out.println("\nSearch Trainers");
					
				case 3:
					System.out.println("\nSearch Available Sessions");
					SearchAvailableSessions(scanner);
					
			}
		}
	}
	
	private static void insertDataMenu(Scanner scanner) {
		System.out.println("\nInsert/Update Data");
		System.out.println("1. Gym");
		System.out.println("2. Trainers");
		System.out.println("3. Sessions");
		System.out.println("4. Customers");
		System.out.println("5. Reservations");
		System.out.println("6. Reservation Payments");
		System.out.println("0. Back to Main Menu");
		
		System.out.println("Select an option (0-6):");
		
		
		int choice3 = scanner.nextInt();
		scanner.nextLine();
		
		switch(choice3) {
			case 1:
				System.out.println("\nAdd New Gym");
				
			case 2:
				System.out.println("\nAdd New Trainer");
				
			case 3:
				System.out.println("\n[-- Add New Session --]");
			case 4:
                System.out.println("\n[-- Add New Customer --]");
                
			case 5:
                System.out.println("\n[-- Add New Reservation --]");
                
			case 6:
                System.out.println("\n[-- Add New Payment --]");
                
			case 0:
                System.out.println("Returning to Main Menu...");
                break;
                
            default: 
            	System.out.println("Invalid option. Returning to Main Menu.");
		}
	}
	
	private static void SearchAvailableSessions(Scanner scanner) {
		
		System.out.println("Enter City: ");
		String city = scanner.nextLine();
		
		
		GymDBUtils gymUtils = new GymDBUtils();
		ResultSet rsGym = gymUtils.getGymsByCity(city);
		HashMap<String, Integer> gymMap = new HashMap<>();
		
		try {
			while (rsGym.next()) {
			    String gymName = rsGym.getString("Name");
			    int gymCode = rsGym.getInt("Gym_code");
			    
			    gymMap.put(gymName.toLowerCase(), gymCode);
			    System.out.println("Found Gym: " + gymName + "");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter Preferred Gym: ");
		String gymName = scanner.nextLine();
		
		int selectedGymCode = 0;
		if (gymMap.containsKey(gymName.toLowerCase())) {
			selectedGymCode = gymMap.get(gymName.toLowerCase());
		}else {
			System.out.println("The gym wasn't found");
		}
		
		System.out.println("Enter Training Type: ");
		String type = scanner.nextLine();
		
		System.out.println("Enter Date e.g. DD-MM-YYYY: ");
		String date = scanner.nextLine();
		
		System.out.println("Enter Time e.g. HH:MM: ");
		String time = scanner.nextLine();
		
		int selectedTrainerId = 0;
		if (selectedGymCode > 0) {
			TrainerDBUtils trainerUtils = new TrainerDBUtils();
			ResultSet rsTrainers = trainerUtils.getTrainerByGym(selectedGymCode);
			
			HashMap<String, Integer> trainerMap = new HashMap<>();
			
			try {
				while (rsTrainers.next()) {
					int tId = rsTrainers.getInt("Trainer_id");
					String tName = rsTrainers.getString("Name");
					trainerMap.put(tName.toLowerCase(), tId);
					
					System.out.println("Found Trainer: " +tName);
				}
				
				System.out.println("Enter Preferred Trainer Name: ");
				String inputTrainerName = scanner.nextLine();
				
				if(!inputTrainerName.isEmpty() && trainerMap.containsKey(inputTrainerName.toLowerCase())) {
					selectedTrainerId = trainerMap.get(inputTrainerName.toLowerCase());
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		}
		
		System.out.println("Enter Additional Services: ");
		String services = scanner.nextLine();
		
		System.out.println("Is Invoice Needed? (true/false):");
		boolean invoice = scanner.nextBoolean();
		scanner.nextLine();
		
		SessionSearch criteria = new SessionSearch(selectedGymCode, city, type, date, time, selectedTrainerId, services,invoice);
		SessionDBUtils sessionUtils = new SessionDBUtils();
		ResultSet finalRs = sessionUtils.searchSessions(criteria);
		try {
			System.out.println("\n Available Sessions:");
			boolean foundSessions = false;
			
			if(finalRs != null) {
				while(finalRs.next()) {
					foundSessions = true;
					int sessionId = finalRs.getInt("Session_Code");
					//String sDate = finalRs.getString("date");
					int sTime = finalRs.getInt("Time");
					String sType = finalRs.getString("Session_Type");
					//String sServices = finalRs.getString("services");
					
					System.out.printf("%-12d | %-12d | %-15s\n", sessionId, sTime, sType);
					
				}
				if (!foundSessions) {
					System.out.println("⚠️ Δεν βρέθηκε κανένα διαθέσιμο μάθημα με αυτά τα κριτήρια.");
				}
			}
		}catch (SQLException e) {
			System.out.println("❌ Σφάλμα κατά την εκτύπωση: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
