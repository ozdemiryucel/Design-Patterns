package dataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import domain.Project;

public class FileOperations {
	
	private static String fileDirectory = System.getProperty("user.dir");
	
	public static List<Project> readFile() {
		
		File dir = new File(fileDirectory);
		File[] foundFiles = dir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.startsWith("Projects-");
		    }
		});
		
		System.out.println("Files:");
		for (File file : foundFiles) {
		    System.out.println(file.getName());
		}
		//System.out.println("\n");
		
		List<Calendar> calendars = new ArrayList<Calendar>();
		
		
		for (File file : foundFiles) {
		    String[] splittedCalendar = file.getName().replace("Projects-", "").replace(".txt", "").split("-");
		    Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.valueOf(splittedCalendar[0]), Integer.valueOf(splittedCalendar[1]), Integer.valueOf(splittedCalendar[2]));
			calendars.add(calendar);
		    
		}
		
		Calendar maxCalendar = Collections.max(calendars);
		
		String fileName = "Projects-" + maxCalendar.get(Calendar.YEAR) + "-" + maxCalendar.get(Calendar.MONTH) + "-" + maxCalendar.get(Calendar.DAY_OF_MONTH) + ".txt";
		
		System.out.println("\nThe last file:");
		System.out.println(fileName);
		
		File file = new File(fileDirectory + "\\" + fileName); 
		  
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
	  
		String contentOfFile = ""; 
		try {
			contentOfFile = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(contentOfFile);
		
		//List<Project> arr = 
		
		//System.out.println("selam");
		JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(contentOfFile);
        
        Gson gson = new Gson();
        
        List<Project> listOfProjects = new ArrayList<Project>();
        
        for (JsonElement o : element.getAsJsonArray()) {
        	
        	//System.out.println(o.toString());
        	
        	Project project = gson.fromJson(o, Project.class);
        	listOfProjects.add(project);
        	//System.out.println(project.getActivityList());     	
        }
		
		
		return listOfProjects;
		
	}
	
	public static void writeFile(List<Project> listOfProjects) {
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();

			
		String fileName = "Projects-" + dtf.format(now) + ".txt";
		String filePath = fileDirectory + "//" + fileName;
		
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) { 
		    f.delete();
		}
				
		
		writeObjectToFile(listOfProjects, filePath);
		
//		for(Project p : listOfProjects)
//			writeObjectToFile(p, filePath);
//		System.out.println("Saved on " + filepath);
			
		
	}
	
	private static void writeObjectToFile(List<Project> serObj, String filePath) {
		
		try (Writer writer = new FileWriter(filePath)) {
		    Gson gson = new GsonBuilder().create();
		    gson.toJson(serObj, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
