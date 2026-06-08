	package Gym_project;
	
	public class Session {
		private int Session_Code;
	    private String Session_Type;
	    private String Description;
	    private int Max_participants;
	    private int Time;
	    private int Price;
	    private boolean Availability;
	    private int Trainer_Trainer_id;
	    private int GYM_Gym_code;
	
	    public Session(int Session_Code, String Session_Type, String Description, int Max_participants , int Time , int Price , boolean Availability , int Trainer_Trainer_id , int GYM_Gym_code) {
	        this.Session_Code = Session_Code;
	        this.Session_Type = Session_Type;
	        this.Description = Description;
	        this.Max_participants = Max_participants;
	        this.Time = Time;
	        this.Price = Price;
	        this.Availability = Availability;
	        this.Trainer_Trainer_id = Trainer_Trainer_id;
	        this.GYM_Gym_code = GYM_Gym_code;
	    }
	
	    public int getSession_Code() {
	        return Session_Code;
	    }
	
	    public void setSession_Code(int Session_Code) {
	        this.Session_Code = Session_Code;
	    }
	
	    public String getSession_Type() {
	        return Session_Type;
	    }
	
	    public void setSession_Type(String Session_Type) {
	        this.Session_Type = Session_Type;
	    }
	    public String getDescription() { 
	        return Description; 
	    }
	    public void setDescription(String Description) {
	        this.Description = Description;
	    }
	
	    public int getMax_participants() {
	        return Max_participants;
	    }
	
	    public void setMax_participants(int Max_participants) {
	        this.Max_participants = Max_participants;
	    }
	    
	    public int getTime() {
	        return Time;
	    }
	
	    public void setTime(int Time) {
	        this.Time = Time;
	    }
	    
	    public int getPrice() {
	        return Price;
	    }
	
	    public void setPrice(int Price) {
	        this.Price = Price;
	    }
	    
	    public boolean getAvailability() {
	        return Availability;
	    }
	    
	    public void setAvailability(boolean Availability) {
	        this.Availability = Availability;
	    }
	    
	    public int getTrainer_Trainer_id() {
	        return Trainer_Trainer_id;
	    }
	
	    public void setTrainer_Trainer_id(int Trainer_Trainer_id) {
	        this.Trainer_Trainer_id = Trainer_Trainer_id;
	    }
	    
	    public int getGYM_Gym_code() {
	        return GYM_Gym_code;
	    }
	
	    public void setGYM_Gym_code(int GYM_Gym_code) {
	        this.GYM_Gym_code = GYM_Gym_code;
	    }
	}
