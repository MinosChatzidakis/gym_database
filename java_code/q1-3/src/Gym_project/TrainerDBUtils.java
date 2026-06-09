//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerDBUtils {
	public ResultSet getAllTrainers(){
		String sqlQuery= "SELECT name, trainer_id, gym_code FROM trainer ORDER BY Specialty";
		ResultSet res= null;
		try {
			Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();
			res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public ResultSet getTrainerByGymCode(int gymCode) {
		String sqlQuery= ("SELECT * FROM trainer WHERE Gym_code = "+ gymCode);
		ResultSet res= null;
		try {
			Connection con= SQLConnector.getConnection();
			Statement stm= con.createStatement();
			res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
