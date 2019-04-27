package domain;

public class Order {
	
	private int orderId;
	private int trackingNumber;
	private int customerId;
	private String customerName;
	private int weight;
	private String shippingAddress;
	private int dateShipped;
	private int dateDelivered;
	private double productPrice;
	private double cargoPrice;
	private double totalPrice;
//	private DriedFruit driedFruit;
	private State currentState;
	
	public Order() {
//		this.customerId = customerId;
//		this.customerName = customerName;
//		this.shippingAddress = shippingAddress;
		this.currentState = new SavedState();
	}
	
	
	public void doAction(Customer customer, int orderId) {
		currentState.doAction(this, customer, orderId);
	}
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(int trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public int getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(int dateShipped) {
		this.dateShipped = dateShipped;
	}

	public int getDateDelivered() {
		return dateDelivered;
	}

	public void setDateDelivered(int dateDelivered) {
		this.dateDelivered = dateDelivered;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getCargoPrice() {
		return cargoPrice;
	}

	public void setCargoPrice(double cargoPrice) {
		this.cargoPrice = cargoPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public State getState() {
		return currentState;
	}

	public void setState(State state) {
		this.currentState = state;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", trackingNumber=" + trackingNumber + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", weight=" + weight + ", shippingAddress=" + shippingAddress
				+ ", dateShipped=" + dateShipped + ", dateDelivered=" + dateDelivered + ", productPrice=" + productPrice
				+ ", cargoPrice=" + cargoPrice + ", totalPrice=" + totalPrice + ", state=" + currentState.toString() + "]";
	}

//	public DriedFruit getDriedFruit() {
//		return driedFruit;
//	}
//
//	public void setDriedFruit(DriedFruit driedFruit) {
//		this.driedFruit = driedFruit;
//	}
	
	

}
