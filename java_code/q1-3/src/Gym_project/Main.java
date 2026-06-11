package Gym_project;

import java.util.ArrayList;
import java.util.HashMap;
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
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					//SessionDBUtils.testQuery();
					adminMenu(scanner);
					break;
					
				case 2:
					customerMenu(scanner);
					break;
					
				case 0: 
			        System.out.println("Exiting system. Goodbye!");
			        isRunning = false; 
			        break;
			        
			    default: 
			        System.out.println("Invalid choice. Please select 0, 1, or 2.");
					
			}
		}
	}
	
	private static void adminMenu(Scanner scanner) {
		boolean adminRunning = true;
		while(adminRunning) {
			System.out.println("1. Insert new Data");
			System.out.println("2. Update Data");
			System.out.println("3. Delete Data");
			System.out.println("4. Search Gyms");
			System.out.println("5. Search Trainers");
			System.out.println("6. View Reserved Sessions");
			System.out.println("7. View Pending Reservations");
			System.out.println("8. Search Available Sessions");
			System.out.println("9. Excecute New Reservation");
			System.out.println("10. Update Reservations/Payments");
			System.out.println("11. Check Unpaid Reservations");
			System.out.println("12. Manage Cancelled Reservations");
			System.out.println("0. Back to Main Menu");
			
			int choice2 = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice2) {
				case 1:
					insertDataMenu(scanner);
					break;
				case 2:
					updateDataMenu(scanner);
					break;
				case 3:
					deleteDataMenu(scanner);
					break;
				case 0:
					return;
					
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
					System.out.println("\nSearch Gyms");
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
				addGym(scanner);
				break;
			case 2:
				System.out.println("\nAdd New Trainer");
				addTrainer(scanner);
				break;
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
	
	private static void updateDataMenu(Scanner scanner) {
		System.out.println("\nUpdate Data");
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
				System.out.println("\nUpdate Gym");
				updateGym(scanner); 
				break;
			case 2:
				System.out.println("\nUpdate Trainer");
				updateTrainer(scanner);
				break;
			case 3:
				System.out.println("\n[-- Update Session --]");
				break;
			case 4:
                System.out.println("\n[-- Update Customer --]");
                break;
			case 5:
                System.out.println("\n[-- Update Reservation --]");
                break;
			case 6:
                System.out.println("\n[-- Update Payment --]");
                break;
			case 0:
                System.out.println("Returning to Main Menu...");
                break;
                
            default: 
            	System.out.println("Invalid option. Returning to Main Menu.");
		}
	}
	
	private static void deleteDataMenu(Scanner scanner) {
		System.out.println("\nDelete Data");
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
				System.out.println("\nDelete Gym");
				deleteGym(scanner); 
				break;
			case 2:
				System.out.println("\nDelete Trainer");
				deleteTrainer(scanner);
				break;
			case 3:
				System.out.println("\n[-- Delete Session --]");
				break;
			case 4:
                System.out.println("\n[-- Delete Customer --]");
                break;
			case 5:
                System.out.println("\n[-- Delete Reservation --]");
                break;
			case 6:
                System.out.println("\n[-- Delete Payment --]");
                break;
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
		System.out.println("Enter your preferred gym's name: ");
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
			ArrayList<Trainer> rsTrainers = TrainerDBUtils.getTrainerByGymCode(selectedGymCode);
			HashMap<String, Integer> trainerMap = new HashMap<>();
			
			if (rsTrainers != null && !rsTrainers.isEmpty()) {
				
				System.out.println("This gym's trainers:");
				for (Trainer t : rsTrainers) {
					int tId = t.getTrainerID(); 
					String tName = t.getName();
					
					trainerMap.put(tName.toLowerCase(), tId);
					System.out.println(tName);
				}
				
				System.out.println("Enter your preferred trainer's name: ");
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
				System.out.println("No gyms were found");
			
			}else {
					System.out.printf("%-15s | %-20s | %-25s | %-15s | %-30s\n", 
                    "City", "Name", "Address", "Phone", "Services");
			
				for (Gym g : gyms) {
	            
	            	System.out.printf("%-15s | %-20s | %-25s | %-15s | %-30s\n",
	                g.getCity(), g.getName(), g.getAddress(), g.getPhone(), g.getServices());
				}
			}
		
		

	}

	public static void addGym(Scanner scanner) {
		System.out.println("--- Insert New Gym ---");
    
    	System.out.print("Enter Name: ");
    	String name = scanner.nextLine();
    	
    	System.out.print("Enter Address: ");
    	String address = scanner.nextLine();
    
    	System.out.print("Enter City: ");
    	String city = scanner.nextLine();
    
    	System.out.print("Enter Phone: ");
    	String phone = scanner.nextLine();
    	
    	System.out.print("Enter Email: ");
    	String email = scanner.nextLine();
    
    	// Περνάμε 0 ως ID, καθώς θα παραχθεί αυτόματα από τη MySQL
    	Gym newGym = new Gym(city, "None", address, name, email, phone, 0);
    
    	// Κλήση της GymDBUtils
    	GymDBUtils.addGym(newGym);
	}


	public static void updateGym(Scanner scanner) {
		System.out.println("--- Update Gym Data ---");
    
		System.out.print("Enter Gym Code to modify (gym_code): ");
		int gymCode = scanner.nextInt();
		scanner.nextLine(); // Καθαρισμός του buffer
    
		// Έλεγχος αν το γυμναστήριο υπάρχει όντως στη βάση πριν ζητήσουμε τα νέα στοιχεία
		Gym existingGym = GymDBUtils.getGymById(gymCode);
		if (existingGym == null) {
			System.out.println("No gym was found with this code.");
			return;
		}
    
		boolean subRunning = true;
		while (subRunning) {
			System.out.println("\n--- Current Gym Data ---");
			System.out.println("Name: " + existingGym.getName());
			System.out.println("Address: " + existingGym.getAddress());
			System.out.println("City: " + existingGym.getCity());
			System.out.println("Phone: " + existingGym.getPhone());
        	System.out.println("Email: " + existingGym.getEmail());
        	System.out.println("-------------------------------------");
        
        	System.out.println("Which field do you want to modify?");
        	System.out.println("1. Name");
        	System.out.println("2. Address");
        	System.out.println("3. City");
        	System.out.println("4. Phone");
        	System.out.println("5. Email");
        	System.out.println("0. Save Changes & Exit");
        	System.out.print("Choice (0-5): ");
        
        	int subChoice = scanner.nextInt();
        	scanner.nextLine(); // Καθαρισμός του buffer
        
        	switch (subChoice) {
            	case 1:
                	System.out.print("Enter New Name: ");
                	existingGym.setName(scanner.nextLine());
                	break;
            	case 2:
                	System.out.print("Enter New Address: ");
                	existingGym.setAddress(scanner.nextLine());
                	break;
            	case 3:
                	System.out.print("Enter New City: ");
                	existingGym.setCity(scanner.nextLine());
                	break;
            	case 4:
                	System.out.print("Enter New Phone: ");
                	existingGym.setPhone(scanner.nextLine());
                	break;
            	case 5:
                	System.out.print("Enter New Email: ");
                	existingGym.setEmail(scanner.nextLine());
                	break;
            	case 0:
                	System.out.println("Saving changes to the database...");
                	subRunning = false; // Σπάει το while loop
                	break;
            	default:
                	System.out.println("Invalid choice. Please try again.");
        	}
    	}
    
    	// Κλήση της GymDBUtils ΜΙΑ ΦΟΡΑ στο τέλος, αφού ο χρήστης πάτησε 0 και βγήκε
    	GymDBUtils.updateGym(existingGym);
	}

 
	public static void deleteGym(Scanner scanner) {
		System.out.println("--- Delete Gym ---");
    
		System.out.print("Enter Gym Code to delete (gym_code): ");
		int gymCode = scanner.nextInt();
		scanner.nextLine(); // Καθαρισμός του buffer
    
		// Έλεγχος αν το γυμναστήριο υπάρχει στη βάση
		Gym existingGym = GymDBUtils.getGymById(gymCode);
		if (existingGym == null) {
			System.out.println("No gym was found with this code.");
			return;
		}
    
    	// Επιβεβαίωση διαγραφής
    	System.out.println("You are about to delete the gym: " + existingGym.getName() + " in " + existingGym.getCity());
    	System.out.print("Are you sure? This action cannot be undone! (Y/N): ");
    	String confirm = scanner.nextLine().trim().toUpperCase();
    
    	if (confirm.equals("Y")) {
    		GymDBUtils.deleteGym(gymCode);// Κλήση της GymDBUtils για τη διαγραφή
    	} else {
    		System.out.println("Deletion cancelled.");
    	}
	}

	public static void addTrainer(Scanner scanner) {
	    System.out.println("--- Insert New Trainer ---");
	    
	    System.out.print("Enter First Name: ");
	    String name = scanner.nextLine();
	    
	    System.out.print("Enter Surname: ");
	    String surname = scanner.nextLine(); // Collected the surname
	    
	    System.out.print("Enter Specialty: ");
	    String specialty = scanner.nextLine();
	    
	    System.out.print("Enter Phone: ");
	    String phone = scanner.nextLine();
	    
	    System.out.print("Enter Email: ");
	    String email = scanner.nextLine();
	    
	    System.out.print("Enter Gym Code where this trainer works: ");
	    int gymCode = scanner.nextInt();
	    scanner.nextLine(); // Clear scanner buffer

	    // Check if the target gym exists to protect database integrity
	    if (GymDBUtils.getGymById(gymCode) == null) {
	        System.out.println("Error: No gym exists with code " + gymCode + ". Trainer insertion aborted.");
	        return;
	    }
	    
	    // Creating the Trainer object with the surname included
	    Trainer newTrainer = new Trainer(0, name, surname, email, phone, gymCode, specialty);
	    
	    // Dispatch to Database utilities
	    TrainerDBUtils.addTrainer(newTrainer);
	}

	public static void updateTrainer(Scanner scanner) {
    System.out.println("--- Update Trainer Data ---");
    
    System.out.print("Enter Trainer ID to modify: ");
    int trainerId = scanner.nextInt();
    scanner.nextLine(); // Καθαρισμός του buffer
    
    // Αναζήτηση του προπονητή στη βάση δεδομένων
    Trainer existingTrainer = TrainerDBUtils.getTrainerByID(trainerId);
    if (existingTrainer == null) {
        System.out.println("No trainer was found with this ID.");
        return;
    }
    
    boolean subRunning = true;
    while (subRunning) {
        System.out.println("\n--- Current Trainer Data ---");
        System.out.println("1. First Name: " + existingTrainer.getName());
        System.out.println("2. Surname: " + existingTrainer.getSurname());
        System.out.println("3. Specialty: " + existingTrainer.getSpecialty());
        System.out.println("4. Phone: " + existingTrainer.getPhone());
        System.out.println("5. Email: " + existingTrainer.getEmail());
        System.out.println("6. Gym Code: " + existingTrainer.getGymCode());
        System.out.println("-------------------------------------");
        
        System.out.println("Which field do you want to modify?");
        System.out.println("1. First Name");
        System.out.println("2. Surname");
        System.out.println("3. Specialty");
        System.out.println("4. Phone");
        System.out.println("5. Email");
        System.out.println("6. Gym Code");
        System.out.println("0. Save Changes & Exit");
        System.out.print("Choice (0-6): ");
        
        int subChoice = scanner.nextInt();
        scanner.nextLine(); // Καθαρισμός του buffer
        
        switch (subChoice) {
            case 1:
                System.out.print("Enter New First Name: ");
                existingTrainer.setName(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter New Surname: ");
                existingTrainer.setSurname(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter New Specialty: ");
                existingTrainer.setSpecialty(scanner.nextLine());
                break;
            case 4:
                System.out.print("Enter New Phone: ");
                existingTrainer.setPhone(scanner.nextLine());
                break;
            case 5:
                System.out.print("Enter New Email: ");
                existingTrainer.setEmail(scanner.nextLine());
                break;
            case 6:
                System.out.print("Enter New Gym Code: ");
                int newGymCode = scanner.nextInt();
                scanner.nextLine(); // Καθαρισμός του buffer
                
                // Έλεγχος ακεραιότητας: Υπάρχει το νέο γυμναστήριο;
                if (GymDBUtils.getGymById(newGymCode) == null) {
                    System.out.println("Error: Target gym does not exist. Gym Code not changed.");
                } else {
                    existingTrainer.setGymCode(newGymCode);
                }
                break;
            case 0:
                System.out.println("Saving changes to the database...");
                subRunning = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        	}
    	}
    
    	// Κλήση της TrainerDBUtils για την εκτέλεση του UPDATE
    	TrainerDBUtils.updateTrainer(existingTrainer);
	}
	
	public static void deleteTrainer(Scanner scanner) {
	    System.out.println("--- Delete Trainer ---");
	    
	    System.out.print("Enter Trainer ID to delete: ");
	    int trainerId = scanner.nextInt();
	    scanner.nextLine(); // Καθαρισμός του buffer
	    
	    // ΔΙΟΡΘΩΘΗΚΕ: Έλεγχος αν ο προπονητής υπάρχει στη βάση με τη σωστή μέθοδο
	    Trainer existingTrainer = TrainerDBUtils.getTrainerByID(trainerId);
	    if (existingTrainer == null) {
	        System.out.println("No trainer was found with this ID.");
	        return;
	    }
	    
	    // ΔΙΟΡΘΩΘΗΚΕ: Επιβεβαίωση διαγραφής με τα σωστά στοιχεία του προπονητή
	    System.out.println("You are about to delete the trainer: " + existingTrainer.getName() + " " + existingTrainer.getSurname() + " (" + existingTrainer.getSpecialty() + ")");
	    System.out.print("Are you sure? This action cannot be undone! (Y/N): ");
	    String confirm = scanner.nextLine().trim().toUpperCase();
	    
	    if (confirm.equals("Y")) {
	        // Κλήση της TrainerDBUtils για τη διαγραφή
	        TrainerDBUtils.deleteTrainer(trainerId);
	    } else {
	        System.out.println("Deletion cancelled.");
	    }
	}
	
}

