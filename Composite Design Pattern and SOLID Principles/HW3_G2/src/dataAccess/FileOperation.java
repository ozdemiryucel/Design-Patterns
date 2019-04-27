package dataAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOperation implements IFileOperation {

	@Override
	public void writeToFile(String str, String fileName) {
		
		try {
			PrintWriter printWriter = new PrintWriter(fileName);
			printWriter.println(str);
			printWriter.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		

	}

	@Override
	public String readFromFile(String fileName) {
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.err.println("There is no file text.txt");
//			e.printStackTrace();
			return null;
		}
		
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            try {
					line = br.readLine();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	        }
	        return sb.toString();
	    } finally {
	        try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}

}
