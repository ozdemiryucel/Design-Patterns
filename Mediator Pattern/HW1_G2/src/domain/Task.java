package domain;

import java.util.*;

import dataAccess.Keyboard;

public class Task {

	private int taskNumber;
	private String taskDescription;
	private Calendar taskStartCalendar;
	private int taskHours;
	private String taskResourceId;
	
	public Task(int taskNumber, String taskDescription, Calendar taskStartCalendar, int taskHours) {
		this.setTaskNumber(taskNumber);
		this.setTaskDescription(taskDescription);
		this.setTaskStartCalendar(taskStartCalendar);
		this.setTaskHours(taskHours);
		
		this.init();
	}
	
	public void init() {
		this.setTaskResourceId();
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Calendar getTaskStartCalendar() {
		return taskStartCalendar;
	}

	public void setTaskStartCalendar(Calendar taskStartCalendar) {
		this.taskStartCalendar = taskStartCalendar;
	}

	public int getTaskHours() {
		return taskHours;
	}

	public void setTaskHours(int taskHours) {
		this.taskHours = taskHours;
	}

	public String getTaskResourceId() {
		return taskResourceId;
	}
	
	public void updateTaskResourceId(String resourceId) {
		this.taskResourceId = resourceId;
	}

	public void setTaskResourceId() {
		
		System.out.println("Enter resource type: ");
		String personType = Keyboard.get();
		
		
		System.out.println("Enter resource ID: ");
		String resourceId = Keyboard.get();
		
		Resource resource = null;
		if ( resourceId.length() > 0 ) {
					
			if (personType.substring(0, 1).toUpperCase().equals("E")) {
				this.taskResourceId = "E" + resourceId;
				resource = new Employee("E" + resourceId);
			}
			else if (personType.substring(0, 1).toUpperCase().equals("C")) {
				this.taskResourceId = "C" + resourceId;
				resource = new Consultant("C" + resourceId);
			}
			else
				System.out.println("Enter a valid person type");		
		}
		
		else
			System.out.println("Resource ID must be greater than 0 ! ");
		
		
		resource.addTask(this);
	}
	

	@Override
	public String toString() {
		return "Task [taskNumber=" + taskNumber + ", taskDescription=" + taskDescription + ", taskStartCalendar="
				+ taskStartCalendar.get(Calendar.DAY_OF_MONTH) + "/" + taskStartCalendar.get(Calendar.MONTH) + "/" + taskStartCalendar.get(Calendar.YEAR)
				+ ", taskHours=" + taskHours + ", taskResourceId=" + taskResourceId + "]";
	}

	
}
