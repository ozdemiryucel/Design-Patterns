package presentation;

import domain.TextEditorComposite;
import domain.ITextEditorComponent;

public class TextEditorApp {
	
	private ITextEditorComponent component;
	
	public TextEditorApp() {
		component = new TextEditorComposite();
	}
	
	public void run() {
		while(true) {
			showMenu();
			
			String operation = Console.getString("Select operation:");
			
			component.operation(operation);
		}
	}
	
	public void showMenu() {
		Console.print("1) Enter console input and save it");
		Console.print("2) Read from file");
		Console.print("3) Count number of words, line, paragraph and character");
		Console.print("4) Search, list and count");
		Console.print("5) Correct and print the text");
		Console.print("q) Enter q to exit");
	}

}
