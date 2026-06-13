package Gym_project;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
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
					adminMenu();
					break;
					
				case 2:
					customerMenu();
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
	
	private static void adminMenu() {
		boolean adminRunning = true;
		while(adminRunning) {
			System.out.println("1. Insert new Data");
			System.out.println("2. Update Data");
			System.out.println("3. Search Gyms");
			System.out.println("4. Search Trainers");
			System.out.println("5. View Reserved Sessions");
			System.out.println("6. View Pending Reservations");
			System.out.println("7. Search Available Sessions");
			System.out.println("8. Excecute New Reservation");
			System.out.println("9. Update Reservations/Payments");
			System.out.println("10. Check Unpaid Reservations");
			System.out.println("11. Manage Cancelled Reservations");
			System.out.println("0. Back to Main Menu");
			
			int choice2 = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice2) {
				case 1:
					insertDataMenu();
					break;
				case 2:
					updateDataMenu();
					break;
				case 1010:
					handleUnpaidReservations();
					//manuallyRecordPayment();
					break;
				case 1020:
					manuallyRecordPayment();
					break;
				case 1030:
					handleCancelledReservations();
					break;
				case 3:
					System.out.println("\n Search Gyms");
					searchAndDisplayGyms();
					break;
				case 4:
					System.out.println("\nSearch Trainers");
					searchAndDisplayTrainers();
					break;
				case 5:
					System.out.println("\nView Reserved Sessions");
					viewActiveReservations(); 
				    break;
				case 6:
					System.out.println("\nView Pending Reservations");
					manuallyRecordPayment(); 
				    break;
				case 7:
					System.out.println("\nSearch Available Sessions");
					SearchAvailableSessions();
					break;
				case 9:
					System.out.println("\nUpdate Reservations/Payments");
					updateReservationsOrPayments();
				case 0:
					return;
			}
		}
	}
	
	private static void customerMenu() {
		boolean customerRunning = true;
		
		while(customerRunning) {
			System.out.println("1. Search Gyms");
			System.out.println("2. Search Trainers");
			System.out.println("3. Search Available Sessions");
			System.out.println("4. Cancel Reservation");
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
					searchAndDisplayTrainers();
					break;
				case 3:
					System.out.println("\nSearch Available Sessions");
					SearchAvailableSessions();
					break;
					
				case 4:
					System.out.println("\nCancel Reservations");
					manageCustomerCancellation();
				case 0: 
					return;
					
			}
		}
	}
	
	private static void insertDataMenu() {
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
				addGym();
				break;
			case 2:
				System.out.println("\nAdd New Trainer");
				addTrainer();
				break;
			case 3:
				System.out.println("\nAdd New Session");
				addSession();
				break;
			case 4:
                System.out.println("\nAdd New Customer ");
                addCustomer();
                break;
			case 5:
                System.out.println("\nAdd New Reservation");
                addReservation();
                break;
			case 6:
                System.out.println("\nAdd New Payment ");
                addPayment();
                break;
			case 0:
                System.out.println("Returning to Main Menu...");
                break;
                
            default: 
            	System.out.println("Invalid option. Returning to Main Menu.");
		}
	}
	
	private static void updateDataMenu() {
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
				updateGym(); 
				break;
			case 2:
				System.out.println("\nUpdate Trainer");
				updateTrainer();
				break;
			case 3:
				System.out.println("\nUpdate Session ");
				updateSession();
				break;
			case 4:
                System.out.println("\nUpdate Customer");
                updateCustomer();
                break;
			case 5:
                System.out.println("\nUpdate Reservation ");
                updateReservation();
                break;
			case 6:
                System.out.println("\nUpdate Payment ");
                break;
			case 0:
                System.out.println("Returning to Main Menu...");
                return;
                
            default: 
            	System.out.println("Invalid option. Returning to Main Menu.");
		}
	}
	
	private static void SearchAvailableSessions( ) {
		
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
			System.out.printf("%-12s | %-15s | %-15s | %-20s | %-20s | %-20s | %-25s\n", 
                    "Number", "Gym Code", "Sessions Type", "Date & Time", "Duration(minutes)", "Price(€)", "Gym Services");
			int num= 0;
			for(Session s : availableSessions) {
				
				String gymServices = ServicesDBUtils.getUnifiedServicesByGymCode(s.getGymCode());
				if (gymServices == null || gymServices.isEmpty()) {
					gymServices = "-";
				}
				System.out.printf("%-12d | %-15s | %-15s | %-20s | %-20s | %-20s | %-25s\n", num, s.getGymCode(), s.getSessionType(), s.getDateAndTime(), s.getDuration(), s.getPrice(), gymServices);
				sessionsMap.put(num, s);
				num++;
			}
			System.out.println("Pick a session: ");
			int sessionChoice= scanner.nextInt();
			scanner.nextLine();
			if(sessionsMap.containsKey(sessionChoice)){
				selectedSession= sessionsMap.get(sessionChoice);
			}else {
				System.out.println("Invalid Session");
				return;
			}
		}
		if(selectedSession != null) {
			Customer c = new Customer(-1, "", "", "", "", selectedSession.getGymCode());
			boolean err= true;
			// Καταγραφή του ονόματος και του επωνύμου του πελάτη
			while(err) {
			    System.out.print("Enter your First Name: ");
			    String firstName = scanner.nextLine();
			    System.out.print("Enter your Surname: ");
			    String lastName = scanner.nextLine();
			    try {
			        c.setName(firstName);
			        c.setSurname(lastName); 
			        err = false;
			    } catch(IllegalArgumentException e) {
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
			
			System.out.println("Gym Code: " + selectedSession.getGymCode());
	        System.out.println("Training Type: " + selectedSession.getSessionType());
	        System.out.println("Date and Time: " + selectedSession.getDateAndTime());
	        System.out.println("Duration: " + selectedSession.getDuration() + " mins");
	        System.out.println("Total Price: " + selectedSession.getPrice() + " €");
	        System.out.println("Invoice Needed: " + (invoice ? "YES" : "NO"));
	        
			Character ans= ' ';
			while (Character.toUpperCase(ans) != 'Y' && Character.toUpperCase(ans) != 'N'){
				System.out.println("Confirm reservation? (Y/N)");
				String input = scanner.nextLine().trim();
				if(!input.isEmpty()) {
					ans = input.charAt(0);
				}
			}
			
			if (Character.toUpperCase(ans) == 'Y') {
				
				System.out.println("\nSelect Payment Option:");
				System.out.println("1. Pay Online Now");
				System.out.println("2. Pay Later (Within 24 hours before the session");
				
				int payChoice = scanner.nextInt();
				scanner.nextLine();
				
				ReservationStatus reservationStatus;
				PaymentStatus paymentStatus;
				PaymentMethods paymentMethod;
				
				if (payChoice == 1) {
					reservationStatus = ReservationStatus.COMPLETE;
					paymentStatus = PaymentStatus.CONFIRMED;
					
				}else {
					reservationStatus = ReservationStatus.PENDING;
					paymentStatus = PaymentStatus.PENDING;
					
				}
				paymentMethod = null;
				while(paymentMethod == null) {
					System.out.println("\nHow do you intend to pay?");
					System.out.println("1. Card");
					System.out.println("2. Cash");
					System.out.println("3. Bank Transfer");
				  	
					int methodChoice = scanner.nextInt();
					scanner.nextLine();
				  	
					switch(methodChoice) {
						case 1:
							paymentMethod = PaymentMethods.CASH;
							break;
						case 2: 
							paymentMethod = PaymentMethods.CREDIT_CARD;
							break;
						case 3:
							paymentMethod = PaymentMethods.BANK_TRANSFER;
							break;
					}
				} 
				int generatedCustomerId = CustomerDBUtils.addCustomerAndGetId(c);
				
				if(generatedCustomerId > 0) {
					
					Reservation r = new Reservation(-1 , selectedSession.getDateAndTime(), invoice, reservationStatus, selectedSession.getSessionCode(), generatedCustomerId);
					try {
						int generatedReservationCode = ReservationDBUtils.addReservationAndGetCode(r);
						SessionDBUtils.checkAndUpdateAvailability(selectedSession);
						r.setReservationCode(generatedReservationCode);
						handleNewPayment(r, selectedSession, paymentMethod, paymentStatus); // create and store new payment 
						
					}catch(SQLException e) {
						System.out.println("Error in recording reservation. Please try again later.");
						return;
					}
				}
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

	public static void addGym() {
		System.out.println("Insert New Gym :\n");
    
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


	public static void updateGym() {
		System.out.println("Update Gym Data : \n");
    
		System.out.print("Enter the Gym's Code that you want to modify : ");
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
			System.out.println("\nCurrent Gym Data : \n");
			System.out.println("Name: " + existingGym.getName());
			System.out.println("Address: " + existingGym.getAddress());
			System.out.println("City: " + existingGym.getCity());
			System.out.println("Phone: " + existingGym.getPhone());
        	System.out.println("Email: " + existingGym.getEmail());
        	System.out.println("\n");
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

	public static void addTrainer() {
	    System.out.println("Insert New Trainer :\n ");
	    
	    System.out.print("Enter First Name: ");
	    String name = scanner.nextLine();
	    
	    System.out.print("Enter Surname: ");
	    String surname = scanner.nextLine(); 
	    
	    System.out.print("Enter Specialty: ");
	    String specialty = scanner.nextLine();
	    
	    System.out.print("Enter Phone: ");
	    String phone = scanner.nextLine();
	    
	    System.out.print("Enter Email: ");
	    String email = scanner.nextLine();
	    
	    System.out.print("Enter Gym Code where this trainer works: ");
	    int gymCode = scanner.nextInt();
	    scanner.nextLine(); 

	   
	    if (GymDBUtils.getGymById(gymCode) == null) {
	        System.out.println("Error: No gym exists with code " + gymCode + ". Trainer insertion aborted.");
	        return;
	    }
	    
	   
	    Trainer newTrainer = new Trainer(0, name, surname, email, phone, gymCode, specialty);
	    
	    
	    TrainerDBUtils.addTrainer(newTrainer);
	}

	public static void updateTrainer() {
    System.out.println("Update Trainer Data : \n");
    
    System.out.print("Enter Trainer ID to modify : ");
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
        System.out.println("\n Current Trainer Data : \n");
        System.out.println("1. First Name: " + existingTrainer.getName());
        System.out.println("2. Surname: " + existingTrainer.getSurname());
        System.out.println("3. Specialty: " + existingTrainer.getSpecialty());
        System.out.println("4. Phone: " + existingTrainer.getPhone());
        System.out.println("5. Email: " + existingTrainer.getEmail());
        System.out.println("6. Gym Code: " + existingTrainer.getGymCode());
        System.out.println("\n");
        
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
	
	
	public static void addCustomer() {
	    System.out.println("Insert New Customer : \n");
	    
	    System.out.print("Enter First Name: ");
	    String name = scanner.nextLine();
	    
	    System.out.print("Enter Surname: ");
	    String surname = scanner.nextLine(); 
	    
	    System.out.print("Enter Phone: ");
	    String phone = scanner.nextLine();
	    
	    System.out.print("Enter Email: ");
	    String email = scanner.nextLine();
	    
	    System.out.print("Enter Gym Code where this Customer is registered: ");
	    int gymCode = scanner.nextInt();
	    scanner.nextLine(); // Καθαρισμός του buffer 

	    // Έλεγχος αν το γυμναστήριο υπάρχει όντως στη βάση
	    if (GymDBUtils.getGymById(gymCode) == null) {
	        System.out.println("Error: No gym exists with code " + gymCode + ". Customer insertion aborted.");
	        return;
	    }
	    
	    // Δημιουργία του αντικειμένου Customer (0 για το AUTO_INCREMENT id)
	    Customer newCustomer = new Customer(0, name, surname, email, phone, gymCode);
	    
	    // Κλήση της CustomerDBUtils
	    CustomerDBUtils.addCustomer(newCustomer);
	}
	
	public static void updateCustomer() {
	    System.out.println("Update Customer Data : \n");
	    
	    System.out.print("Enter Customer ID to modify : ");
	    int customerId = scanner.nextInt();
	    scanner.nextLine(); // Καθαρισμός του buffer
	    
	    //Αναζήτηση πελάτη 
	    Customer existingCustomer = CustomerDBUtils.getCustomerByID(customerId);
	    if (existingCustomer == null) {
	        System.out.println("No customer was found with this ID.");
	        return;
	    }
	    
	    boolean subRunning = true;
	    while (subRunning) {
	        System.out.println("\nCurrent Customer Data : \n");
	        System.out.println("1. First Name: " + existingCustomer.getName());
	        System.out.println("2. Surname: " + existingCustomer.getSurname());
	        System.out.println("3. Phone: " + existingCustomer.getPhone());
	        System.out.println("4. Email: " + existingCustomer.getEmail());
	        System.out.println("5. Gym Code: " + existingCustomer.getGymCode());
	        System.out.println("\n");
	        
	        System.out.println("Which field do you want to modify?");
	        System.out.println("1. First Name");
	        System.out.println("2. Surname");
	        System.out.println("3. Phone");
	        System.out.println("4. Email");
	        System.out.println("5. Gym Code");
	        System.out.println("0. Save Changes & Exit");
	        System.out.print("Choice (0-5): ");
	        
	        int subChoice = scanner.nextInt();
	        scanner.nextLine(); // Καθαρισμός του buffer
	        
	        switch (subChoice) {
	            case 1:
	                System.out.print("Enter New First Name: ");
	                existingCustomer.setName(scanner.nextLine());
	                break;
	            case 2:
	                System.out.print("Enter New Surname: ");
	                existingCustomer.setSurname(scanner.nextLine());
	                break;
	            case 3:
	                System.out.print("Enter New Phone: ");
	                existingCustomer.setPhone(scanner.nextLine());
	                break;
	            case 4:
	                System.out.print("Enter New Email: ");
	                existingCustomer.setEmail(scanner.nextLine());
	                break;
	            case 5:
	                System.out.print("Enter New Gym Code: ");
	                int newGymCode = scanner.nextInt();
	                scanner.nextLine(); // Καθαρισμός του buffer
	                
	                // Έλεγχος ακεραιότητας: Υπάρχει το γυμναστήριο;
	                if (GymDBUtils.getGymById(newGymCode) == null) {
	                    System.out.println("Error: Target gym does not exist. Gym Code not changed.");
	                } else {
	                    existingCustomer.setGymCode(newGymCode);
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
	    
	    CustomerDBUtils.updateCustomer(existingCustomer);
	}
	
	public static void addSession() {
	    System.out.println("Insert New Session : \n");
	    
	    System.out.print("Enter Session Type (e.g., Yoga, CrossFit): ");
	    String type = scanner.nextLine();
	    
	    System.out.print("Enter Description: ");
	    String description = scanner.nextLine();
	    
	    System.out.print("Enter Max Participants: ");
	    int maxPart = scanner.nextInt();
	    
	    System.out.print("Enter Duration (in minutes): ");
	    int duration = scanner.nextInt();
	    
	    System.out.print("Enter Price ($): ");
	    float price = scanner.nextInt();
	    scanner.nextLine(); 
	    
	    System.out.print("Enter Date and Time (e.g., DD/MM/YYYY HH:MM): ");
	    String dateTime = scanner.nextLine();
	    
	    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    java.time.LocalDateTime sessionDateTime;
	    
	    try {
	        sessionDateTime = java.time.LocalDateTime.parse(dateTime, formatter);
	    } catch (java.time.format.DateTimeParseException e) {
	        System.out.println("❌ Error: Invalid date/time format. Session creation aborted.");
	        return; 
	    }
	    System.out.print("Enter Gym Code for this session: ");
	    int gymCode = scanner.nextInt();
	    
	   
	    if (GymDBUtils.getGymById(gymCode) == null) {
	        System.out.println("Error: Gym with code " + gymCode + " does not exist. Session creation aborted.");
	        return;
	    }
	    
	    System.out.print("Enter Trainer ID for this session: ");
	    int trainerId = scanner.nextInt();
	    scanner.nextLine(); 
	    
	   
	    if (TrainerDBUtils.getTrainerByID(trainerId) == null) {
	        System.out.println("Error: Trainer with ID " + trainerId + " does not exist. Session creation aborted.");
	        return;
	    }
	    
	   
	    Session newSession = new Session(0, type, description, maxPart, duration, price, 1, trainerId, gymCode, sessionDateTime, 0);
	    
	    
	    SessionDBUtils.addSession(newSession);
	}
	
	public static void updateSession() {
	    System.out.println("Update Session Data : \n");
	    
	    System.out.print("Enter Session Code to modify : ");
	    int sessionCode = scanner.nextInt();
	    scanner.nextLine(); 
	    
	    
	    Session existingSession = SessionDBUtils.getSessionByID(sessionCode);
	    if (existingSession == null) {
	        System.out.println("No session was found with this Code.");
	        return;
	    }
	    
	    boolean subRunning = true;
	    while (subRunning) {
	        System.out.println("\nCurrent Session Data : \n");
	        System.out.println("1. Session Type: " + existingSession.getSessionType());
	        System.out.println("2. Description: " + existingSession.getDescription());
	        System.out.println("3. Max Participants: " + existingSession.getMaxParticipants());
	        System.out.println("4. Duration: " + existingSession.getDuration() + " mins");
	        System.out.println("5. Price: $" + existingSession.getPrice());
	        System.out.println("6. Date & Time: " + existingSession.getDateAndTime());
	        System.out.println("7. Gym Code: " + existingSession.getGymCode());
	        System.out.println("8. Trainer ID: " + existingSession.getTrainerTrainerID());
	        System.out.println("9. Availability: " + existingSession.getAvailability());
	        System.out.println("0. Save Changes & Exit");
	        System.out.print("Choice (0-9): ");
	        
	        int subChoice = scanner.nextInt();
	        scanner.nextLine(); 
	        
	        switch (subChoice) {
	            case 1:
	                System.out.print("Enter New Session Type: ");
	                existingSession.setSessionType(scanner.nextLine());
	                break;
	            case 2:
	                System.out.print("Enter New Description: ");
	                existingSession.setDescription(scanner.nextLine());
	                break;
	            case 3:
	                System.out.print("Enter New Max Participants: ");
	                existingSession.setMaxParticipants(scanner.nextInt());
	                scanner.nextLine();
	                break;
	            case 4:
	                System.out.print("Enter New Duration (mins): ");
	                existingSession.setDuration(scanner.nextInt());
	                scanner.nextLine();
	                break;
	            case 5:
	                System.out.print("Enter New Price ($): ");
	                existingSession.setPrice(scanner.nextInt());
	                scanner.nextLine();
	                break;
	            case 6:
	                System.out.print("Enter New Date & Time (e.g., DD/MM/YYYY HH:MM): ");
	                String dateTimeInput = scanner.nextLine();
	                
	                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	                
	                try {
	                	java.time.LocalDateTime newDateTime = java.time.LocalDateTime.parse(dateTimeInput, formatter);
	                
	                	existingSession.setDateAndTime(newDateTime);
	                }catch(java.time.format.DateTimeParseException e){
	                	System.out.println("Error: Wrong date/time format!");
	                }
	                break;
	            case 7:
	                System.out.print("Enter New Gym Code: ");
	                int newGymCode = scanner.nextInt();
	                scanner.nextLine();
	                
	                if (GymDBUtils.getGymById(newGymCode) == null) {
	                    System.out.println("Error: Target gym does not exist. Gym Code not changed.");
	                } else {
	                    existingSession.setGymCode(newGymCode);
	                }
	                break;
	            case 8:
	                System.out.print("Enter New Trainer ID: ");
	                int newTrainerId = scanner.nextInt();
	                scanner.nextLine();
	                
	                if (TrainerDBUtils.getTrainerByID(newTrainerId) == null) {
	                    System.out.println("Error: Target trainer does not exist. Trainer ID not changed.");
	                } else {
	                    existingSession.setTrainerID(newTrainerId);
	                }
	                break;
	            case 9:
	                System.out.print("Is it available? (true = 1/false = 0): ");
	                int newAvailability = scanner.nextInt();
	                scanner.nextLine();
	                existingSession.setAvailability(newAvailability);
	                
	                break;
	            case 0:
	                System.out.println("Saving changes to the database...");
	                subRunning = false;
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	    

	    SessionDBUtils.updateSession(existingSession);
	}
	
	public static void addReservation() {
	    System.out.println("\nCreate New Reservation\n");
	    
	    System.out.print("Please enter the Customer ID making the reservation: ");
	    int customerId = scanner.nextInt();
	    scanner.nextLine(); 
	    

	    if (CustomerDBUtils.getCustomerByID(customerId) == null) {
	        System.out.println("Error: Customer with ID " + customerId + " does not exist. Reservation aborted.");
	        return;
	    }
	    

	    SessionSearch allSessionsCriteria = new SessionSearch(-1, "", "", "", "", -1, "", false);
	    

	    ArrayList<Session> availableSessions = SessionDBUtils.searchSessions(allSessionsCriteria);
	    
	    if (availableSessions == null || availableSessions.isEmpty()) {
	        System.out.println("There are currently no available sessions to book. Reservation aborted.");
	        return;
	    }
	    

	    System.out.println("\nAvailable Sessions Catalog:\n");
	    System.out.printf("%-10s %-20s %-25s %-15s %-10s\n", "Code", "Type", "Date & Time", "Gym Code", "Price");
	    
	    for (Session s : availableSessions) {
	        System.out.printf("%-10d %-20s %-25s %-15d $%-10d\n", 
	            s.getSessionCode(), s.getSessionType(), s.getDateAndTime(), s.getGymCode(), s.getPrice());
	    }
	    System.out.println("\n");
	    

	    System.out.print("Please select a Session Code from the list above: ");
	    int sessionCode = scanner.nextInt();
	    scanner.nextLine();
	    
	    if (SessionDBUtils.getSessionByID(sessionCode) == null) {
	        System.out.println("Error: Invalid Session Code. Reservation aborted.");
	        return;
	    }
	    

	    java.time.LocalDateTime now = java.time.LocalDateTime.now();
	    System.out.println("Booking timestamp automatically recorded as: " + now);
	    

	    System.out.print("Will the customer need an invoice? (Y/N): ");
	    String invoiceInput = scanner.nextLine().trim().toLowerCase();
	    boolean invoice = invoiceInput.equals("y") || invoiceInput.equals("yes") || invoiceInput.equals("true");
	    

	    System.out.print("Will the customer pay now or later? (Type 'NOW' or 'LATER'): ");
	    String paymentChoice = scanner.nextLine().trim().toUpperCase();
	    
	    ReservationStatus status;
	    if (paymentChoice.equals("NOW")) {
	        status = ReservationStatus.COMPLETE;
	        System.out.println("\n[!] Reminder: Don't forget to go to 'Add New Payment' immediately to record the transaction.");
	    } else {
	        status = ReservationStatus.PENDING;
	    }
	    

	    Reservation newReservation = new Reservation(0, now, invoice, status, sessionCode, customerId);
	    
	    try {
	        int generatedCode = ReservationDBUtils.addReservationAndGetCode(newReservation);
	        System.out.println("Reservation created successfully in database with Code: " + generatedCode);
	    } catch (SQLException e) {
	        System.out.println("Error: Could not save the reservation to the database.");
	        e.printStackTrace();
	    }
	}
	
	public static void updateReservation() {
	    System.out.println("\nUpdate Reservation Data\n");
	    
	    System.out.print("Please enter the Reservation Code to modify: ");
	    int reservationCode = scanner.nextInt();
	    scanner.nextLine(); 
	    

	    Reservation existingReservation = ReservationDBUtils.getReservationByID(reservationCode);
	    if (existingReservation == null) {
	        System.out.println("No Reservation was found with this Code.");
	        return;
	    }
	    
	    boolean subRunning = true;
	    while (subRunning) {
	        System.out.println("\nCurrent Reservation Data:\n");
	        System.out.println("1. Date & Time: " + existingReservation.getDateAndTime());
	        System.out.println("2. Invoice Needed: " + (existingReservation.getInvoiceNeeded() ? "Yes" : "No"));
	        System.out.println("3. Status: " + existingReservation.getReservationStatus());
	        System.out.println("4. Session Code: " + existingReservation.getSessionCode());
	        System.out.println("5. Customer ID: " + existingReservation.getCustomerID());
	        System.out.println("0. Save Changes & Exit");
	        System.out.print("\nChoice (0-5): ");
	        
	        int subChoice = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch (subChoice) {
	            case 1:
	                System.out.print("Enter New Date & Time (e.g., DD/MM/YYYY HH:MM): ");
	                String dateTimeInput = scanner.nextLine();
	                
	                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	                
	                try {
	                	java.time.LocalDateTime newDateTime = java.time.LocalDateTime.parse(dateTimeInput, formatter);
	                	existingReservation.setDateAndTime(newDateTime);
	                }catch(java.time.format.DateTimeParseException e){
	                	System.out.println("Error: Wrong date/time format!");
	                }
	                break;
	            case 2:
	                System.out.print("Will the customer need an invoice? (Type 'Y' or 'N'): ");
	                String invoiceInput = scanner.nextLine().trim().toLowerCase();
	                boolean invoice = invoiceInput.equals("y") || invoiceInput.equals("yes") || invoiceInput.equals("t") || invoiceInput.equals("true");
	                existingReservation.setInvoiceNeeded(invoice);
	                break;
	            case 3:
	                System.out.print("Enter New Status (e.g., PENDING, CONFIRMED, CANCELLED): ");
	                String statusInput = scanner.nextLine().trim().toUpperCase();
	                try {
	                    ReservationStatus newStatus = ReservationStatus.valueOf(statusInput);
	                    existingReservation.setReservationStatus(newStatus);
	                    System.out.println("Reservation status provisionally updated to " + newStatus);
	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error: Invalid status! Please enter exactly PENDING, COMPLETE, or CANCELLED.");
	                }

	                
	                break;
	            case 4:
	                System.out.print("Enter New Session Code: ");
	                int newSessionCode = scanner.nextInt();
	                scanner.nextLine();
	                

	                Session targetSession = SessionDBUtils.getSessionByID(newSessionCode);
	                

	                if (targetSession == null) {
	                    System.out.println("Error: Target Session does not exist. Session Code not changed.");
	                } else if (targetSession.getAvailability() == 0) {

	                    System.out.println("Error: The requested Session (Code: " + newSessionCode + ") is currently unavailable or full. Session Code not changed.");
	                } else {
	                    existingReservation.setSessionCode(newSessionCode);
	                    System.out.println("Success: Session Code provisionally updated to " + newSessionCode + ".");
	                }
	                break;
	            case 5:
	                System.out.print("Enter New Customer ID: ");
	                int newCustomerId = scanner.nextInt();
	                scanner.nextLine();
	                
	                // Έλεγχος αν ο νέος πελάτης υπάρχει
	                if (CustomerDBUtils.getCustomerByID(newCustomerId) == null) {
	                    System.out.println("Error: Target Customer does not exist. Customer ID not changed.");
	                } else {
	                    existingReservation.setCustomerID(newCustomerId);
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
	    
	    // Κλήση της ReservationDBUtils για την εκτέλεση του UPDATE
	    ReservationDBUtils.updateReservation(existingReservation);
	}
	
	public static void addPayment() {
	    System.out.println("\n--- Create New Payment ---\n");
	    
	    System.out.print("Please enter the Reservation Code for this payment: ");
	    int reservationCode = scanner.nextInt();
	    scanner.nextLine();
	    
	    Reservation existingRes = ReservationDBUtils.getReservationByID(reservationCode);
	    if (existingRes == null) {
	        System.out.println("Error: Reservation not found. Payment aborted.");
	        return;
	    }
	    
	    Session bookedSession = SessionDBUtils.getSessionByID(existingRes.getSessionCode());
	    if (bookedSession == null) {
	        System.out.println("Error: The associated Session could not be found. Payment aborted.");
	        return;
	    }
	    float amount = bookedSession.getPrice();
	    System.out.println("Amount to pay (Auto-retrieved from Session): $" + amount);
	    
	    String paymentMethodStr;
	    String paymentStatusStr;
	    
	    ReservationStatus currentResStatus = existingRes.getReservationStatus();
	    
	    // ΕΛΕΓΧΟΣ ΛΟΓΙΚΗΣ: Ταυτόχρονη Πληρωμή (COMPLETE) vs Ετεροχρονισμένη (PENDING)
	    if (currentResStatus==ReservationStatus.COMPLETE) {
	        System.out.println("\nSimultaneous Online Payment Detected");
	        System.out.println("Rule: For immediate payments, only CREDIT_CARD or BANK_TRANSFER are accepted.");
	        
	        boolean valid = false;
	        while (!valid) {
	            System.out.print("Enter Payment Method (CREDIT_CARD, BANK_TRANSFER): ");
	            paymentMethodStr = scanner.nextLine().trim().toUpperCase();
	            if (paymentMethodStr.equals("CREDIT_CARD") || paymentMethodStr.equals("BANK_TRANSFER")) {
	                valid = true;
	            } else {
	                System.out.println("Invalid input. Cash is not accepted for online simultaneous payments.");
	            }
	        }
	        
	        // Η πληρωμή είναι αυτόματα CONFIRMED αφού γίνεται ταυτόχρονα
	        paymentStatusStr = "CONFIRMED";
	        System.out.println("Payment Status automatically set to: CONFIRMED");
	        
	    } else {
	        System.out.println("\n[Deferred Payment / Pending Reservation Detected]");
	        System.out.println("Rule: For pending reservations, all payment methods (including CASH) are accepted.");
	        
	        boolean valid = false;
	        while (!valid) {
	            System.out.print("Enter Payment Method (CASH, CREDIT_CARD, BANK_TRANSFER): ");
	            paymentMethodStr = scanner.nextLine().trim().toUpperCase();
	            if (paymentMethodStr.equals("CASH") || paymentMethodStr.equals("CREDIT_CARD") || paymentMethodStr.equals("BANK_TRANSFER")) {
	                valid = true;
	            } else {
	                System.out.println("Invalid input. Please enter CASH, CREDIT_CARD, or BANK_TRANSFER.");
	            }
	        }
	        
	        System.out.print("Enter Payment Status (CONFIRMED, PENDING): ");
	        paymentStatusStr = scanner.nextLine().trim().toUpperCase();
	    }
	    
	    System.out.print("Enter Points Transaction ID (or 0 if none): ");
	    int transID = scanner.nextInt();
	    scanner.nextLine(); 
	    
	    java.time.LocalDateTime now = java.time.LocalDateTime.now();
	    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    String paymentDate = now.format(formatter);
	    System.out.println("Payment timestamp automatically recorded as: " + paymentDate);
	    
	    // Δημιουργία Payment
	    Payment newPayment = new Payment(
	    		amount, paymentMethodStr, paymentDate, reservationCode, transID, paymentStatusStr
	    		);
	    PaymentDBUtils.addPayment(newPayment);
	    
	    // Αν η κράτηση ήταν PENDING και τώρα η πληρωμή έγινε CONFIRMED, αναβαθμίζουμε την κράτηση
	    if (currentResStatus.equals("PENDING") && paymentStatusStr.equals("CONFIRMED")) {
	        existingRes.setReservationStatus(ReservationStatus.COMPLETE); 
	        ReservationDBUtils.updateReservation(existingRes);
	        System.out.println("Associated Reservation status automatically updated to COMPLETE.");
	    }
	}
	
	public static void updatePayment() {
	    System.out.println("\n--- Update Payment Data ---\n");
	    
	    System.out.print("Enter Payment ID to modify: ");
	    int paymentId = scanner.nextInt();
	    scanner.nextLine();
	    
	    Payment existingPayment = PaymentDBUtils.getPaymentByID(paymentId);
	    if (existingPayment == null) {
	        System.out.println("Error: Payment ID not found.");
	        return;
	    }
	    
	    System.out.println("\nCurrent Status: " + existingPayment.getPaymentStatus());
	    System.out.print("Enter New Payment Status (CONFIRMED, PENDING) or press Enter to keep current: ");
	    String newStatus = scanner.nextLine().trim().toUpperCase();
	    
	    if (!newStatus.isEmpty()) {
	        existingPayment.setPaymentStatus(PaymentStatus.valueOf(newStatus.toUpperCase()));
	        
	        // BUSINESS LOGIC: Αν άλλαξε σε CONFIRMED, πρέπει να ενημερώσουμε την κράτηση
	        if (newStatus.equals(PaymentStatus.CONFIRMED.toString())) {
	            Reservation existingRes = ReservationDBUtils.getReservationByID(existingPayment.getReservationCode());
	            if (existingRes != null && existingRes.getReservationStatus()==ReservationStatus.PENDING) {
	                existingRes.setReservationStatus(ReservationStatus.COMPLETE);
	                ReservationDBUtils.updateReservation(existingRes);
	                System.out.println("Auto-Update: Associated Reservation changed to COMPLETE.");
	            }
	        }
	    }
	    
	    PaymentDBUtils.updatePayment(existingPayment);
	}

	private static void searchAndDisplayTrainers() {
		System.out.println("\n Trainer's List");
		
		ArrayList<Trainer> trainers = TrainerDBUtils.getAllTrainers();
		
		if(trainers == null || trainers.isEmpty()) {
			System.out.println("No trainers found");
			
		}else {
			System.out.printf("%-10s | %-25s | %-20s | %-20s \n", "Trainer ID", "Trainers Name", "Specialty", "Gym Code");
			
			for (Trainer t : trainers) {
				System.out.printf("%-10d | %-25s | %-20s | %-20d\n", t.getTrainerID(), t.getName(), t.getSpecialty(), t.getGymCode());
			}
		}
	}
	
	private static void viewActiveReservations() {
		
		ArrayList<Reservation> activeReservation = ReservationDBUtils.getActiveReservations();
		
		if (activeReservation == null || activeReservation.isEmpty()) {
			System.out.println("No active reservations found in the system.");
		}else {
			System.out.printf("%-18s | %-20s | %-15s | %-12s | %-12s | %-12s\n", 
                    "Reservation Code", "Date & Time", "Invoice Needed", "Status", "Session Code", "Customer ID");
		
			for (Reservation res : activeReservation) {
				System.out.printf("%-18d | %-20s | %-15s | %-12s | %-12d | %-12d\n", 
						res.getReservationCode(),
						res.getDateAndTime(),
						(res.getInvoiceNeeded() ? "YES" : "NO"),
						res.getReservationStatus().name(),
						res.getSessionCode(),
						res.getCustomerID());
			}
		
		
		}
	}

	//user has accepted the session and the reservation has been created => proceed with the payment
	private static void handleNewPayment(Reservation selectedReservation, Session selectedSession, PaymentMethods selectedPaymentMethod, PaymentStatus paymentStatus) {
		Payment newPayment= new Payment(
					selectedSession.getPrice(),
					selectedPaymentMethod,
					paymentStatus == PaymentStatus.CONFIRMED ? LocalDateTime.now() : null,
					selectedReservation.getReservationCode(),
					paymentStatus
				);
		try { // try adding payment to db
			int newPaymentId= PaymentDBUtils.addPayment(newPayment); // add new payment to the database
			newPayment.setPaymentID(newPaymentId); // update local payment instance with the id returned from the database
		}catch(SQLException e) {
			System.out.println("Something went worng in recording your payment. Please try again later");
			return;
		}
		
		if(paymentStatus == PaymentStatus.CONFIRMED) { // payment happens at the same time with the reservation
			selectedReservation.setReservationStatus(ReservationStatus.COMPLETE);
			ReservationDBUtils.updateReservationStatus(selectedReservation.getReservationCode(), ReservationStatus.COMPLETE); //update reservation status in the database
		}
		//in any other case the gym employee manually records the payment once it happens
	}
	
	// handle a payment that has happened after the reservation
	private static void manuallyRecordPayment() {
		ArrayList<PendingPayment> pendingPayments= PaymentDBUtils.getPendingPayments();
		System.out.println("Reservations that have not yet been paid for: ");
		
		System.out.printf("%-10s | %-9s | %-15s | %-10s | %-22s | %-20s | %-20s\n", 
                "ID", "Amount", "Method", "Status", "Customer Name", "Booked On", "Session Date");
		for(PendingPayment p : pendingPayments) {
			/*System.out.println("payment id: " + p.getPaymentId());
			System.out.println("payment amount: " + p.getAmount());
			System.out.println("payment method: " + p.getPaymentMethod());
			System.out.println("payment status: " + p.getPaymentStatus());
			System.out.println("customer name: " + p.getCustomerFullName());
			System.out.println("reservation date of : " + p.getDateOfReservation());
			System.out.println("session date: " + p.getDateOfSession());*/
			
			System.out.printf("%-10d | %-9s | %-15s | %-10s | %-22s | %-20s | %-20s\n", 
                    p.getPaymentId(),
                    p.getAmount() + " €",
                    p.getPaymentMethod().name(),
                    p.getPaymentStatus().name(),
                    p.getCustomerFullName(),
                    p.getDateOfReservation(),
                    p.getDateOfSession());
		}
		
		System.out.print("\nEnter the Payment ID you want to mark as PAID (or 0 to cancel): ");
	    int selectedPaymentId = scanner.nextInt();
	    scanner.nextLine();
	    
	    if(selectedPaymentId == 0) {
	    	System.out.println("Operation Cancelled");
	    }
	    boolean idExists = false;
	    for (PendingPayment p : pendingPayments) {
	    	if(p.getPaymentId() == selectedPaymentId) {
	    		idExists = true;
	    		break;
	    	}
	    }
	    
	    if (idExists) {
	    	boolean success = PaymentDBUtils.confirmPaymentChangeInDB(selectedPaymentId);
	    	if(success) {
	    		System.out.println("Payment " + selectedPaymentId + " is now marked as COMPLETE.");
	    	}else {
	    		System.out.println("Could not Update Payment Status.");
	    	}
	    }else {
	    	System.out.println("Invalid Payment ID, please try again.");
	    }
	}
	
	private static void updateReservationsOrPayments() {
		System.out.println("1. Cancel a Reservation ");
		System.out.println("2. Update Reservation Status Manually ");
		System.out.println("3. Update Payment Status Manually ");
		System.out.println("4. Back to Admin Menu ");
		
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		switch(choice) {
			case 1:
				viewActiveReservations();
				System.out.println("Enter Reservation Code to Cancel");
				int resCodeCancel = scanner.nextInt();
				scanner.nextLine();
				
				boolean successCancel = ReservationDBUtils.cancelReservationInDB(resCodeCancel);
				if(successCancel) {
					System.out.println("Reservation " + resCodeCancel + " successfully Cancelled. ");
				}else {
					System.out.println("Could not cancel reservation. Check if Reservation ID is correct");
				}
				break;
				
			case 2:
				viewActiveReservations();
				System.out.print("Enter Reservation Code: ");
	            int resCode = scanner.nextInt();
	            scanner.nextLine();
	            System.out.println("Choose new status: 1. PENDING, 2. COMPLETE, 3. CANCELLED");
	            int resStatusChoice = scanner.nextInt();
	            scanner.nextLine();
	            ReservationStatus chosenStatus = null;
	            
	            if(resStatusChoice == 1) {
	            	chosenStatus = ReservationStatus.PENDING;
	            }else if(resStatusChoice == 2) {
	            	chosenStatus = ReservationStatus.COMPLETE;
	            }else if(resStatusChoice == 3) {
	            	chosenStatus = ReservationStatus.CANCELLED;
	            }else {
	            	System.out.println("Invalid choice. Defaulting to PENDING.");
	            	chosenStatus = ReservationStatus.PENDING;
	            }
	            
	            ReservationDBUtils.updateReservationStatus(resCode, chosenStatus);
	            
	            break;
	            
			case 3:
				PaymentDBUtils.displayAllPaymentsInDB();
				
				System.out.println("\nEnter Payment ID to update: ");
				int payId = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Choose new status: 1. PENDING, 2. CONFIRMED");
	            System.out.print("Your choice: ");
	            int payStatusChoice = scanner.nextInt();
	            scanner.nextLine();
	            
	            PaymentStatus newPayStatus;
	            
	            if(payStatusChoice == 1) {
	            	newPayStatus = PaymentStatus.PENDING;
	            }else if(payStatusChoice == 2) {
	            	newPayStatus = PaymentStatus.CONFIRMED;
	            }else {
	            	System.out.println("Invalid Choice. Defaulting to Pending");
	            	newPayStatus = PaymentStatus.PENDING;
	            }
	            
	            if(PaymentDBUtils.updatePaymentStatusd(payId, newPayStatus)) {
	            	System.out.println("Payment Status updated to " + newPayStatus);
	            }else {
	            	System.out.println("Failed to Update Payment Status. Check if ID exists");
	            }
		}
	}
	
	private static void manageCustomerCancellation() {
		System.out.println("Enter your phone Number: ");
		String inputPhone = scanner.nextLine();
		
		ArrayList<Reservation> myReservations = ReservationDBUtils.displayReservationsByCustomerPhone(inputPhone);
		
		if(myReservations == null || myReservations.isEmpty()) {
			System.out.println("No reservations found for: " + inputPhone);
		}else {
			
			System.out.printf("%-18s | %-22s | %-12s \n", "Reservation Code","Reservation Date/Time", "Status");
			
			for (Reservation r : myReservations) {
				System.out.printf("%-18s | %-22s | %-12s \n",
						r.getReservationCode(),
						r.getDateAndTime(),
						r.getReservationStatus());
			}
		}
		
		System.out.println("Enter the Reservation Code you wish to Cancel: ");
		int resCode = scanner.nextInt();
		scanner.nextLine();
		
		boolean success = ReservationDBUtils.cancelReservationInDB(resCode);
		
		if (success) {
			System.out.println("The reservation with Code: " + resCode + " was cancelled.");
		}else {
			System.out.println("Something went wrong. The reservation wasn't cancelled.");
		}
		
	}
	// cancel reservations that have not been paid on time
	private static void handleUnpaidReservations() {
		ArrayList<Reservation> unpaidReservations= ReservationDBUtils.getUnpaidReservations(); // get overdue reservations
		System.out.println("Tomorrow's sessions that have not yet been paid for:\n---------------------------------------------------------------------\n");
		if(unpaidReservations.isEmpty()) { // no incomplete reservations
			System.out.println("No reservations found");
		}
		
		StringBuilder resIdsString= new StringBuilder(); //reservation IDs for which a change must be made
		StringBuilder sesIdsString= new StringBuilder(); //session IDs for which a change must be made
		
		for (Reservation ur : unpaidReservations){ // display all overdue reservations 
			System.out.printf("%-15s | %-20s | %-25s | %-15s | %-30s\n",
	                "ID: "+ur.getReservationCode(), "Reserv. Date: "+ur.getDateAndTime(), "Invoice needed: "+ur.getInvoiceNeeded(), "Status: "+ur.getReservationStatus().toString(), "Session Id:"+ur.getSessionCode(), "Customer ID: " + ur.getCustomerID());
			resIdsString.append(ur.getReservationCode()).append(", "); //gather all IDs that need changing in their status
			
		}
		resIdsString.delete(resIdsString.length()-2, resIdsString.length()); //remove trailing comma
		resIdsString.delete(sesIdsString.length()-2, sesIdsString.length()); //remove trailing comma
		try {
			ReservationDBUtils.cancelMultipleReservations(resIdsString.toString()); // change reservation status to cancelled
			SessionDBUtils.freeUpSpaceInMultipleSessions(sesIdsString.toString()); 
		}catch(SQLException e) {
			System.out.println("An error occured while canceling unpaid reservations. Please try again later.");
			return;
		}
		
	}
	
	private static void handleCancelledReservations() {
		ArrayList<Reservation> cancelledReservations= ReservationDBUtils.getCancelledReservations(); // get all cancelled reservations
		//HashMap<Integer, Reservation> pastReservationsMap= new HashMap<>();
		StringBuilder idSb= new StringBuilder();
		Integer num=0;
		for(Reservation r:cancelledReservations) {
			System.out.printf("%-15s | %-20s | %-25s | %-15s | %-30s | %-15s | %-15s\n",
	                ++num+"ID: "+r.getReservationCode(), "Reserv. Date: "+r.getDateAndTime(), "Invoice needed: "+r.getInvoiceNeeded(), "Status: "+r.getReservationStatus().toString(), "Session Id:"+r.getSessionCode(), "Customer ID: " + r.getCustomerID() + "Is passed: " + (r.isPast()?"YES":"NO"));
			if(r.isPast()) { //if the reservation is cancelled and in the past, 
				idSb.append(r.getReservationCode()).append(", ");
			}
		}
		char letter;
		do{ 
			System.out.println("Delete past reservations? (Y/N)");
			String choice= scanner.next();
			letter= Character.toUpperCase(choice.charAt(0));
		}while((letter!= 'Y' && letter != 'N') );
		if(letter == 'Y') {
			idSb.delete(idSb.length()-2, idSb.length()); //remove trailing comma
			ReservationDBUtils.deleteReservationsById(idSb.toString());	
		}
	}

	
}
