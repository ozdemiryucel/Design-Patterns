package presentation;

import java.util.*;
import dataAccess.FileOperations;
import dataAccess.Keyboard;
import domain.*;

public class ProjectPortfolioManager implements IProjectPortfolioManager {
	
	private List<Project> listOfProjects;
	private static List<Resource> listOfResources;
	
	public ProjectPortfolioManager() {
		
		try {
			listOfProjects = FileOperations.readFile();
		}
		catch (NoSuchElementException e) {
			listOfProjects = new ArrayList<Project>();
		}
		
		listOfResources = new ArrayList<Resource>();
		
		for (Project p : this.listOfProjects)
			for(Activity a : p.getActivityList())
				for(Task t : a.getTaskList()) {
					if(t.getTaskResourceId().substring(0, 1).equals("E"))
						new Employee(t.getTaskResourceId(), t);
					else
						new Consultant(t.getTaskResourceId(), t);
				}
		
	}

	public void start() {
		
		System.out.println("\nLast records:");
		System.out.println(calculateHours());
		
		while(true) {

			System.out.println(getMainMenu());
			System.out.println("Please select an operation (Enter Q to Save and Exit.)");
					
			String entry = Keyboard.get();
			
			Project project = null;
			
			try {
				if(entry.toLowerCase().equals("q")) {
					Keyboard.close();
					FileOperations.writeFile(listOfProjects);
					System.out.println("Changes are saved.");
					System.exit(0);
				}
				else if (Integer.valueOf(entry) == 1) {
					addProject();
				}
				else if (Integer.valueOf(entry) == 2) {
					System.out.println("Project "+removeProject(project).getProjectName() + " is removed.");
				}
				else if (Integer.valueOf(entry) == 3) {
					updateProject();
				}
				else if (Integer.valueOf(entry) == 4)			
					System.out.println(calculateHours());
				else if (Integer.valueOf(entry) == 5)					
					System.out.println(showResourcesAll());
				else if (Integer.valueOf(entry) == 6)					
					System.out.println(function());
				else
					System.out.println("Please enter a number which is between 1-5! (in PMS class)");
			}
			catch(NumberFormatException e) {
				System.out.println("Please enter a valid entry! (in PMS class)");
				continue;
			}
		}	
	}

	@Override
	public Project addProject() {
		
		String projectName = "", projectDescription = "";
		
		while(true) {
			
			boolean flag = true;
			System.out.println("Enter the name of project: ");
			
			projectName = Keyboard.get();
			for (Project p : this.listOfProjects) {
				if (p.getProjectName().equals(projectName)) {
					System.out.println("This project already exists.");
					flag = false;
				}
			}
			
			if(projectName.trim().equals(""))
				continue;
			else if(flag)
				break;
		}
		
		
		System.out.println("Enter the description of project: ");
		
		projectDescription = Keyboard.get();
		
		System.out.println("Enter the start date of project (DD/MM/YYYY): ");
		String[] splittedCalendar = Keyboard.get().split("/");									
		Calendar projectStartCalendar = Calendar.getInstance();
		projectStartCalendar.set(Integer.valueOf(splittedCalendar[2]), Integer.valueOf(splittedCalendar[1]), Integer.valueOf(splittedCalendar[0]));
		
		Project project = new Project(projectName, projectDescription, projectStartCalendar);
		
		this.listOfProjects.add(project);
		
		return project;
		
	}

	@Override
	public Project removeProject(Project project) {
		
		System.out.println("----------ALL PROJECTS----------");
		for(Project p: this.listOfProjects)
			System.out.println("- Project Name: "+p.getProjectName());
		
		System.out.println("Enter the name of project which you want to remove: ");
		String willBeRemovedProjectName = Keyboard.get();
		Project removedProject = null;
		for (Project p : this.listOfProjects)
			if (p.getProjectName().equals(willBeRemovedProjectName)) {
				removedProject = p;
				this.listOfProjects.remove(p);
				break;
			}
		
		if (removedProject == null)
			System.out.println("There is no such project !");
		return removedProject;
	}


