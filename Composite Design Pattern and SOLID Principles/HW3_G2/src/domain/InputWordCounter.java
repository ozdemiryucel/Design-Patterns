package domain;

public class InputWordCounter implements IInputWordCounter {

	@Override
	public String operation(String str) {
		
		if (str == null)
			return null;
		
		String[] chosenWords = str.split("\n");
		
		return String.valueOf(chosenWords.length);
	}

}
