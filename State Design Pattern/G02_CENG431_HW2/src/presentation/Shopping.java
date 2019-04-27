package presentation;
import java.util.List;

import dataAccess.FileOperations;
import dataAccess.Keyboard;
import domain.*;


import java.util.ArrayList;

public class Shopping {
	
	private List<Customer> listOfCustomer;
	private List<Order> listOfOrder;
	
	public Shopping() {
		
		try {
			listOfCustomer = FileOperations.readCustomersFromFile();
		}
		catch (Exception e) {
			listOfCustomer = new ArrayList<Customer>();
			System.out.println("catch 1");
		}
		
		try {
			listOfOrder = FileOperations.readDeliveredOrdersFromFile();
		}
		catch (Exception e) {
			listOfOrder = new ArrayList<Order>();
			System.out.println("catch 2");
		}	
		
		printLists();
	}
		
	
	public void run() {
		
		Customer customer = getCustomer();
		System.out.println("Welcome " + customer.getName().toUpperCase());
		System.out.println(customer.toString());
		
		List<Order> tempList = new ArrayList<Order>();
		
		while(true) {
			
			printLists();
			
			tempList.clear();
			
			Order order = null;
			System.out.println(menu());
			
			int operation = Keyboard.getInteger("Select operation:");
			switch (operation) {
			
				//Save order
				case 1: 
					order = customer.saveOrder();
					int orderId;
					if(this.listOfOrder.isEmpty())
						orderId = 0;
					else
						orderId = this.listOfOrder.get(this.listOfOrder.size()-1).getOrderId() + 1;
					
					order.doAction(customer, orderId);
					this.listOfOrder.add(order);
					System.out.println("The order is saved!");
					break;
					
					
				//In this case by getting the order id from the customer
				//submit operation of order is performed and state of order is changed to PlacedState.
				//According to user's wants order's state can be change to ChargedState, ShippedState or CancelledState.
				case 2: 
					System.out.println("Saved orders of yours:");
					for(Order o : this.listOfOrder)
						if (o.getCustomerId() == customer.getCustomerId() && o.getState().getClass() == SavedState.class) {
							tempList.add(o);
							System.out.println("ID: " + o.getOrderId());
						}
					
					System.out.print("Enter Order ID you want to submit:");
					order = customer.submitOrder(tempList);
					
					if (order != null && order.getState().getClass() == SavedState.class) {
						for (Order o : listOfOrder) {
							if (o.getOrderId() == order.getOrderId()) {
								o.setState(new PlacedState());
								o.doAction(customer, o.getOrderId());
								
								
								String entry = Keyboard.getString("Do you want to Charge or Cancel (charge or cancel)").toLowerCase();
								if (entry.equals("charge") || entry.trim().equals("")) {
									o.setState(new ChargedState());
									o.doAction(customer, o.getOrderId());
									
									// System.out.println("Your order is charged!");
									
									entry = Keyboard.getString("Do you want to Ship or Cancel (ship or cancel)").toLowerCase();
									if (entry.equals("ship") || entry.trim().equals("")) {
										o.setState(new ShippedState());
										o.doAction(customer, o.getOrderId());
									}
									else if (entry.equals("cancel")) {
										o.setState(new CancelledState());
										customer.setSaving(customer.getSaving() + o.getTotalPrice());
										o.doAction(customer, o.getOrderId());
										break;
									}
								}
								else if (entry.equals("cancel")) {
									o.setState(new CancelledState());
									o.doAction(customer, o.getOrderId());
									break;
								}
								
								
								break;
							}
						}
					}				
					break;
					
					
					
				//In this case by getting the order id from the customer
				//cancellation operation of order is performed and state of order is changed to CancelledState.
				case 3: 
					for(Order o : this.listOfOrder)
						if (o.getCustomerId() == customer.getCustomerId()) {
							if (o.getState().getClass() == PlacedState.class ||
									o.getState().getClass() == ChargedState.class)
							tempList.add(o);
							System.out.println(o.toString());
						}
					
					System.out.println("Enter Order ID you want to cancel:");
					order = customer.cancelOrder(tempList);
					
					if (order != null && order.getState().getClass() == PlacedState.class ||
							order.getState().getClass() == ChargedState.class) {
						for (Order o : listOfOrder) {
							if (o.getOrderId() == order.getOrderId()) {
								o.setState(new CancelledState());
								o.doAction(customer, o.getOrderId());
								break;
							}
						}
					}	
					break;
					
					
					
				//In this case by getting the order id from the customer
				//deletion operation of order is performed.
				case 4: 
					for(Order o : this.listOfOrder)
						if (o.getCustomerId() == customer.getCustomerId()) {
							if (o.getState().getClass() == SavedState.class)
							tempList.add(o);
							System.out.println(o.toString());
						}
					
					System.out.println("Enter Order ID you want to delete:");
					order = customer.deleteOrder(tempList);
					
					if (order != null && order.getState().getClass() == SavedState.class) {
						for (Order o : listOfOrder) {
							if (o.getOrderId() == order.getOrderId()) {
								listOfOrder.remove(o);
								break;
							}
						}
					}	
					break;
					
				//Quit and save operations
				//It is the case that where writing file operations are performed.
				case 0: 
					FileOperations.writeCustomersToFile(listOfCustomer);
					FileOperations.writeDeliveredOrdersToFile(listOfOrder);
					Keyboard.close();
					System.out.println("Quited");
					System.exit(0);
				default:
					System.out.println("Enter a valid entry!");
					break;
			}
			
		}
			
	}
	
