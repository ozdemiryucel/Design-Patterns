package domain;

public abstract class State {
	
	public abstract void doAction(Order order, Customer customer, int orderId);
	
}
