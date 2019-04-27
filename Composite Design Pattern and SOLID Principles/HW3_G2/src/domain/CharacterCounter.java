package domain;

public class CharacterCounter implements ICharacterCounter {

	@Override
	public String operation(String str) {
		if (str == null)
			return null;
		str = str.replace(" ", "");
//		System.out.println(str);
		str = str.replace("\n", "");
//		System.out.println(str);
		return String.valueOf(str.length());
		
	}

}
