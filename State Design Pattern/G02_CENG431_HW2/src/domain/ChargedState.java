package domain;

public class ChargedState extends State {
	
//	setTotalPrice (Order, Customer) :void

	@Override
	public void doAction(Order order, Customer customer, int orderId) {
		
//		System.out.println("in ChargeState");
		
		
		double totalPrice = order.getCargoPrice() + order.getProductPrice();
		
		if(customer.getSaving() >= totalPrice) {
//			order.setState(new ShippedState());
			order.setTotalPrice(totalPrice);
			Store.chargeCustomer(customer, totalPrice);
			System.out.println("The order is charged!");
//			order.doAction(customer, orderId);
		}
		else {
			Store.cancelPayment(order);
			System.out.println("Payment cancelled");
			System.out.println("Customer saving " + customer.getSaving());
			System.out.println("Total Price " + totalPrice);
		}	
	}
	
	public String toString() {
		return " charged";
	}
}
