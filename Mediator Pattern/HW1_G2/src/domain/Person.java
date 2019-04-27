package domain;

public abstract class Person extends Resource {

	public Person(String resourceId) {
		super(resourceId);
		
	}
	
	public Person(String resourceId, Task t) {
		super(resourceId, t);
		
	}
	
	
}
