package Gym_project;

public class Gym {
	
	public int gymCode;
    private String name;
	private String address;
	private String city;
	private String phone;
    private String email;
	private String services;

    public Gym(String city , String services , String address , String name, String email, String phone, int gymCode) {
		this.city = city;
		this.services = services;
		this.address = address;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gymCode = gymCode;
    }

	public String getCity() {
        return city;
    }

	public void setCity(String city) {
		if(city==null || city.lenght() == 0){
			throw new IllegalArgumentException("The city field cant be empty.");
		}	
        this.city = city;
    }

	
	public String getServices() {
        return services;
    }

	public void setServices(String services) {
		if(services==null || services.lenght() == 0){
			throw new IllegalArgumentException("The services field cant be empty.");
		}	
        this.services = services;
    }
	
	public String getAddress() {
        return address;
    }

	public void setAddress(String address) {
		if(address==null || address.lenght() == 0){
			throw new IllegalArgumentException("The address field cant be empty.");
		}	
        this.address = address;
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
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) {
		if(email==null || email.length() == 0){
			throw new IllegalArgumentException("The email field cant be empty.");
		}
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
		if(phone==null || phone.length() == 0){
			throw new IllegalArgumentException("The phone field cant be empty.");
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