	private void printLists() {
		
		System.out.println("----------------");
		System.out.println("Orders:");
		for(Order o : listOfOrder)
			System.out.println(o.toString());
		System.out.println("----------------");
		System.out.println("Customers:");
		for(Customer c : listOfCustomer)
			System.out.println(c.toString());
		System.out.println("----------------");
		
	}

	// This method is responsible for visualising user interface of 
	// the Shopping application.
	private String menu() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("-------MENU-------\n");
		builder.append("1) Save order\n");
		builder.append("2) Submit order\n");
		builder.append("3) Cancel order\n");
		builder.append("4) Delete order\n");
		builder.append("0) Quit\n");
		builder.append("------------------");

		return builder.toString();
		
	}
	
	// This method is responsible for login or register operations 
	// in order customer to process.
	private Customer getCustomer() {
		
		String loginOrRegister = Keyboard.getString("Login or Register").substring(0, 1).toUpperCase();
		
		Customer customer = null;
		
		boolean isLogin = false; 
		
		// Register operations
		if(loginOrRegister.equals("R") || this.listOfCustomer.isEmpty()) {
			
			if(!loginOrRegister.equals("R") && this.listOfCustomer.isEmpty())
				System.out.println("There is no customer, so you must register");
			
			int customerId = 0;
			if(!this.listOfCustomer.isEmpty())
				customerId = this.listOfCustomer.get(this.listOfCustomer.size()-1).getCustomerId() + 1;
			
			String name = Keyboard.getString("Enter your name:");
			String address = Keyboard.getString("Enter your address:");
			int phone = Keyboard.getInteger("Enter your phone:");
			String mail = getMailForRegister();
			
			String password = Keyboard.getString("Enter your password:");
			
			double saving = Keyboard.getDouble("Enter your saving (TL):");
			
			customer = new Customer(customerId, name, address, phone, mail, password, saving);
			this.listOfCustomer.add(customer);					
			
		}
		
		// Login operations
		else if (loginOrRegister.equals("L") && !this.listOfCustomer.isEmpty()) {
			
			while(true) {
			
				String mail = Keyboard.getString("Enter your mail:");
				String password = Keyboard.getString("Enter your password:");
				
				for(Customer c : this.listOfCustomer) {
					if (c.getMail().equals(mail) && c.getPassword().equals(password)) {
						customer = c;
						isLogin = true;
						if(isLogin)
							break;
					}
				}
				if(isLogin)
					break;
			}
		}
		
		else {
			System.err.println("FAIL");
			return getCustomer();
		}
		
		return customer;
		
	}

	// This method is responsible for getting mail address of user
	// by checking whether a user is already registered or not
	// and checking the mail address is in the right format or not.
	private String getMailForRegister() {
		
		String mail;
		
		while(true) {
			boolean alreadyRegistered = false;
			
			mail = Keyboard.getString("Enter your mail:");
			
			for(Customer c : listOfCustomer) {
				if(c.getMail().equals(mail)) {
					alreadyRegistered = true;
					System.out.println("This mail is already registered!");
				}
			}
			if(!alreadyRegistered) {
				if(mail.contains("@"))
					break;
				else
					System.out.println("Put @ inside mail!");
			}
		}
		
		return mail;
	}

}
