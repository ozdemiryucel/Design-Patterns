package domain;

public class ParagraphCounter implements IParagraphCounter {

	@Override
	public String operation(String str) {
		if (str == null)
			return null;
		String[] list = str.split("\r\n");
		return String.valueOf(list.length);
	}

}
