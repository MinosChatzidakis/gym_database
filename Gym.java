package Test;

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

    public int getGymCode() {
        return gymCode; 
    }

    public void setGymCode(int gymCode) {
        this.gymCode = gymCode; 
    }
}
