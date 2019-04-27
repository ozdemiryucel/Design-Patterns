package domain;

public class Search implements ISearch {
	
	private String allText;
	
	public Search(String allText) {
		this.allText = allText;
	}

	@Override
	public String operation(String str) {
		
		if (allText == null)
			return null;
		
		String[] listOfWords = this.allText.split(" ");
		
		StringBuilder builder = new StringBuilder();
		
		for (String s : listOfWords)
			if(s.contains(str))
				builder.append(s + "\n");
		
		return builder.toString();
	}

	@Override
	public void setText(String text) {
		this.allText = text;
		
	}

}
