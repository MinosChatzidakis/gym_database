package Gym_project;

public class Customer {
	
	 private int customerID;
	 private String name;
	 private String surname;
	 private String email;
	 private String phone;
	 private int gymCode;

	 public Customer(int customerID , String surname , String name, String email, String phone, int gymCode) {
		 this.customerID = customerID;
		 this.surname = surname;
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
		 if(name==null || name.length() == 0){
			throw new IllegalArgumentException("The name field cant be empty.");
		}
		 this.name = name;
	 }
	 
	 public String getSurname() {
		 return surname;
	 }

	 public void setSurname(String surname) {
		 if(surname==null || surname.length() == 0){
			throw new IllegalArgumentException("The surname field cant be empty.");
		}
		 this.surname = surname;
	 }
	 
	 public String getEmail() { 
		 return email; 
	 }
	    
	 public void setEmail(String email) {
		 if(email==null || email.length() == 0 || !email.contains("@")){
			throw new IllegalArgumentException("The email field cant be empty.");
		}
		 this.email = email;
	 }

	 public String getPhone() {
		 return phone;
	 }

	 public void setPhone(String phone) {
		 if(phone==null || phone.length() == 0 || !(phone.length()==10)){
			throw new IllegalArgumentException("Wrong input for the phone field.");
		}
		 this.phone = phone;
	 }

	 public int getGymCode() {
		 return gymCode; 
	 }

	 public void setGymCode(int gymCode) {
		 this.gymCode = gymCode; 
	 }
}
