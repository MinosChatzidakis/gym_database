	package Gym_project;

import java.time.LocalDateTime;

public class Session {
		private int sessionCode;
	    private String sessionType;
	    private String description;
	    private int maxParticipants;
	    private int duration;
	    private float price;
	    private int availability;
	    private int trainerID;
	    private int gymCode;
		private LocalDateTime dateAndTime;
		private int amountOfParticipants;
	
	    public Session(int sessionCode, String sessionType, String description, int maxParticipants , int duration , float price , int availability , int trainerID , int gymCode , LocalDateTime dateAndTime, int amountOfParticipants) {
	        this.sessionCode = sessionCode;
	        this.sessionType = sessionType;
	        this.description = description;
	        this.maxParticipants = maxParticipants;
	        this.duration = duration;
	        this.price = price;
	        this.availability = availability;
	        this.trainerID = trainerID;
	        this.gymCode = gymCode;
			this.dateAndTime = dateAndTime;
			this.amountOfParticipants = amountOfParticipants;
	    }
	    
	
	    public Session(String sessionType, String description, int maxParticipants , int duration , float price , int availability , int trainerID , int gymCode , LocalDateTime dateAndTime, int amountOfParticipants) {
	        this.sessionType = sessionType;
	        this.description = description;
	        this.maxParticipants = maxParticipants;
	        this.duration = duration;
	        this.price = price;
	        this.availability = availability;
	        this.trainerID = trainerID;
	        this.gymCode = gymCode;
			this.dateAndTime = dateAndTime;
			this.amountOfParticipants = amountOfParticipants;
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
	    
	    public float getPrice() {
	        return price;
	    }
	
	    public void setPrice(float price) {
	        this.price = price;
	    }
	    
	    public int getAvailability() {
	        return availability;
	    }
	    
	    public void setAvailability(int availability) {
	        if(availability != 1 && availability !=0) throw new  IllegalArgumentException("Invalid input");
	    	this.availability = availability;
	    }
	    
	    public int getTrainerTrainerID() {
	        return trainerID;
	    }
	
	    public void setTrainerID(int trainerTrainerID) {
	        this.trainerID = trainerTrainerID;
	    }
	    
	    public int getGymCode() {
	        return gymCode;
	    }
	
	    public void setGymCode(int gymGymCode) {
	        this.gymCode = gymGymCode;
	    }

		public LocalDateTime getDateAndTime() {
	        return dateAndTime;
	    }
	
	    public void setDateAndTime(LocalDateTime dateAndTime) {
			if(dateAndTime==null){
			throw new IllegalArgumentException("The Date And duration field cant be empty.");
			}
	        this.dateAndTime = dateAndTime;
	    }
    
	    public int getAmountOfParticipants() { 
	    	return this.amountOfParticipants;
	    }
    
	    public void setAmountOfParticipants(int amount) {
	    	if (amount>=0) {
	    		this.amountOfParticipants = amount;	    		
	    	} else {
	        	throw new IllegalArgumentException("The amount of participants cannot be a negative number.");
	    	}
	    }
	    
	    public void addParticipant() {
	    	setAmountOfParticipants(getAmountOfParticipants()+1);
	    }
}