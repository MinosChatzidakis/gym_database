package Gym_project;

public class Customer {
	 private int customerID;
	 private String name;
	 private String email;
	 private String phone;
	 private int gymCode;


	 public Customer(int customerID, String name, String email, String phone, int gymCode) {
		 this.customerID = customerID;
		 this.name = name;
		 this.email = email;
		 this.phone = phone;
		 this.gymCode = gymCode;
	 }

	 public int getCustomerID() {
		 return customerID;
	 }

	 public void setCustomerID(int customerID) {
		 this.customerID = customerID;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }
	 public String getEmail() { 
		 return email; 
	 }
	    
	 public void setEmail(String email) {
		 this.email = email;
	 }

	 public String getPhone() {
		 return phone;
	 }

	 public void setPhone(String phone) {
		 this.phone = phone;
	 }

	 public int gymCode() {
		 return gymCode; 
	 }

	 public void setGymCode(int gymCode) {
		 this.gymCode = gymCode; 
	 }
}
