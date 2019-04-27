package dataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import domain.Customer;
import domain.DeliveredState;
import domain.Order;

public class FileOperations {
	
	private static String fileDirectory = System.getProperty("user.dir");

	public static List<Customer> readCustomersFromFile() {
		
		String fileName = "customers.json";
//		System.out.println(fileName);
		
		File file = new File(fileDirectory + "\\" + fileName); 
		  
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} 
	  
		String contentOfFile = ""; 
		try {
			contentOfFile = br.readLine();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
//		System.out.println(contentOfFile);
		
		JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(contentOfFile);
        
        Gson gson = new Gson();
        
        List<Customer> listOfCustomer = new ArrayList<Customer>();
        
        for (JsonElement o : element.getAsJsonArray()) {
        	
        	//System.out.println(o.toString());
        	
        	Customer customer = gson.fromJson(o, Customer.class);
        	listOfCustomer.add(customer);
        	//System.out.println(project.getActivityList());     	
        }
		
		
		return listOfCustomer;
	}

	public static List<Order> readDeliveredOrdersFromFile() {

		String fileName = "deliveredOrders.json";
//		System.out.println(fileName);
		
		File file = new File(fileDirectory + "\\" + fileName); 
		  
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} 
	  
		String contentOfFile = ""; 
		try {
			contentOfFile = br.readLine();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
//		System.out.println(contentOfFile);
		
		JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(contentOfFile);
        
        Gson gson = new Gson();
        
        List<Order> listOfOrder = new ArrayList<Order>();
        
        for (JsonElement o : element.getAsJsonArray()) {
        	
        	//System.out.println(o.toString());
        	
        	Order order = gson.fromJson(o, Order.class);
        	order.setState(new DeliveredState());
        	listOfOrder.add(order);
        	//System.out.println(project.getActivityList());     	
        }
		
		
		return listOfOrder;
	}

	public static void writeCustomersToFile(List<Customer> list) {
			
		String fileName = "customers.json";
		String filePath = fileDirectory + "//" + fileName;
		
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory())
		    f.delete();
		
		try (Writer writer = new FileWriter(filePath)) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(list, writer);
		    System.out.println(fileName + " is saved!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void writeDeliveredOrdersToFile(List<Order> list) {
		
		List<Order> willSaveList = new ArrayList<Order>();
		
		for (Order o : list)
			if(o.getState().getClass() == DeliveredState.class)
				willSaveList.add(o);
		
		for (Order o : willSaveList)
			o.setState(null);
		
			
		String fileName = "deliveredOrders.json";
		
		String filePath = fileDirectory + "//" + fileName;
		
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory())
		    f.delete();
		
		try (Writer writer = new FileWriter(filePath)) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(willSaveList, writer);
		    System.out.println(fileName + " is saved!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
