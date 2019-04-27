package domain;

import java.util.Comparator;
import java.util.Random;

public class Process {
	private Integer processId;
	private Integer arrivalTime;
	private Integer burstTime;
	private Integer completionTime;
	private Integer waitingTime;
	private Integer priority;
	private State processState;
	
	public Process(Integer processId){
		this.setArrivalTime();
		this.setBurstTime();
		this.setCompletionTime();
		this.setPriority();
		setProcessId(processId);
//		this.processState = processState;
	}

	public Integer getProcessId() {
		return processId;
	}

	private void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public Integer getArrivalTime() {
		return arrivalTime;
	}
	private void setArrivalTime() {
		Random generator = new Random(); 
		this.arrivalTime = generator.nextInt(20) + 1;
	}
	public Integer getBurstTime() {
		return burstTime;
	}
	private void setBurstTime() {
		Random generator = new Random(); 
		this.burstTime = generator.nextInt(20) + 1;
	}
	public Integer getCompletionTime() {
		return completionTime;
	}
	private void setCompletionTime() {
		this.completionTime = this.getArrivalTime() + this.burstTime;
	}
	
	public Integer getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(Integer waitingTime) {
		this.waitingTime = waitingTime;
	}
	public Integer getPriority() {
		return priority;
	}
	private void setPriority() {
		Random generator = new Random(); 
		this.priority = generator.nextInt(10) + 1;
	}
	public State getProcessState() {
		return processState;
	}
	public void setProcessState(State processState) {
		this.processState = processState;
	}
	
	public static Comparator<Process> getCompByPriority() {   
	 Comparator<Process> comp = new Comparator<Process>(){
	     @Override
	     public int compare(Process p1, Process p2) {
	         return p1.priority.compareTo(p2.priority);
	     }        
	 };
	 return comp;
	}  
	
	public static Comparator<Process> getCompByArrivalTime() {   
		 Comparator<Process> comp = new Comparator<Process>(){
		     @Override
		     public int compare(Process p1, Process p2) {
		         return p1.arrivalTime.compareTo(p2.arrivalTime);
		     }        
		 };
		 return comp;
	}
	
	public static Comparator<Process> getCompByBurstTime() {   
		 Comparator<Process> comp = new Comparator<Process>(){
		     @Override
		     public int compare(Process p1, Process p2) {
		         return p1.burstTime.compareTo(p2.burstTime);
		     }        
		 };
		 return comp;
	} 
	
	

	@Override
	public String toString() {
		return "Process [processId=" + processId + ", arrivalTime=" + arrivalTime + ", burstTime=" + burstTime
				+ ", completionTime=" + completionTime + ", waitingTime=" + waitingTime + ", priority=" + priority
				+ ", processState=" + processState + "]";
	}
	
	
	
	

}
