package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class ServicesDBUtils {
	public static ArrayList<Services> getServicesByGymCode(int gymCode){
		String sql= "SELECT Service_Name FROM services WHERE GYM_Gym_code=" + "'" + gymCode + "'";
		try (Connection con= SQLConnector.getConnection();
				Statement stm= con.createStatement();){
			ResultSet res= stm.executeQuery(sql);
			ArrayList<Services> fetchedServices= new ArrayList<>();
			while(res.next()) {
				Services currentService= new Services(
						res.getString("Service_name"),
						res.getInt("GYM_Gym_code")
												);
				fetchedServices.add(currentService);
			}
			return fetchedServices;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null; // nothing was found => we return null
	}
}
