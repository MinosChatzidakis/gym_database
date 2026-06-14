package Gym_project;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionSearch {
	private String city;
    private int preferredGymCode;
    private String trainingType;
    private LocalDate date;
	private LocalTime time;
	private int trainerId;
	private String additionalServices;
	private boolean invoiceNeeded;
	
	
    public SessionSearch(int preferredGymCode, String city, String trainingType , LocalDate date , LocalTime time , int trainerId , String additionalServices , boolean invoiceNeeded) {
        this.preferredGymCode = preferredGymCode;
        this.city = city;
        this.trainingType = trainingType;
        this.date = date;
		this.time = time;
		this.trainerId = trainerId;
		this.additionalServices = additionalServices;
		this.invoiceNeeded = invoiceNeeded;
    }

    public int getPreferredGymCode() {
        return preferredGymCode;
    }

    public void setPreferredGymCode(int preferredGymCode) {
		if(preferredGymCode<=0){
			throw new IllegalArgumentException("The preferred Gym Code can't be a negative number.");
		}
        this.preferredGymCode = preferredGymCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
		if(city==null || city.length() == 0){
			throw new IllegalArgumentException("The city field cant be empty.");
		}
        this.city = city;
    }
	
    public String getTrainingType() { 
        return trainingType; 
    }
	
    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

	public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
		if(date==null ){
			throw new IllegalArgumentException("The date field cannot be empty.");
		}
        this.date = date;
    }

	
	public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
		if(time==null){
			throw new IllegalArgumentException("The time field cant be empty.");
		}
        this.time = time;
    }

	public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

	public boolean getInvoiceNeeded() {
        return invoiceNeeded;
    }

    public void setInvoiceNeeded(boolean invoiceNeeded) {
        this.invoiceNeeded = invoiceNeeded;
    }

	public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }
}
