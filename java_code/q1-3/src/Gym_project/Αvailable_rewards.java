package Gym_project;

public class Αvailable_rewards {
	private int reward_id;
    private String description;
    private int valid_for;
    private int pts_required;


    public Αvailable_rewards(int reward_id, String description, int valid_for, String phone, int pts_required) {
        this.reward_id = reward_id;
        this.description = description;
        this.valid_for = valid_for;
        this.pts_required = pts_required;
    }

    public int getReward_id() {
        return reward_id;
    }

    public void setReward_id(int reward_id) {
        this.reward_id = reward_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPts_required() {
        return pts_required;
    }

    public void setPts_required(int pts_required) {
        this.pts_required = pts_required;
    }

    public int getValid_for() {
        return valid_for; 
    }

    public void setValid_for(int valid_for) {
        this.valid_for = valid_for; 
    }
}
