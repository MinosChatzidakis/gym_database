package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerDBUtils {

	public ResultSet getTrainerByGym(int gymCode) {
        String sqlQuery= ("SELECT * FROM trainer WHERE GYM_Gym_code = '"+ gymCode + "'");
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
