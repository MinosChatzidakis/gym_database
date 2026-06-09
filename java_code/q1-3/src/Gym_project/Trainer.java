package Gym_project;

public class Trainer {

	    
	    private int TrainerID;
	    private String name;
	    private String specialty;
	    private String phone;
	    private String email;
	    private int gymCode;
	    
	    
	    public Trainer(int TrainerID, String name, String email, String phone, int gymCode , String specialty) {
	    	this.TrainerID = TrainerID;
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
			this.specialty = specialty;
	        this.gymCode = gymCode;
	    }
	    
	    public Trainer() {
	    	
	    }
	    
	    public int getTrainerID() {
	        return TrainerID;
	    }

		public void setTrainerID(int TrainerID) {
	        this.TrainerID = TrainerID; 
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

	    public String  getSpecialty() {
	        return specialty; 
	    }
	    
	    public void setSpecialty(String specialty) {
			if(specialty==null || specialty.length() == 0){
			throw new IllegalArgumentException("The specialty field cant be empty.");
			}
	        this.specialty = specialty; 
	    }
	    
}
