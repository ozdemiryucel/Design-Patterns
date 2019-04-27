package domain;

public class Employee extends Person {

	public Employee(String resourceId) {
		super(resourceId);
		
	}
	
	public Employee(String resourceId, Task t) {
		super(resourceId, t);
		
	}

	@Override
	public String toString() {
		return "Employee " + this.getResourceId() + " - " + this.getTaskList();
	}

}
