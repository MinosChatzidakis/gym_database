package Test;

public class Trainer {
	
	    private int TrainerID;
	    private String name;
	    private String Specialty;
	    private String phone;
	    private String email;
	    
	    
	    
	    public Trainer(int TrainerID, String name, String email, String phone, int gymCode) {
	        this.TrainerID = TrainerID;
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        
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

	    public String  getSpecialty() {
	        return Specialty; 
	    }
	    
	    public void setSpecialty(String Specialty) {
	        this.Specialty = Specialty; 
	    }
	    
}
