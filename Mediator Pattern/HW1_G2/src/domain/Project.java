package domain;

import java.util.*;

import dataAccess.Keyboard;

public class Project {
	
	private String projectName;
	private String projectDescription;
	private Calendar projectStartCalendar;
	private int projectHours;
	
	private List<Activity> activityList;
	
	public Project(String projectName, String projectDescription, Calendar projectStartCalendar) {
		
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectStartCalendar = projectStartCalendar;
		
		this.activityList = new ArrayList<Activity>();
		
		this.init();
		
	}
	
	public void init() {
		this.addActivity();
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Calendar getProjectStartCalendar() {
		return projectStartCalendar;
	}

	public void setProjectStartCalendar(Calendar projectStartCalendar) {
		this.projectStartCalendar = projectStartCalendar;
	}
	
	public int getProjectHours() {
		int sumOfHours = 0;
		
		for (Activity a : this.getActivityList())
			sumOfHours += a.getActivityHours();
		
		return sumOfHours;
	}
	
	public Activity addActivity() {
		
		int activityNumber = 0;
		
		while(true) {
			
			boolean flag = true;
			System.out.println("Enter activity number: ");
			activityNumber = Integer.valueOf(Keyboard.get());
			
			for (Activity a : this.getActivityList()) {
				if (a.getActivityNumber() == activityNumber) {
					System.out.println("This activity is already existed");
					flag = false;
				}
			}
			
			if(activityNumber == 0)
				continue;
			else if(flag)
				break;
		}
		
		
		System.out.println("Enter activity description: ");
		String activityDescription = Keyboard.get();
		
		System.out.println("Enter start date of activity (DD/MM/YYYY): ");
		String[] splittedCalendar = Keyboard.get().split("/");									
		Calendar activityStartCalendar = Calendar.getInstance();
		activityStartCalendar.set(Integer.valueOf(splittedCalendar[2]), Integer.valueOf(splittedCalendar[1]), Integer.valueOf(splittedCalendar[0]));

		
		System.out.println("Enter activity deliverable: ");
		String activityDeliverable = Keyboard.get();
		
		
		Activity activity = new Activity(
				activityNumber,
				activityDescription,
				activityStartCalendar,
				activityDeliverable);
		
		
		this.getActivityList().add(activity);
		this.projectHours = this.getProjectHours();
		
		return activity;
		
	}
	
	public Activity removeActivity() {
		
		System.out.println("----------ACTIVITIES OF PROJECT----------");
		for (Activity a : getActivityList()) {
			System.out.println("- Activity Number: " + a.getActivityNumber());
		}

		System.out.println("Enter activity number to delete the activity. ");
		int activityNumber = Integer.valueOf(Keyboard.get());

		Iterator<Activity> iter = getActivityList().iterator();
		Activity activity = null;
		while (iter.hasNext()) {
			activity = iter.next();
			if (activity.getActivityNumber() == activityNumber) {
				iter.remove();
			}
		}	
		
		this.projectHours = this.getProjectHours();
		
		return activity;
	}
	
	
	public void updateActivity() {

		System.out.println("----------ACTIVITIES OF PROJECT---------- ");
		for (Activity a : getActivityList())
			System.out.println("- Activity Number: " + a.getActivityNumber());

		System.out.println("Enter activity number that you want to update.");
		int activityInput = Integer.valueOf(Keyboard.get());

		for (Activity a : getActivityList())
			if (a.getActivityNumber() == activityInput) {
				
				System.out.println("Enter activity number: ");
				int activityNumber = Integer.valueOf(Keyboard.get());

				System.out.println("Enter activity description: ");
				String activityDescription = Keyboard.get();

				System.out.println("Enter start date of activity (DD/MM/YYYY): ");
				String[] splittedCalendar = Keyboard.get().split("/");
				Calendar activityStartCalendar = Calendar.getInstance();
				activityStartCalendar.set(Integer.valueOf(splittedCalendar[2]), Integer.valueOf(splittedCalendar[1]),
						Integer.valueOf(splittedCalendar[0]));

				System.out.println("Enter activity deliverable: ");
				String activityDeliverable = Keyboard.get();

				a.setActivityNumber(activityNumber);
				a.setActivityDescription(activityDescription);
				a.setActivityStartCalendar(activityStartCalendar);
				a.setActivityDeliverable(activityDeliverable);

				System.out.println("Activity is updated.");
			}

	}
	
	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", projectDescription=" + projectDescription
				+ ", projectStartCalendar=" 
				+ projectStartCalendar.get(Calendar.DAY_OF_MONTH) + "/" + projectStartCalendar.get(Calendar.MONTH) + "/" + projectStartCalendar.get(Calendar.YEAR) 
				+ ", projectHours= " + this.getProjectHours() +
				", activityList=" + activityList + "]";
	}
	
}
