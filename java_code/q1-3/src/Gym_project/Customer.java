package Gym_project;

public class Customer {
	 private static int num;
	 private int customerID;
	 private String name;
	 private String email;
	 private String phone;
	 private int gymCode;


	 public Customer(String name, String email, String phone, int gymCode) {
		 this.customerID = num++;
		 this.name = name;
		 this.email = email;
		 this.phone = phone;
		 this.gym_Code = gym_Code;
	 }

	 public int getCustomerID() {
		 return customerID;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 if(name==null || name.lenght() == 0){
			throw new IllegalArgumentException("The name field cant be empty.");
		}
		 this.name = name;
	 }
	 public String getEmail() { 
		 return email; 
	 }
	    
	 public void setEmail(String email) {
		 if(email==null || email.lenght() == 0){
			throw new IllegalArgumentException("The email field cant be empty.");
		}
		 this.email = email;
	 }

	 public String getPhone() {
		 return phone;
	 }

	 public void setPhone(String phone) {
		 if(phone==null || phone.lenght() == 0){
			throw new IllegalArgumentException("The phone field cant be empty.");
		}
		 this.phone = phone;
	 }

	 public int gymCode() {
		 return gymCode; 
	 }

	 public void setGymCode(int gymCode) {
		 this.gymCode = gymCode; 
	 }
}
