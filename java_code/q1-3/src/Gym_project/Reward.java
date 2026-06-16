package Gym_project;

public class Reward {
	private int rewardID;
    private String description;
    private int validFor;
    private int ptsRequired;


    public Reward(int rewardID, String description, int validFor, int ptsRequired) {
        this.rewardID = rewardID;
        this.description = description;
        this.validFor = validFor;
        this.ptsRequired = ptsRequired;
    }
    
    public Reward(String description, int validFor, int ptsRequired) {
    	this.description = description;
        this.ptsRequired = ptsRequired;
        this.validFor= validFor;
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
