	package Gym_project;
	
	public class Session {
		private int sessionCode;
	    private String sessionType;
	    private String description;
	    private int maxParticipants;
	    private int duration;
	    private int price;
	    private boolean availability;
	    private int trainerTrainerID;
	    private int gymGymCode;
		private String dateAndTime;
		private int amountOfParticipants;
	
	    public Session(int sessionCode, String sessionType, String description, int maxParticipants , int duration , int price , boolean availability , int trainerTrainerID , int gymGymCode , String dateAndTime) {
	        this.sessionCode = sessionCode;
	        this.sessionType = sessionType;
	        this.description = description;
	        this.maxParticipants = maxParticipants;
	        this.duration = duration;
	        this.price = price;
	        this.availability = availability;
	        this.trainerTrainerID = trainerTrainerID;
	        this.gymGymCode = gymGymCode;
			this.dateAndTime = dateAndTime;
			this.amountOfParticipants = 0;
	    }
	    
	
	    public int getSessionCode() {
	        return sessionCode;
	    }
	
	    public void setSessionCode(int sessionCode) {
			if(sessionCode<=0){
			throw new IllegalArgumentException("The Session Code can't be a negative number.");
			}	
	        this.sessionCode = sessionCode;
	    }
	
	    public String getSessionType() {
	        return sessionType;
	    }
	
	    public void setSessionType(String sessionType) {
			if(sessionType==null || sessionType.length() == 0){
			throw new IllegalArgumentException("The Session Type field cant be empty.");
			}
	        this.sessionType = sessionType;
	    }
	    public String getDescription() { 
	        return description; 
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }
	
	    public int getMaxParticipants() {
	        return maxParticipants;
	    }
	
	    public void setMaxParticipants(int maxParticipants) {
			if(maxParticipants<=0){
			throw new IllegalArgumentException("The number of Max participants can't be negative.");
			}
	        this.maxParticipants = maxParticipants;
	    }
	    
	    public int getDuration() {
	        return duration;
	    }
	
	    public void setDuration(int duration) {
	        this.duration = duration;
	    }
	    
	    public int getPrice() {
	        return price;
	    }
	
	    public void setPrice(int price) {
	        this.price = price;
	    }
	    
	    public boolean getAvailability() {
	        return availability;
	    }
	    
	    public void setAvailability(boolean availability) {
	        this.availability = availability;
	    }
	    
	    public int getTrainerTrainerID() {
	        return trainerTrainerID;
	    }
	
	    public void setTrainerID(int trainerTrainerID) {
	        this.trainerTrainerID = trainerTrainerID;
	    }
	    
	    public int getGymGymCode() {
	        return gymGymCode;
	    }
	
	    public void setGymGymCode(int gymGymCode) {
	        this.gymGymCode = gymGymCode;
	    }

		public String getDateAndTime() {
	        return dateAndTime;
	    }
	
	    public void setDateAndTime(String dateAndTime) {
			if(dateAndTime==null || dateAndTime.length() == 0){
			throw new IllegalArgumentException("The Date And duration field cant be empty.");
			}
	        this.dateAndTime = dateAndTime;
	    }
	    
	    public int getAmountOfParticipants() {
	    	return this.amountOfParticipants;
	    }
	    
	    public void setAmountOfParticipants(int amount) {
	    	if (amount>=0) {
	    		this.amountOfParticipants= amount;	    		
	    	}else {
	    		throw new IllegalArgumentException("The amount of participants in this session cannot be a negative number.");
	    	}
	    }
	    
	    public void addParticipant() {
	    	setAmountOfParticipants(getAmountOfParticipants()+1);
	    }

	}
