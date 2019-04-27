package domain;

public class CancelledState extends State {

	@Override
	public void doAction(Order order, Customer customer, int orderId) {
		System.out.println(order.getOrderId() + " is cancelled");

	}
	
	public String toString() {
		return " cancelled";
	}

}