	@Override
	public String calculateHours() {
			
		StringBuilder builder = new StringBuilder();
		
		for (Project p : listOfProjects) {
			builder.append("Project Name: " + p.getProjectName() + " - " + p.getProjectHours() + " hours\n");
			for (Activity a : p.getActivityList()) {
				builder.append("    Activity Number: " + a.getActivityNumber() + " - " + a.getActivityHours() + " hours\n");
				for (Task t : a.getTaskList()) {
					builder.append("        Task Number: " + t.getTaskNumber() + " - " + t.getTaskHours() + " hours\n");
				}
			}
		}
			
		return builder.toString();
		
	}

	
	public String function() {
		
		StringBuilder builder = new StringBuilder();
		
		for (Resource r : ProjectPortfolioManager.getListOfResources()) {
			builder.append(r.toString() + "\n");
		}
		
		return builder.toString();
		
	}

	
	public static List<Resource> getListOfResources() {
		return listOfResources;
	}
	

	@Override
    public String showResourcesAll() {
		
		List<String> arr = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		
        for(Project p: listOfProjects) {
        	  	
        	int projectCounterForEmployee = 0;
        	int projectCounterForConsultant = 0;
        	
            for(Activity a: p.getActivityList()) {
            	
            	int activityCounterForEmployee = 0;
            	int activityCounterForConsultant = 0;
            	
                for(Task t: a.getTaskList()) {
                    
                	arr.add("\n    Task Number: " + t.getTaskNumber() + " - (Resource is " + t.getTaskResourceId().substring(0, 1) + ")");
                	
                	if(t.getTaskResourceId().substring(0, 1).equals("E")) {
                		activityCounterForEmployee += 1;
                		projectCounterForEmployee += 1;
                	}
                	else {
                		activityCounterForConsultant += 1;
                		projectCounterForConsultant += 1;
                	}
                	
                }
                arr.add("\n  Activity Number: " + a.getActivityNumber() + " (" + activityCounterForEmployee + " Employees - " + activityCounterForConsultant + " Consultants)");
            }
            arr.add("\nProject Name: " + p.getProjectName() + " (" + projectCounterForEmployee + " Employees - " + projectCounterForConsultant + " Consultants)"); 
        }
        
        
        Collections.reverse(arr);
        for (String str : arr)
        	builder.append(str);

        return builder.toString();
    }
	
	
	@Override
	public Project updateProject() {

		System.out.println("----------ALL PROJECTS----------");
		if(listOfProjects.isEmpty()) {
			System.out.println("No projects are available.");	
			return null;
		}
		
		for (Project p : listOfProjects) {
			System.out.println("- Project Name: " + p.getProjectName());
		}

		System.out.println("Enter the project's name that you want to update: ");
		String projectName = Keyboard.get();
		
		boolean isThere = false;
		
		for(Project p : this.getListOfProjects())
			if (p.getProjectName().equals(projectName))
				isThere = true;
		
		if(!isThere) {
			System.out.println("There is no such project.");
			return null;
		}

		System.out.println(getUpdateProjectMenu());
		System.out.println("** Please select between A-E to perform an operation **");

		String operationSelected = Keyboard.get().toUpperCase();
		
		
		System.out.println(getOperationsMenu(operationSelected));
		String operationSpecified = "";
		
		if(operationSelected.equals("A") || operationSelected.equals("B") || operationSelected.equals("C"))
			operationSpecified = Keyboard.get().toLowerCase();

		for (Project p : listOfProjects) {
			if (p.getProjectName().equals(projectName)) {
				if (operationSelected.equals("A")) {
					updateProjectByActivity(operationSpecified, p);
					break;
				}
				else if (operationSelected.equals("B")) {
					updateProjectByTask(operationSpecified, p);
					break;
				}
				else if (operationSelected.equals("C")){
					updatingResourceOperations(operationSpecified);
					break;
				}		
				else if(operationSelected.equals("D")) {
					assigningResourceToTask(p);
					break;
				}
				else if(operationSelected.equals("E")) {
					unassigningResourceFromTask(p);
					break;
				}
			}
		}

		return null;

	}


