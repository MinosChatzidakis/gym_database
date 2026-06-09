//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class GymDBUtils {
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
	
	public static ArrayList<Gym> getGymsByCity(String city) {
        String sqlQuery= ("SELECT * FROM gym WHERE city = '" + city + "'");
        try (Connection con= SQLConnector.getConnection();
                Statement stm= con.createStatement();){
            ResultSet res= stm.executeQuery(sqlQuery);
            ArrayList<Gym> fetchedGyms= new ArrayList<>();
            while(res.next()) {
                Gym currentGym = new Gym(
                        res.getString("city"),
                        res.getString("services"),
                        res.getString("address"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("phone"),
                        res.getInt("gym_code")
                                                );
                fetchedGyms.add(currentGym);
            }
            
            return fetchedGyms;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null; // nothing was found => we return null
    }
}
