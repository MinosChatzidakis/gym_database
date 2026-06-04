package gym_project;

public class Services {
	private String serviceName;
	private int gymCode;
	
	
	public Services(String serviceName, int gymCode) {
		
		this.serviceName = serviceName;
		this.gymCode = gymCode;
		
	}
	
	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public int getGymCode() {
		return gymCode;
	}
	
	public void setGymCode(int gymCode) {
		this.gymCode = gymCode;
	}
	
}
