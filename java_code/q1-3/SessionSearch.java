package gym_project;

public class SessionSearch {
	private String city;
    private int preferredGymCode;
    private String trainingType;
    private String date;
	private String time;
	private String trainer;
	private String additionalServices΄
	private boolean receipt;
	
	
    public SessionSearch(int preferredGymCode, String city, String trainingType , String date , String time , String trainer , String additionalServices , boolean receipt) {
        this.preferredGymCode = preferredGymCode;
        this.city = city;
        this.trainingType = trainingType;
        this.date = date;
		this.time = time;
		this.trainer = trainer;
		this.additionalServices = additionalServices;
		this.receipt = receipt;
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

	public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

	public boolean getReceipt() {
        return receipt;
    }

    public void setReceipt(boolean receipt) {
        this.receipt = receipt;
    }

	public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }
}
