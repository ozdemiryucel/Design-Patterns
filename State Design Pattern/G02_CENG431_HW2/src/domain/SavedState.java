package domain;

import java.util.Random;

public class SavedState extends State {
	
//	initializeOrder(Order, Customer, int) :void

	@Override
	public void doAction(Order order, Customer customer, int orderId) {
		
//		System.out.println("in SavedState");
		

		Random r = new Random();
		int low =  100000;
		int high = 999999;
		
		int trackingNumber = r.nextInt(high-low) + low;
		int weight = customer.getWeight();
		
		order.setOrderId(orderId);
		order.setTrackingNumber(trackingNumber);
		order.setCustomerId(customer.getCustomerId());
		order.setCustomerName(customer.getName());
		order.setShippingAddress(customer.getAddress());
		order.setWeight(weight);
		System.out.println("The order is saved!");
		
		//order.setState(new PlacedState());
		
	}
	
	public String toString() {
		return " saved";
	}
}
