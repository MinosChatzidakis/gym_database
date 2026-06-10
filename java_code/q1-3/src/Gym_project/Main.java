package Gym_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
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
					searchAndDisplayGyms();
					break;
				case 2:
					System.out.println("\nSearch Trainers");
					break;
				case 3:
					System.out.println("\nSearch Available Sessions");
					SearchAvailableSessions(scanner);
					break;
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
		String selectedCity = scanner.nextLine();
		
		
		HashMap<String, Integer> gymMap = new HashMap<>(); //gym name, gym code
		ArrayList<Gym> foundGyms = GymDBUtils.getGymsByCity(selectedCity);
		if(foundGyms == null || foundGyms.isEmpty()) {
			System.out.println("No gyms were found in " + selectedCity);
		}else {
			for(Gym g : foundGyms) {
				System.out.println(g.getName());
				gymMap.put(g.getName().toLowerCase(), g.getGymCode());

			}
		}
		System.out.println("Enter Preferred Gym: ");
		String gymName = scanner.nextLine();
		
		int selectedGymCode = -1;
		if (gymMap.containsKey(gymName.toLowerCase())) {
			selectedGymCode = gymMap.get(gymName.toLowerCase());
		}else {
			System.out.println("The gym wasn't found");
		}
		
		System.out.println("Enter Training Type: ");
		String type = scanner.nextLine();
		
		System.out.println("Enter Date e.g. DD/MM/YYYY: ");
		String date = scanner.nextLine();
		
		System.out.println("Enter Time e.g. HH:MM: ");
		String time = scanner.nextLine();
		
		int selectedTrainerId = 0;
		if (selectedGymCode > 0) {
			TrainerDBUtils trainerUtils = new TrainerDBUtils();
			ArrayList<Trainer> rsTrainers = trainerUtils.getTrainerByGymCode(selectedGymCode);
			HashMap<String, Integer> trainerMap = new HashMap<>();
			
			if (rsTrainers != null && !rsTrainers.isEmpty()) {
				
				
				for (Trainer t : rsTrainers) {
					int tId = t.getTrainerID(); 
					String tName = t.getName();
					
					trainerMap.put(tName.toLowerCase(), tId);
					System.out.println("Available Trainer: " + tName);
				}
				
				System.out.println("Enter Preferred Trainer Name: ");
				String inputTrainerName = scanner.nextLine();
				
				if(!inputTrainerName.isEmpty() && trainerMap.containsKey(inputTrainerName.toLowerCase())) {
					selectedTrainerId = trainerMap.get(inputTrainerName.toLowerCase());
				}
			} else {
				System.out.println("No trainers found for this gym.");
			}
		
		}
		
		System.out.println("Enter Additional Services: ");
		String services = scanner.nextLine();
		
		System.out.println("Is Invoice Needed? (true/false):");
		boolean invoice = scanner.nextBoolean();
		scanner.nextLine();
		
		//begin search and display results
		SessionSearch criteria = new SessionSearch(selectedGymCode, selectedCity, type, date, time, selectedTrainerId, services,invoice);
		ArrayList<Session> availableSessions = SessionDBUtils.searchSessions(criteria);
		HashMap<Integer, Session> sessionsMap= new HashMap<>(); //number presented on the screen, selectedSession
		Session selectedSession= null; // session which the user wants to book
		if(availableSessions == null || availableSessions.isEmpty()) {
			System.out.println("No sessions that match your criteria found. Pleasy try again with different search terms... ");
		}else{ //sessions were found
			System.out.println("\n Available Sessions:");
			int num= 0;
			for(Session s : availableSessions) {
				System.out.printf("%-12d | %-15s | %-15s | %-15s | %-15s | %-20s\n", num, s.getGymCode(), s.getSessionType(), s.getDateAndTime(), s.getDuration(), s.getPrice() );
				sessionsMap.put(num, s);
			}
			System.out.println("Pick a session: ");
			String sessionChoice= scanner.nextLine();
			if(sessionsMap.containsKey(sessionChoice)){
				selectedSession= sessionsMap.get(sessionChoice);
			}
		}
		if(selectedSession != null) {
			Customer c= new Customer(-1, "", "", "", selectedSession.getGymCode());
			boolean err= true;
			//record customer's name and surname
			while(err) {
				System.out.println("Enter your full name: ");
				String name= scanner.nextLine();
				try {
					c.setName(name);
					err= false;
				}catch(IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
			err= true;
			while(err) {
				//record customer's phone
				System.out.println("Enter your phone number: ");
				String phone= scanner.nextLine();
				try {
					c.setPhone(phone);
					err= false;
				}catch(IllegalArgumentException e) {
					e.printStackTrace();
				}				
			}
			err= true;
			while(err) {
				//record customer's email
				System.out.println("Enter your email address: ");
				String email= scanner.nextLine();
				try {
					c.setEmail(email);
					err= false;
				}catch(IllegalArgumentException e) {
					e.printStackTrace();				
			}
				
			}
			
			String selectedGymName= GymDBUtils.getGymById(selectedSession.getGymCode()).getName();
			String selectedTrainerName= TrainerDBUtils.getTrainerByID(selectedSession.getTrainerTrainerID()).getName();
			System.out.println(
					"Your reservation?\nGym: "+ selectedGymName +"\nTraining type: "+ selectedSession.getSessionType()+"\nDate and time: "+selectedSession.getDateAndTime()+"\nTrainer:"+selectedTrainerName+"\nDuration: "+selectedSession.getDuration()+"mins"+"\nPrice: "+selectedSession.getPrice()+"$"
					);
			Character ans= ' ';
			while (Character.toUpperCase(ans) != 'Y' || Character.toUpperCase(ans) != 'N'){
				System.out.println("Confirm reservation? (Y/N)");
				ans= scanner.next().charAt(0);
			}
			
			
			
			
			
		}
		
		
	}
	
	private static void searchAndDisplayGyms() {
		System.out.println("\n Λίστα Γυμναστηρίων");
		
		ArrayList<Gym> gyms = GymDBUtils.getAllGymsSortedByCity();
		
		if(gyms ==null || gyms.isEmpty()) {
			System.out.println("Δεν βρέθηκαν γυμναστήρια στην βάση δεδομένων.");
			
		}else {
			System.out.printf("%-15s | %-20s | %-25s | %-15s | %-30s\n", 
                    "Πόλη", "Όνομα", "Διεύθυνση", "Τηλέφωνο", "Παροχές");
			
			for (Gym g : gyms) {
	            
	            System.out.printf("%-15s | %-20s | %-25s | %-15s | %-30s\n",
	                g.getCity(), g.getName(), g.getAddress(), g.getPhone(), g.getServices());
	        }
		}
		
	}

}
