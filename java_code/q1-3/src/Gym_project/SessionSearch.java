package Gym_project;

public class SessionSearch {
	private String city;
    private int preferredGymCode;
    private String trainingType;
    private String date;
	private String time;
	private String trainerId;
	private String additionalServices;
	private boolean invoiceNeeded;
	
	
    public SessionSearch(int preferredGymCode, String city, String trainingType , String date , String time , String trainerId , String additionalServices , boolean invoiceNeeded) {
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
        this.preferredGymCode = preferredGymCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
	
    public String getTrainingType() { 
        return trainingType; 
    }
	
    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

	
	public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

	public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
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
