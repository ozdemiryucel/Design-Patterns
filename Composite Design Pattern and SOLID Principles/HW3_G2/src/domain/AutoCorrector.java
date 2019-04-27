package domain;

import autocorrection.AutoCorrect;

public class AutoCorrector implements IAutoCorrector {
	
	private AutoCorrect autoCorrect;
	
	public AutoCorrector() {
		autoCorrect = new AutoCorrect();
		autoCorrect.initialize();
	}

	@Override
	public String operation(String str) {
		
		if (str == null)
			return null;
		
		String[] listOfWords = str.trim().split("\\s+");
		
		for (int i = 0; i < listOfWords.length; i++)
			listOfWords[i] = autoCorrect.autoCorrect(listOfWords[i]);

		StringBuilder builder = new StringBuilder();
		
		// TODO no suggestions donduruyor ve update ediyor, napalim? ayni zamanda \n var...
		// ...buna bi ara bak
		for (String s : listOfWords) 
			builder.append(s + "\n");
		
		return builder.toString();
	}

}