	private void assigningResourceToTask(Project project) {
		
		System.out.println("--------ALL TASKS OF " + project.getProjectName() + "--------");
		for(Project p: this.listOfProjects) {
			if(p.getProjectName().equals(project.getProjectName())) {
	            for(Activity a: p.getActivityList()) 
	                for(Task t: a.getTaskList()) 
	                	System.out.println("- Task Number: "+t.getTaskNumber());
	            break;
			}
		}
                
		
		System.out.println("Select a task to change its resource:");
		int taskNumber = Integer.valueOf(Keyboard.get());
		
		System.out.println("--------ALL RESOURCES--------");
		for (Resource r : ProjectPortfolioManager.getListOfResources())
			System.out.println(r.getResourceId());
		
		System.out.println("Select a resource to assign to selected task:");
		String resourceId = Keyboard.get();
		
		
		label: { 	
            for(Activity a: project.getActivityList()) 
                for(Task t: a.getTaskList())
                	if(t.getTaskNumber() == taskNumber) {
                		t.updateTaskResourceId(resourceId);
                		
                		for(Resource r : ProjectPortfolioManager.getListOfResources())
                			if(r.getTaskList().contains(t)) {
                				r.getTaskList().remove(t);
                				break;
                			}
                		
                		for(Resource r : ProjectPortfolioManager.getListOfResources())
                			if(r.getResourceId().equals(resourceId)) {
                				r.addTask(t);
                				break label;
                			}
                	}
		}		
	}
	
	
	private void unassigningResourceFromTask(Project project) {
		
		System.out.println("--------ALL TASKS OF " + project.getProjectName() + "--------");
		for(Project p: this.listOfProjects) {
			if(p.getProjectName().equals(project.getProjectName())) {
	            for(Activity a: p.getActivityList()) 
	                for(Task t: a.getTaskList()) 
	                	System.out.println("- Task Number: "+t.getTaskNumber());
	            break;
			}
		}
                
		
		System.out.println("Select a task to unassign its resource:");
		int taskNumber = Integer.valueOf(Keyboard.get());
		
			
		label: { 	
            for(Activity a: project.getActivityList()) 
                for(Task t: a.getTaskList())
                	if(t.getTaskNumber() == taskNumber) {
                		t.updateTaskResourceId(null);
                		
                		for(Resource r : ProjectPortfolioManager.getListOfResources())
                			if(r.getTaskList().contains(t)) {
                				r.getTaskList().remove(t);
                				break;
                			}
                		
                		a.getTaskList().remove(t);
                		if(a.getTaskList().isEmpty())
                			project.getActivityList().remove(a);
                			if(project.getActivityList().isEmpty())
                				this.getListOfProjects().remove(project);
                		
                		break label;
                	}
		}	
		
	}

	public void updatingResourceOperations(String operationSpecified) {
		switch (operationSpecified) {
		case "a":
			System.out.println("Enter a resource ID:");
			ProjectPortfolioManager.getListOfResources().add(new Resource(Keyboard.get()));
			break;
		case "b":
			System.out.println("Resource with ID" + this.removeResource().getResourceId() + " is removed.");
			break;
		case "c":
			System.out.println("--------ALL RESOURCES--------");
			for(Resource r : ProjectPortfolioManager.listOfResources) {
				System.out.println("- Resource ID: "+r.getResourceId());
			}
			System.out.println("Enter resource ID:");
			String oldResourceId = Keyboard.get();
			
			for(Resource r : ProjectPortfolioManager.listOfResources) {
				if(r.getResourceId().equals(oldResourceId)) {
					System.out.println("Enter new resource ID:");
					String newResourceId = Keyboard.get();
					
					try {
						if((Employee) r instanceof Employee) {
							System.out.println("in Employee");
							r.setResourceId("E" + newResourceId);
							label1: {
								for (Project p : this.getListOfProjects())
									for (Activity a : p.getActivityList())
										for (Task t : a.getTaskList())
											if(t.getTaskResourceId().equals(oldResourceId)) {
												t.updateTaskResourceId("E" + newResourceId);
												break label1;
											}
							}
						}
					}
					catch(Exception e) {
						if((Consultant) r instanceof Consultant) {
							r.setResourceId("C" + newResourceId);
							label2: {
								for (Project p : this.getListOfProjects())
									for (Activity a : p.getActivityList())
										for (Task t : a.getTaskList())
											if(t.getTaskResourceId().equals(oldResourceId)) {
												t.updateTaskResourceId("C" + newResourceId);
												break label2;
											}
							}
						}
					}
					break;
				}
			}
			
			break;
		}
	}

