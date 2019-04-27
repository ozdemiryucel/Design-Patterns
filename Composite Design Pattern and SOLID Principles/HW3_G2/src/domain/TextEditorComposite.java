package domain;

import java.util.ArrayList;
import java.util.List;

import presentation.Console;

//import java.util.HashSet;
//import java.util.Set;

public class TextEditorComposite implements ITextEditorComposite {
	
	private List<ITextEditorComponent> listOfComponents;
	private String text;
	
	public TextEditorComposite() {
		listOfComponents = new ArrayList<ITextEditorComponent>();
		text = null;
		initialize();
	}
	
	
//	Console.print("1) Enter console input and save it");
//	Console.print("2) Read from file");
//	Console.print("3) Count number of words, line, paragraph and character");
//	Console.print("4) Search, list and count");
//	Console.print("5) Correct and print the text");
//	Console.print("q) Enter q to exit");
	
	private void initialize() {
		ITextSaver textSaver = new TextSaver("text.txt");
		addComponent(textSaver);
		
		ITextReader textReader = new TextReader();
		addComponent(textReader);
		
		ICounter wordCounter = new WordCounter();
		ICounter lineCounter = new LineCounter();
		ICounter paragraphCounter = new ParagraphCounter();
		ICounter characterCounter = new CharacterCounter();
		addComponent(wordCounter);
		addComponent(lineCounter);
		addComponent(paragraphCounter);
		addComponent(characterCounter);
		
		ISearch search = new Search(text);
		IList list = new domain.List();
		IInputWordCounter inputWordCounter = new InputWordCounter();
		addComponent(search);
		addComponent(list);
		addComponent(inputWordCounter);
		
		IAutoCorrector autoCorrector = new AutoCorrector();
		addComponent(autoCorrector);
		
		text = getInstanceOf(ITextReader.class).operation("text.txt");
		
		
	}

	@Override
	public String operation(String str) {
		
		switch(str) {
			case "1":
				text = Console.getString("Enter text:");
				getInstanceOf(ITextSaver.class).operation(text);
				break;
				
			case "2":
				text = getInstanceOf(ITextReader.class).operation("text.txt");			
				break;
				
			case "3":
				Console.print("Number of words:" + getInstanceOf(IWordCounter.class).operation(text));
				Console.print("Number of lines:" + getInstanceOf(ILineCounter.class).operation(text));
				Console.print("Number of paragraph:" + getInstanceOf(IParagraphCounter.class).operation(text));
				Console.print("Number of characters:" + getInstanceOf(ICharacterCounter.class).operation(text));
				break;
			
			case "4":
				String s = getSearch().operation(Console.getString("Enter word to search:"));
				getInstanceOf(IList.class).operation(s);
				getInstanceOf(IInputWordCounter.class).operation(s);
				break;
			
			case "5":
				Console.print(getInstanceOf(IAutoCorrector.class).operation(text));
				break;
				
			case "q":
				Console.print("Quited");
				System.exit(0);
				break;
				
		}
		
		return null;
	}

	@Override
	public void addComponent(ITextEditorComponent component) {
		this.listOfComponents.add(component);
	}

	@Override
	public void removeComponent(ITextEditorComponent component) {
		this.listOfComponents.remove(component);
	}


	@Override
	public ISearch getSearch() {
		for (ITextEditorComponent c : this.listOfComponents) {
			if (c instanceof ISearch) {
				((ISearch) c).setText(text);
				return (ISearch) c;
			}
		}
		return null;
	}

	
	@Override
	@SuppressWarnings("rawtypes")
	public ITextEditorComponent getInstanceOf(Class clazz) {
		
		String className = clazz.getName();
		
		for (ITextEditorComponent c : this.listOfComponents) {
			if (c.getClass().getInterfaces()[0].getName().equals(className)) {
				return (ITextEditorComponent) c;
			}
		}
		return null;
		
		
	}
	
	
	
}
