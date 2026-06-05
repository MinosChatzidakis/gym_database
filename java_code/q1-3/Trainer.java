package gym_project;

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
			this.specialty = specialty
	        this.gymCode = gymCode
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

	    public String  getSpecialty() {
	        return specialty; 
	    }
	    
	    public void setSpecialty(String specialty) {
	        this.specialty = specialty; 
	    }
	    
}
