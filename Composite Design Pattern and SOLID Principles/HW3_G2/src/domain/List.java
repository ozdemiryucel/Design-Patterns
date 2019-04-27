package domain;

import presentation.Console;

public class List implements IList {

	@Override
	public String operation(String str) {
		
		if (str == null)
			return null;

		Console.print(str);
		return str;
	}

}
