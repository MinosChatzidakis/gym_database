package Gym_project;

public class Gym {
	private int customerId;
    private String name;
    private String email;
    private String phone;
    public int gymCode;


    public Gym(int customerId, String name, String email, String phone, int gymCode) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gymCode = gymCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
		if(name==null || name.lenght() == 0){
			System.out.print("The name field cant be empty.")΄
		}	
        this.name = name;
    }
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
		if(email==null || email.lenght() == 0){
			System.out.print("The email field cant be empty.")΄
		}
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
		if(phone==null || phone.lenght() == 0){
			System.out.print("The phone field cant be empty.")΄
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
