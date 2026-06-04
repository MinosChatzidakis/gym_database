package Test;

public class Trainer {
	
	    private int Trainer_id;
	    private String name;
	    private String Specialty;
	    private String phone;
	    private String email;
	    
	    
	    
	    public Trainer(int Trainer_id, String name, String email, String phone, int gymCode) {
	        this.Trainer_id = Trainer_id;
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        
	    }
	    
	    public int getTrainer_id() {
	        return Trainer_id;
	    }
	    
	    public void setTrainer_id(int Trainer_id) {
	        this.Trainer_id = Trainer_id;
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
