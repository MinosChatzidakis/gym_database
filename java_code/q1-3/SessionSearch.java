package gym_project;

public class SessionSearch {
	private String city;
    private int preferredGymCode;
    private String trainingType;
   
    public SessionSearch(int preferredGymCode, String city, String trainingType) {
        this.preferredGymCode = preferredGymCode;
        this.city = city;
        this.trainingType = trainingType;
        
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

}
