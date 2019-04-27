package domain;

public class Store {
	
	public static void chargeCustomer(Customer customer, double charge) {
		customer.setSaving(customer.getSaving() - charge);
	}
	
	public static void cancelPayment(Order order) {
		order.setState(new PlacedState());
	}
	
	public static void shipOrder(Order order, int dateShipped) {
		order.setDateShipped(dateShipped);
	}
	
	public static void deliverOrder(Order order, int dateDelivered) {
		order.setState(new DeliveredState());
		order.setDateDelivered(dateDelivered);
	}

}
