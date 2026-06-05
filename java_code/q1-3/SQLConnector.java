package Test;

import java.sql.*;

public class SQLConnector{
    public static void main(String[] args) throws SQLException {
    	
        System.out.println("MySQL Connect Example");
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "gym_database";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try{
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName, userName, password);
            System.out.println("Connected to database");
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        System.out.println("Disconnected from database");
        
        
        
    }
}