package domain;

import java.util.List;

import dataAccess.Keyboard;

public class Customer {
	
	private int customerId;
	private String name;
	private String address;
	private int phone;
	private String mail;
	private String password;
	
	private double saving;
	
	public Customer(int customerId, String name, String address, int phone, String mail, String password, double saving) {
		this.setCustomerId(customerId);
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.mail = mail;
		this.password = password;
		this.setSaving(saving);
	}
	
	public Order saveOrder() {
		return new Order();
	}
	
	// The following method is responsible for checking the entered order id
	// where the user wants to perform submit order operation, is in order list or not. 
	// In the case of id is in order list, then returns that order with given id.
	// If not, then null is returned.
	public Order submitOrder(List<Order> list) {
		int orderId = Keyboard.getInteger("");
		for (Order o : list)
			if (o.getOrderId() == orderId)
				return o;
		
		return null;
	}

	// The following method is responsible for checking the entered order id
	// where the user wants to perform cancel order operation, is in order list or not. 
	// In the case of id is in order list, then returns that order with given id.
	// If not, then null is returned.
	public Order cancelOrder(List<Order> list) {
		int orderId = Keyboard.getInteger("");
		for (Order o : list)
			if (o.getOrderId() == orderId)
				return o;
		
		return null;
		
	}
	
	// The following method is responsible for checking the entered order id
	// where the user wants to delete order operation, is in order list or not. 
	// In the case of id is in order list, then returns that order with given id.
	// If not, then null is returned.
	public Order deleteOrder(List<Order> list) {
		int orderId = Keyboard.getInteger("");
		for (Order o : list)
			if (o.getOrderId() == orderId)
				return o;
		
		return null;
	}

	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getWeight() {
		return Keyboard.getInteger("Enter weight you want:");
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", mail=" + mail + ", password=" + password + ", saving=" + saving + "]";
	}

	public double getSaving() {
		return saving;
	}

	public void setSaving(double saving) {
		this.saving = saving;
	}
	


}
