package domain;

import java.util.*;

import dataAccess.Keyboard;

public class Activity {
	
	private int activityNumber;
	private String activityDescription;
	private Calendar activityStartCalendar;
	private String activityDeliverable;
	private int activityHours;
	
	
	private List<Task> taskList;
	
	public Activity(int activityNumber, String activityDescription, Calendar activityStartCalendar, String activityDeliverable){
		
		this.activityNumber = activityNumber;
		this.activityDescription = activityDescription;
		this.activityStartCalendar = activityStartCalendar;
		this.activityDeliverable = activityDeliverable;
		
		
		taskList = new ArrayList<Task>();
		
		this.init();
			
	}
	
	public void init() {
		this.addTask();
	}
	
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public int getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(int activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public Calendar getActivityStartCalendar() {
		return activityStartCalendar;
	}

	public void setActivityStartCalendar(Calendar activityStartCalendar) {
		this.activityStartCalendar = activityStartCalendar;
	}

	public String getActivityDeliverable() {
		return activityDeliverable;
	}

	public void setActivityDeliverable(String activityDeliverable) {
		this.activityDeliverable = activityDeliverable;
	}

	public int getActivityHours() {
		
		int sumOfHours = 0;
		
		for (Task t : this.getTaskList())
			sumOfHours += t.getTaskHours();
		
		this.activityHours = sumOfHours;
		
		return sumOfHours;
	}


	public Task addTask() {
		
		int taskNumber = 0;
		
		while(true) {
			
			boolean flag = true;
			System.out.println("Enter task number: ");
			taskNumber = Integer.valueOf(Keyboard.get());
			
			for (Task t : this.getTaskList()) {
				if (t.getTaskNumber() == taskNumber) {
					System.out.println("This task already exists.");
					flag = false;
				}
			}
			
			if(taskNumber == 0)
				continue;
			else if(flag)
				break;
		}
		
		
		System.out.println("Enter task description: ");
		String taskDescription = Keyboard.get();
		
		
		System.out.println("Enter start date of task (DD/MM/YYYY): ");
		String[] splittedCalendar = Keyboard.get().split("/");									
		Calendar taskStartCalendar = Calendar.getInstance();
		taskStartCalendar.set(Integer.valueOf(splittedCalendar[2]), Integer.valueOf(splittedCalendar[1]), Integer.valueOf(splittedCalendar[0]));

		
		System.out.println("Enter task hours: ");
		int taskHours = Integer.valueOf(Keyboard.get());
		
		Task task = new Task(taskNumber, taskDescription, taskStartCalendar, taskHours);

		this.getTaskList().add(task);
		
		this.activityHours = this.getActivityHours();
		
		return task;
		
	}
	
	public Task removeTask() {
		
		System.out.println("----------TASKS OF PROJECT---------- ");
		for (Task t : getTaskList())
			System.out.println("- Task Number: " + t.getTaskNumber());

		System.out.println("Please select the Task Number that you want to remove. ");
		int taskNumber = Integer.valueOf(Keyboard.get());

		Iterator<Task> iter = getTaskList().iterator();
		Task task = null;
		while (iter.hasNext()) {
			task = iter.next();
			if (task.getTaskNumber() == taskNumber) {
				iter.remove();
			}
		}
		
		this.activityHours = this.getActivityHours();
		
		return task;
	}
	
	
	public void updateTask() {

		System.out.println("----------TASKS OF PROJECT---------- ");
		for (Task t : getTaskList())
			System.out.println("- Task Number: " + t.getTaskNumber());

		System.out.println("Enter task number that you want to update.");
		int taskInput = Integer.valueOf(Keyboard.get());

		for (Task t : getTaskList())
			if (t.getTaskNumber() == taskInput) {

				System.out.println("Enter task number: ");
				int taskNumber = Integer.valueOf(Keyboard.get());

				System.out.println("Enter task description: ");
				String taskDescription = Keyboard.get();

				System.out.println("Enter start date of task (DD/MM/YYYY): ");
				String[] splittedCalendar = Keyboard.get().split("/");
				Calendar taskStartCalendar = Calendar.getInstance();
				taskStartCalendar.set(Integer.valueOf(splittedCalendar[2]), Integer.valueOf(splittedCalendar[1]),
						Integer.valueOf(splittedCalendar[0]));

				System.out.println("Enter task hours: ");
				int taskHours = Integer.valueOf(Keyboard.get());

				t.setTaskNumber(taskNumber);
				t.setTaskDescription(taskDescription);
				t.setTaskHours(taskHours);
				t.setTaskStartCalendar(taskStartCalendar);

				System.out.println("Task is updated.");

			}
	}

	@Override
	public String toString() {
		return "Activity [activityNumber=" + activityNumber + ", activityDescription=" + activityDescription
				+ ", activityStartCalendar=" 
				+ activityStartCalendar.get(Calendar.DAY_OF_MONTH) + "/" + activityStartCalendar.get(Calendar.MONTH) + "/" + activityStartCalendar.get(Calendar.YEAR) 
				+ ", activityDeliverable=" + activityDeliverable
				+ ", activityHours=" + this.getActivityHours() + ", taskList=" + taskList + "]";
	}

}
