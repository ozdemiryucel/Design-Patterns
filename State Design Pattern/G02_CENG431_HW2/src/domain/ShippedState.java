package domain;

import java.util.Random;

public class ShippedState extends State {
	
//	setShippingDate(Order) :void

	@Override
	public void doAction(Order order, Customer customer, int orderId) {
		
//		System.out.println("in ShippedState");
		
		Random r = new Random();
		int low =  1;
		int high = 10;
		
		int realShippingDurationInDays = r.nextInt(high-low) + low;
		int plannedShippingDurationInDays = r.nextInt(high-low) + low;
		
		
		System.out.println("realShippingDurationInDays - " + realShippingDurationInDays);
		System.out.println("plannedShippingDurationInDays - " + plannedShippingDurationInDays);
		
		if( (realShippingDurationInDays - plannedShippingDurationInDays) > 7 ) {
			order.setState(new ChargedState());
			System.err.println("Error: Not shipped");
		}
		else {
			Store.shipOrder(order, realShippingDurationInDays);
			System.out.println("The order is shipped!\n");
			order.setState(new DeliveredState());
			order.doAction(customer, orderId);
		}
		
		
	}
	
	public String toString() {
		return " shipped";
	}



}
