package dataAccess;

import java.util.Scanner;

public class Console {
	
	private static Scanner keyboard = null;

	private Console() {}
	
	public static String getString(String str) {

		if (keyboard == null)
			keyboard = new Scanner(System.in);
		
		System.out.println("\n" + str);
		String entry = keyboard.nextLine().trim();
		
		isQuit(entry);
		
		return entry;
	}
	
	public static int getInteger(String str) {

		if (keyboard == null)
			keyboard = new Scanner(System.in);
		
		System.out.println("\n" + str);
		String entry = keyboard.nextLine().trim();
			
		isQuit(entry);
		
		try {
			int number = Integer.valueOf(entry);
			return number;
		}
		catch(Exception e) {
			System.out.println("Enter an integer !");
			return getInteger(str);		
		}	
	}
	
	public static double getDouble(String str) {

		if (keyboard == null)
			keyboard = new Scanner(System.in);
		
		System.out.println("\n" + str);
		String entry = keyboard.nextLine().trim();
		
		isQuit(entry);
		
		try {
			double number = Double.valueOf(entry);
			return number;
		}
		catch(Exception e) {
			System.out.println("Enter an double !");
			return getInteger(str);		
		}	
	}
	
	public static void print(Object obj) {
		System.out.println(obj.toString());
	}
	
	private static void isQuit(String entry) {
		if(entry.equals("quit")) {
			close();
			System.out.println("Quited (in Keyboard class)");
			System.exit(0);
		}			
	}
	
	public static void close() {
		keyboard.close();
		System.out.println("Keyboard closed");
	}
}
