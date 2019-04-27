package domain;

import dataAccess.FileOperation;
import dataAccess.IFileOperation;

public class TextSaver implements ITextSaver {
	
	private IFileOperation fileOperation;
	private String fileName;
	
	public TextSaver(String fileName) {
		this.fileName = fileName;
		fileOperation = new FileOperation();
	}

	@Override
	public String operation(String str) {
		this.fileOperation.writeToFile(str, this.fileName);
		return this.fileName + " is saved";
	}

}
