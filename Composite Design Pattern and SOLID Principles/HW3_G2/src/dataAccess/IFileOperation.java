package dataAccess;

public interface IFileOperation {
	
	public void writeToFile(String str, String fileName);
	
	public String readFromFile(String fileName);

}
