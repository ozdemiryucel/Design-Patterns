package domain;

import java.util.Random;

public class DeliveredState extends State {

	@Override
	public void doAction(Order order, Customer customer, int orderId) {
		
		//Planned and real delivery duration in days will be generated randomly between 3-12.
		Random r = new Random();
		int low =  3;
		int high = 12;
		
		int realDeliveryDurationInDays  = r.nextInt(high-low) + low;
		int plannedDeliveryDurationInDays  = r.nextInt(high-low) + low;
		
		
		System.out.println("realDeliveryDurationInDays  - " + realDeliveryDurationInDays );
		System.out.println("plannedDeliveryDurationInDays  - " + plannedDeliveryDurationInDays );
		
		if( (realDeliveryDurationInDays  - plannedDeliveryDurationInDays ) > 8 ) {
			order.setState(new ChargedState());
			System.err.println("Error: Lost in shipping");
		}
		else {
			Store.deliverOrder(order, realDeliveryDurationInDays);
			System.out.println("The order is delivered!");
		}	
	}
	
	public String toString() {
		return " delivered";
	}
}
