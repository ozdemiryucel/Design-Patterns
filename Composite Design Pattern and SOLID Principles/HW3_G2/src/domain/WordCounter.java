package domain;

public class WordCounter implements IWordCounter {

	@Override
	public String operation(String str) {
//		str = str.replace("\n", " ");
//		System.out.println("-------");
//		System.out.println(str);
		if (str == null)
			return null;
		String[] separated = str.trim().split("\\s+");
		return String.valueOf(separated.length);
	}

}
