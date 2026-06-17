package Gym_project;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Extension4 {
	
	private static Scanner scanner = new Scanner(System.in);
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static class Reward{
		
		private int rewardId;
		private String description;
		private int pointsRequired;
		private int validForDays;
		
		public Reward(int rewardId, String description, int pointsRequired, int validForDays) {
			this.rewardId = rewardId;
			this.description = description;
			this.pointsRequired = pointsRequired;
			this.validForDays = validForDays;
		}
		
		public Reward(String description, int pointsRequired, int validForDays) {
			this.description = description;
			this.pointsRequired = pointsRequired;
			this.validForDays = validForDays;
		}
		
		public int getRewardId() {
			return rewardId;
		}
		
		public String getDescription() {
			return description;
		}
		public int getPointsRequired() {
			return pointsRequired;
		}
		public int getValidForDays() {
			return validForDays;
		}
		
	
	}
	
	
	public static int getCustomerPoints(int customerId, Connection conn) throws SQLException {
		String sqlQuery = "SELECT SUM(amount) AS total_points FROM pts_transactions WHERE customer_Customer_ID = " + customerId;
		try(Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sqlQuery)){
			if(rs.next()) {
				return rs.getInt("total_points");
			}
		}
		return 0;
	}
	
	
	public static void viewCurrentStatus(int customerId) {
		System.out.println("My Loyalty Status");
		//String rewardsSql = "SELECT * FROM available_rewards ORDER BY pts_Required ASC";
		
		try(Connection conn =SQLConnector.getConnection()){
			
			int currentBalance = getCustomerPoints(customerId, conn);
			
			System.out.println("Customer ID: " + customerId);
			System.out.println("Current Point Balance: " + currentBalance);
			
			ArrayList<Reward> availableRewardIds = getAvailableRewards(customerId);

			java.util.HashMap<Integer, Reward> optionsMap = new java.util.HashMap<>();
			int num = 1;
			for(Reward r : availableRewardIds) {
				
					System.out.printf("[%d] %s | Cost: %d pts (Valid for: %d days)\n", 
                            num, r.getDescription(), r.getPointsRequired(), r.getValidForDays());
					
					optionsMap.put(num, r);
					num++;
			}
			if (optionsMap.isEmpty()) {
                System.out.println("No rewards available for redemption at your current points level.");
            }
			
			
			System.out.println("Would you like to reddem a reward? (Enter the number or 0 to exit)");
			int choiceId = scanner.nextInt();
            scanner.nextLine();
            
            if(choiceId == 0) {
            	System.out.println("Exiting Status Menu");
            	return;
            }
			
            if(optionsMap.containsKey(choiceId)) {
            	
            	Reward selectedReward = optionsMap.get(choiceId);
            	executeRedemption(customerId, selectedReward, conn);
            }else {
            	System.out.println("Invalid Choice or Insufficient points");
            }
            
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void executeRedemption(int customerId, Reward reward, Connection conn) throws SQLException {
		
		try(Statement stm = conn.createStatement()){
			conn.setAutoCommit(false); //Stops the Auto Commit in case one of the two .executeUpdate fails
			 
				
				LocalDate today = LocalDate.now();
				String todayString = today.format(DATE_FORMATTER);
				String validUntil = today.plusDays(reward.getValidForDays()).format(DATE_FORMATTER);
				
				String PointsSql = "INSERT INTO pts_transactions (amount, source, date, description, customer_Customer_ID, payment_Payment_ID) " +
                        "VALUES (" + (-reward.getPointsRequired()) + ", 'Redeem', '" + todayString + "', 'Εξαργύρωση: " + reward.getDescription() + "', " + customerId + ", NULL)";
				
				String RewardsSql = "INSERT INTO rewards_distribution (available_Rewards_Reward_ID, is_Used, date_Obtained, date_Used, valid_Until, customer_Customer_ID) " +
                        "VALUES (" + reward.getRewardId() + ", 0, '" + todayString + "', NULL, '" + validUntil + "', " + customerId + ")";
				
				stm.executeUpdate(PointsSql);
                stm.executeUpdate(RewardsSql);
                
                System.out.println("Reward Successfully redeemed!");
                conn.commit();//commits after both .executeUpdate was executed succesfully
			
		}catch(SQLException e) {
			conn.rollback();//returns the database to the state before the two .executeUpdate
			throw e;
		}finally {
			conn.setAutoCommit(true);//return setAutoCommit to normal value "true"
		}
	}
	
	public static ArrayList<Reward> getAvailableRewards(int customerId){
        //get all rewards the customer can buy in the gym they are signed up in 
        String sql= "SELECT * FROM available_rewards WHERE gym_Gym_Code = (SELECT gym_Gym_Code FROM customer WHERE customer_ID =" + customerId +") AND pts_Required <=(SELECT SUM(amount) AS total_points FROM pts_transactions WHERE customer_Customer_ID = "+customerId+");";
        ArrayList<Reward> availableRewards = new ArrayList<>();
        try(Connection conn = SQLConnector.getConnection();
            Statement stm= conn.createStatement()) {
            ResultSet res= stm.executeQuery(sql);

            while(res.next()) {
                Reward r = new Reward(
                        res.getString("description"),
                        res.getInt("pts_Required"),
                        res.getInt("valid_For")
                        ); //create a minimal reward object
                availableRewards.add(r);
            }
            return availableRewards; //return rewards
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public static void viewAllAvailableRewardsForGym(int gymId) {
				
		String sqlQuery = "SELECT name, city, reward_ID, description, pts_Required, valid_For " +
								"FROM gym " +
								"LEFT JOIN available_rewards ON gym_Code = gym_Gym_Code " +
								"WHERE gym_Code = " + gymId + " ORDER BY pts_Required ASC";
		
		try(Connection conn =SQLConnector.getConnection();
				Statement stm = conn.createStatement()){
			ResultSet rs = stm.executeQuery(sqlQuery);
			
			boolean gymHeaderPrinted = false;
			boolean hasRewards = false;
			while (rs.next()) {
				
				if(!gymHeaderPrinted) {
					System.out.println("Gym Info:");
					System.out.printf("Name: %-20s | City: %-20s | Code: %-10d", rs.getString("name"), rs.getString("city"), gymId);
			
					System.out.println("\n\nAvailable Rewards for gym");
					System.out.printf("%-10s | %-50s | %-15s | %-20s\n", "Reward ID", "Description", "Points", "Valid For (Days)");
					gymHeaderPrinted = true;
				} 
				
				hasRewards = true;
				System.out.printf("%-10d | %-50s | %-15s | %d ημέρες\n",
						rs.getInt("reward_ID"),
						rs.getString("description"),
						rs.getInt("pts_Required") + " pts",
						rs.getInt("valid_For"));
			
				
			}
			if(!hasRewards) {
				System.out.println("This gym doesn't have any Point Rewards yet");
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
