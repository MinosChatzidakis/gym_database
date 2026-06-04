package gym_project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Connection conn;
		try {
			conn = SQLConnector.getConnection();
			if (conn != null) {
            System.out.println("[ΕΠΙΤΥΧΙΑ] Η βάση είναι συνδεδεμένη και έτοιμη να δεχτεί δεδομένα!");
            try { 
                conn.close();
            } catch (Exception e) {}
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

}
