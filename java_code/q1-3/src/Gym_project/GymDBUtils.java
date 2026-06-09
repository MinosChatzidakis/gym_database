package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GymDBUtils {

	
	public ResultSet getGymsByCity(String city) {
        String sqlQuery= ("SELECT * FROM gym WHERE city = '" + city + "'");
        ResultSet res= null;
        try {
            Connection con= SQLConnector.getConnection();
            Statement stm= con.createStatement();
            res= stm.executeQuery(sqlQuery);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
