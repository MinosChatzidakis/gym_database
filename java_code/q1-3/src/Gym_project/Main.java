package Gym_project;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
			
			int  choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					SessionDBUtils.testQuery();
					adminMenu(scanner);
					break;
					
				case 2:
					customerMenu(scanner);
					break;
				case 0:
					isRunning = false;
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
			System.out.println("1010. pending payments niggers");
			System.out.println("0. Back to Main Menu");
			
			int choice2 = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice2) {
				case 1:
					insertDataMenu(scanner);
					break;
				case 1010:
					handleUnpaidReservations();
					//manuallyRecordPayment();
					break;
				case 2:
					System.out.println("\n Search Gyms");
					searchAndDisplayGyms();
					break;
				case 3:
					System.out.println("\nSearch Trainers");
					searchAndDisplayTrainers();
					break;
				case 4:
					System.out.println("\nView Reserved Sessions");
					viewActiveReservations(); 
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
					searchAndDisplayTrainers();
					break;
				case 3:
					System.out.println("\nSearch Available Sessions");
					SearchAvailableSessions(scanner);
					break;
				case 0: 
					return;
					
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
                return;
                
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
			Customer c= new Customer(-1, "", "", "", selectedSession.getGymCode());
			boolean err= true;
			
			while(err) {
				//record customer's name
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
				String paymentMethod = "";
				
				if (payChoice == 1) {
					reservationStatus = ReservationStatus.COMPLETE;
					paymentStatus = PaymentStatus.CONFIRMED;
					paymentMethod = "Credit Card";
				}else {
					reservationStatus = ReservationStatus.PENDING;
					paymentStatus = PaymentStatus.PENDING;
					System.out.println("\nHow do you intend to pay at the gym?");
				    System.out.println("1. Card (at the desk)");
				    System.out.println("2. Cash");
				    
				    int methodChoice = scanner.nextInt();
				    scanner.nextLine();
				    
				    if(methodChoice == 1) {
				    	paymentMethod = "Credit Card";
				    }else {
				    	paymentMethod = "Cash";
				    }
				}
				
				int generatedCustomerId = CustomerDBUtils.addCustomerAndGetId(c);
				
				if(generatedCustomerId > 0) {
					
					Reservation r = new Reservation(-1 , selectedSession.getDateAndTime(), invoice, reservationStatus, selectedSession.getSessionCode(), generatedCustomerId);
					try {
						int generatedReservationCode = ReservationDBUtils.addReservationAndGetCode(r);
						SessionDBUtils.checkAndUpdateAvailability(selectedSession);
						r.setReservationCode(generatedReservationCode);
						//handlePayment(r, s, paymentStatus); 
						
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
			System.out.printf("%-18s | %-18s | %-15s | %-12s | %-12s | %-12s\n", 
                    "Reservation Code", "Date & Time", "Invoice Needed", "Status", "Session Code", "Customer ID");
		
			for (Reservation res : activeReservation) {
				System.out.printf("%-18d | %-18s | %-15s | %-12s | %-12d | %-12d\n", 
						res.getReservationCode(),
						res.getDateAndTime(),
						(res.getInvoiceNeeded() ? "YES" : "NO"),
						res.getReservationStatus().name(),
						res.getSessionCode(),
						res.getcustomerID());
			}
		
		
		}
	}

	//user has accepted the session and the reservation has been created => proceed with the payment
	private static void handlePayment(Reservation selectedReservation, Session selectedSession, PaymentMethods selectedPaymentMethod, PaymentStatus paymentStatus) {
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
		for(PendingPayment p : pendingPayments) {
			System.out.println("payment id: " + p.getPaymentId());
			System.out.println("payment amount: " + p.getAmount());
			System.out.println("payment method: " + p.getPaymentMethod());
			System.out.println("payment status: " + p.getPaymentStatus());
			System.out.println("customer name: " + p.getCustomerFullName());
			System.out.println("reservation date of : " + p.getDateOfReservation());
			System.out.println("session date: " + p.getDateOfSession());
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
	                "ID: "+ur.getReservationCode(), "Reserv. Date: "+ur.getDateAndTime(), "Invoice needed: "+ur.getInvoiceNeeded(), "Status: "+ur.getReservationStatus().toString(), "Session Id:"+ur.getSessionCode(), "Customer ID: " + ur.getcustomerID());
			resIdsString.append(ur.getReservationCode()).append(", "); //get all IDs that need changing in their status
			
		}
		resIdsString.delete(resIdsString.length()-2, resIdsString.length()); //remove trailing comma
		resIdsString.delete(sesIdsString.length()-2, sesIdsString.length()); //remove trailing comma
		try {
			ReservationDBUtils.cancelMultipleReservations(resIdsString.toString()); // change reservation status to cancelled
			SessionDBUtils.freeUpSpaceInMultipleSessions(sesIdsString.toString()); //  free space in session
		}catch(SQLException e) {
			System.out.println("An error occured while canceling unpaid reservations. Please try again later.");
			return;
		}
		
	}
	
	private static void handleCancelledReservations() {
		ArrayList<> cancelledReservations= ReservationDBUtils.
	}
	
}