	public Resource removeResource() {
		
		System.out.println("All resources:");
		for (Resource r : ProjectPortfolioManager.getListOfResources())
			System.out.println("Resource ID: " + r.getResourceId());

		System.out.println("Enter the Resource ID that you want to remove: ");
		String resourceId = Keyboard.get();

		Resource resource = null;
		for (Resource r : ProjectPortfolioManager.getListOfResources()) {
			if (r.getResourceId().equals(resourceId)) {
				resource = r;
				ProjectPortfolioManager.getListOfResources().remove(r);			
				break;				
			}
		}
		
		label: {
			for (Project p : this.getListOfProjects()) {
				for (Activity a : p.getActivityList()) {
					for (Task t : a.getTaskList()) {
						if (t.getTaskResourceId().equals(resource.getResourceId())){
							
							a.getTaskList().remove(t);
							
							if(a.getTaskList().size() == 0) {
								p.getActivityList().remove(a);
								
								if( p.getActivityList().size() == 0)
									this.getListOfProjects().remove(p);
							}
							
							break label;
							
						}
					}
				}				
			}
		}

		return resource;
	}

	public void updateProjectByTask(String operationSpecified, Project project) {
		
		System.out.println("-------ACTIVITIES-------");
		for (Activity a : project.getActivityList())
			System.out.println("- Activity Number: "+a.getActivityNumber());
		
		System.out.println("Enter the activity number:");
		
		int activityNumber = Integer.valueOf(Keyboard.get());
		
		switch (operationSpecified) {
			case "a":
				for (Activity a : project.getActivityList())
					if(a.getActivityNumber() == activityNumber)
						System.out.println("Task with Task Number: " + a.addTask().getTaskNumber() + " is added to the list.");
				break;
			case "b":
				for (Activity a : project.getActivityList())
					if(a.getActivityNumber() == activityNumber)
						System.out.println(
								"Task with Task Number: " + a.removeTask().getTaskNumber() + " is removed from the list.");
				break;
			case "c":
				for (Activity a : project.getActivityList())
					if(a.getActivityNumber() == activityNumber)
						a.updateTask();
				break;
		}
	}

	public void updateProjectByActivity(String operationSpecified, Project project) {
		switch (operationSpecified) {
			case "a":
				System.out.println("Activity with Activity Number: " + project.addActivity().getActivityNumber()
						+ " is added.");
				break;
			case "b":
				System.out.println("Activity with Activity Number: " + project.removeActivity().getActivityNumber()
						+ " is removed.");
				if(project.getActivityList().size() == 0) {
					for (Project p : this.listOfProjects) {
						if(p.getProjectName().equals(project.getProjectName())) {
							this.getListOfProjects().remove(p);
							break;
						}
					}
				}
				break;
			case "c":
				project.updateActivity();
				break;
		}
	}

	public String getUpdateProjectMenu() {

		StringBuilder builder = new StringBuilder();
		builder.append("----------------------UPDATE PROJECT----------------------\n");
		builder.append("A) Update project by adding,removing or updating an activity.\n");
		builder.append("B) Update project by adding,removing or updating a task.\n");
		builder.append("C) Update project by adding,removing or updating a resource.\n");
		builder.append("D) Update project by assigning a task to a resource.\n");
		builder.append("E) Update project by unassigning a task from a resource.\n");
		builder.append("-----------------------------------------------------------");

		return builder.toString();
	}

	public String getOperationsMenu(String operationSelected) {
		
		if(operationSelected.equals("D"))
			return "";

		StringBuilder builder = new StringBuilder();
		builder.append("** Please specify the operation **\n");
		switch (operationSelected) {
		case "A":
			builder.append("a) Update project by adding an activity.\n");
			builder.append("b) Update project by removing an activity.\n");
			builder.append("c) Update project by updating an activity.");
			break;
		case "B":
			builder.append("a) Update project by adding a task.\n");
			builder.append("b) Update project by removing a task.\n");
			builder.append("c) Update project by updating a task.");
			break;
		case "C":
			builder.append("a) Update project by adding a resource.\n");
			builder.append("b) Update project by removing a resource.\n");
			builder.append("c) Update project by updating a resource.");
			break;
		case "E":
			break;
		}

		return builder.toString();

	}
	
	
	@Override
	public String getMainMenu() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("\n--------------------------PROJECT PORTFOLIO MANAGEMENT SYSTEM--------------------------\n");
		builder.append("1) Add a project\n");
		builder.append("2) Find and remove a project\n");
		builder.append("3) Find and update a project\n");
		builder.append("4) Calculate project, activity, and task duration by hours\n");
		builder.append("5) Find number of distinct employees and consultants assigned to a project, activity, and task\n");
		builder.append("-----------------------------------------------------------------------------------------");

		return builder.toString();
	}

	public List<Project> getListOfProjects() {
		return listOfProjects;
	}

}
