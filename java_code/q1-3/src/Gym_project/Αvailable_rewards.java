package Gym_project;

public class Αvailable_rewards {
	private int rewardID;
    private String description;
    private int validFor;
    private int ptsRequired;


    public Αvailable_rewards(int rewardID, String description, int validFor, String phone, int ptsRequired) {
        this.rewardID = rewardID;
        this.description = description;
        this.validFor = validFor;
        this.ptsRequired = ptsRequired;
    }

    public int getRewardID() {
        return rewardID;
    }

    public void setRewardID(int rewardID) {
        this.rewardID = rewardID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPtsRequired() {
        return ptsRequired;
    }

    public void setPtsRequired(int ptsRequired) {
        this.ptsRequired = ptsRequired;
    }

    public int getValidFor() {
        return validFor; 
    }

    public void setValidFor(int validFor) {
        this.validFor = validFor; 
    }
}
