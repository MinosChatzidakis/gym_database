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


public static String getUnifiedServicesByGymCode(int gymCode){
	String sql= "SELECT * FROM services WHERE gym_Gym_Code=" + "'" + gymCode + "';";
	try (Connection con= SQLConnector.getConnection();
			Statement stm= con.createStatement();){
		ResultSet res= stm.executeQuery(sql);
		StringBuilder fetchedServices= new StringBuilder("");
		while(res.next()) {
			fetchedServices.append(", ").append(res.getString("service_Name"));
		}
		return fetchedServices.toString();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return ""; // nothing was found => we return null
}
}
