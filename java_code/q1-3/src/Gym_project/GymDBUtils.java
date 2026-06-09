//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GymDBUtils {
	public void addGym(Gym g) {
		String sqlQuery= "INSERT INTO gym () VALUES ()";
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
	
	public ResultSet getAllGyms() {
		String sqlQuery= "SELECT * FROM gym ORDER BY city;";
		try {
			Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getGymsByCity(String city) {
		String sqlQuery= ("SELECT * FROM gym WHERE city = '" + city + "'");
		try {
			Connection con= SQLConnector.getConnection();
			Statement stm= con.createStatement();
			ResultSet res= stm.executeQuery(sqlQuery);
			if(res.next()) { //if we find something we return it 
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null; // nothing was found => we return null
	}
}
