package dataAccess;

import java.util.Scanner;

public class Keyboard {
	
	private static Scanner keyboard = new Scanner(System.in);
	
	public static String get() {
		
		String entry = keyboard.nextLine();
		
		if(entry.toUpperCase().equals("QUIT")) {
			System.out.println("Quited without saving");
			System.exit(0);
		}
		
		return entry;
	}
	
	public static void close() {
		System.out.println("Keyboard closed");
		keyboard.close();
	}

}
