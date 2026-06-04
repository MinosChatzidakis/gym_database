package gym_project;

public class Customer {
	 private int customer_ID;
	 private String name;
	 private String email;
	 private String phone;
	 private int gym_Code;


	 public Customer(int customer_ID, String name, String email, String phone, int gym_Code) {
		 this.customer_ID = customer_ID;
		 this.name = name;
		 this.email = email;
		 this.phone = phone;
		 this.gym_Code = gym_Code;
	 }

	 public int getCustomerId() {
		 return customer_ID;
	 }

	 public void setCustomerId(int customerId) {
		 this.customer_ID = customerId;
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

	 public int getGym_Code() {
		 return gym_Code; 
	 }

	 public void setGymCode(int gym_Code) {
		 this.gym_Code = gym_Code; 
	 }
}
