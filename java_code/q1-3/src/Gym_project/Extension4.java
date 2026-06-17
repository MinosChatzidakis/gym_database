package Gym_project;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.ResultSet;

public class Extension4 {		
		
		//this method should be called when a new payment goes through.
		public void onSuccessfulPayment(int customerId, Payment p) {
			//ADD POINTS TO USER
			//CHECK IF THEY ARE ENOUGH FOR A REWARD
			//INFORM USER
			
			String formattedDate = p.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); //convert date to wanted format for the database
			String sql = "INSERT INTO pts_transactions (amount, source, date, customer_Customer_ID, payment_Payment_ID) " 
			           + "VALUES (" 
			           + p.getAmount() + ", " 		  // payment ID
			           + "'class booking', "          // source
			           + "'" + formattedDate + "', "  // date
			           + customerId + ", "            // customer ID
			           + p.getPaymentID()             // payment ID
			           + ");";
			try(Connection conn= SQLConnector.getConnection();
					Statement stm= conn.createStatement()) {
				int rowsAffected= stm.executeUpdate(sql);
				System.out.println("Rows Affected:" + rowsAffected);
			}catch(SQLException e) {
				e.printStackTrace();
			}

			ArrayList<Reward> availableRewards= getAvailableRewards(customerId);
			if(availableRewards==null||availableRewards.isEmpty()) {
				return;
			}
			System.out.println("You have "+availableRewards.size()+" rewards available. You can view them in your current status menu");
		}

		//get the rewards the customer is eligible for based on their points balance
		public ArrayList<Reward> getAvailableRewards(int customerId){
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
		
		public void viewCurrentStatus(int customerId) {
			//get the customer's current point balance and available rewards & allow for point redemption
		}
		
		public void redeemPoints(int customerId) {
			//redeem points
		}
		
		//view points history and rewards
		public static void viewHistory(int customerId) {
			//get points history (earned and redeemed)
			String pointsQuery= "SELECT p.amount, p.source, p.date"
					+ " FROM pts_transactions p"
					+ " WHERE customer_Customer_ID =" + customerId
					+ " ORDER BY p.date DESC;";
			
			//get rewards history (redeemed or not)
			String rewardsQuery= "SELECT r.is_Used, r.date_Obtained, r.date_Used, r.valid_Until, a.description, a.pts_Required"
					+ " FROM rewards_distribution r"
					+ " JOIN available_rewards a ON r.available_Rewards_Reward_ID = a.reward_ID"
					+ " WHERE r.customer_Customer_ID = " + customerId
					+ " ORDER BY r.dateObtained";
			
			try(Connection conn= SQLConnector.getConnection();
					Statement stm= conn.createStatement()){
				//points
				ResultSet res= stm.executeQuery(pointsQuery);
				System.out.println("Points history:\n");
				while (res.next()) {
					float pointsAmount= res.getFloat("amount");
					System.out.printf("%-10s | %-9.2f | %-15s | %-10s\n\n", 
			               pointsAmount>0?"Earned":"Redeemed",
			               pointsAmount,
			               res.getString("source"),
			               res.getObject("date", LocalDateTime.class).toLocalDate());
				}
				
				//rewards history
				System.out.println("Rewards history:\n");
				res= stm.executeQuery(rewardsQuery);
				while (res.next()) {
					String statusDisplay; //calculate if the reward has been used yet or not 
					Object dateUsed = res.getObject("date_Used");
				    if (dateUsed == null) {
				        
				        statusDisplay = "Valid until: " + res.getObject("valid_Until", LocalDateTime.class).toLocalDate();
				        // If null, show the expiration date
				    } else {
				        statusDisplay = "Used on: " + ((java.sql.Timestamp) dateUsed).toLocalDateTime().toLocalDate();
				        // If not null, show the usage date
				    }
					System.out.printf("%-25s | Earned on %-12s | %-10s | %-5spoints\n", 
							  res.getString("description"),
							  res.getObject("date_Obtained", LocalDateTime.class).toLocalDate(),
		                      statusDisplay, 
		                      res.getInt("pts_Required"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void viewAllAvailableRewardsForGym(int gymId) {
			//view all available rewards a gym has to offer and the point count they are available at
		}
}
