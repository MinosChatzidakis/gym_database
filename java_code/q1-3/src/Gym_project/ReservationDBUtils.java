package Gym_project;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationDBUtils {
	public void addReservation(Reservation r) {
		String sqlQuery= "INSERT INTO reservations () VALUES ()";
		
		try{
			Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement();
			int rowsAffected= stm.executeUpdate(sqlQuery); //run the query on the database and store amount of rows affected by it
			if(rowsAffected>0) {
				System.out.println("Reservation was successfully added to db.");
			}else {
				System.out.println("Something went wrong and reservation could not be added to db.");
			}
		}catch(SQLException e) {
			System.out.println("Error in adding reservation to db:");
			e.printStackTrace();
		}
	}
}
