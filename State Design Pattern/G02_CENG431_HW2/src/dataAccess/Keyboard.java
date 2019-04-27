package dataAccess;

import java.util.Scanner;

public class Keyboard {
	
	private static Scanner keyboard = new Scanner(System.in);
	
	public static String getString(String str) {
		
		System.out.println("\n" + str);
		String entry = keyboard.nextLine().trim();
		
		isQuit(entry);
		
		return entry;
	}
	
	public static int getInteger(String str) {
		
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
	
	private static void isQuit(String entry) {
		if(entry.equals("quit")) {
			System.out.println("Quited (in Keyboard class)");
			System.exit(0);
		}			
	}
	
	public static void close() {
		System.out.println("Keyboard closed");
		keyboard.close();
	}
}
