package domain;

public class Consultant extends Person {

	public Consultant(String resourceId) {
		super(resourceId);
		
	}
	
	public Consultant(String resourceId, Task t) {
		super(resourceId, t);
		
	}

	@Override
	public String toString() {
		return "Consultant " + this.getResourceId() + " - " + this.getTaskList();
	}
	
	

}
