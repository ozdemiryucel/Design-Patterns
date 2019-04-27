package domain;

import dataAccess.FileOperation;
import dataAccess.IFileOperation;

public class TextReader implements ITextReader {
	
	private IFileOperation fileOperation;
	
	public TextReader() {
		fileOperation = new FileOperation();
	}

	@Override
	public String operation(String str) {
		
		return this.fileOperation.readFromFile(str);
		
	}

}
