package domain;

import java.util.ArrayList;
import java.util.List;

import presentation.ProjectPortfolioManager;

public class Resource {
	
	private String resourceId;
	private List<Task> taskList;
	
	
	public Resource(String resourceId) {
		this.setResourceId(resourceId);
		setTaskList(new ArrayList<Task>());
		addToPortfolio();
	}
	
	public Resource(String resourceId, Task task) {
		this.setResourceId(resourceId);
		setTaskList(new ArrayList<Task>());
		this.addTask(task);
		addToPortfolio();
	}

	public boolean isThereInPortfolio() {
		
		boolean isThere = false;
		
		for (Resource r : ProjectPortfolioManager.getListOfResources())
			if(r.resourceId.equals(this.resourceId))
				isThere = true;
		return isThere;
		
	}
	
	private void addToPortfolio() {
		
		if(!isThereInPortfolio())
			ProjectPortfolioManager.getListOfResources().add(this);
		else
			System.out.println(this.toString() + " already exists.");
			
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		if (resourceId.length() > 0)
			this.resourceId = resourceId;
		else
			System.out.println("Resource ID must be bigger than 0 !");
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public void addTask(Task task) {
		
		if(isThereInPortfolio()) {
			for (Resource r : ProjectPortfolioManager.getListOfResources()) {
				if(r.getResourceId().equals(this.resourceId))
					r.getTaskList().add(task);
			}
		}
		
		else if(!isThereInPortfolio())
			this.getTaskList().add(task);
			
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", taskList=" + taskList + "]";
	}

}
