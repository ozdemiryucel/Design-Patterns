package domain;

public class LineCounter implements ILineCounter {

	@Override
	public String operation(String str) {
		if (str == null)
			return null;
		String[] separated = str.split("\n");
		return String.valueOf(separated.length);
	}

}
