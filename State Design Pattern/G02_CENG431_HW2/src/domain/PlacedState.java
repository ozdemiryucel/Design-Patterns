package domain;

import java.util.Random;

public class PlacedState extends State {
	
//	setProductAndCargoPrice(Order, Customer) :void

	@Override
	public void doAction(Order order, Customer customer, int orderId) {
		
//		System.out.println("in PlacedState");
		System.out.println("Your order is in placed!");
		
		Random r = new Random();
		int low =  100;
		int high = 500;
		
		int distance = r.nextInt(high-low) + low;
		
		double cargoPrice = distance * 0.53;
		double productPrice = order.getWeight() * 55;
		
		order.setCargoPrice(cargoPrice);
		order.setProductPrice(productPrice);
		
//		order.setState(new ChargedState());
//		order.doAction(customer, orderId);
		
	}
	
	public String toString() {
		return " placed";
	}



}
