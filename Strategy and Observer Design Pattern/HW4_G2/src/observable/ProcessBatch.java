package observable;

import java.util.ArrayList;
import java.util.List;

import domain.*;
import domain.Process;
import dataAccess.Console;
import observer.*;
import strategy.IStrategy;


public class ProcessBatch implements ObservableProcessBatch {
	
	private List<Observer> observers;
	private List<Process> processes;
	
	public ProcessBatch() {
		observers = new ArrayList<Observer>();
		processes = new ArrayList<Process>();
//		this.addObserver(new RunningStateObserver());
		this.addObserver(new NewStateObserver());
		this.addObserver(new ReadyStateObserver());
//		this.addObserver(new TerminatedStateObserver());
		this.addObserver(new StarvedStateObserver());
//		this.addObserver(new BlockedStateObserver());
		
	}
	
	public List<Observer> getObservers() {
		return observers;
	}

	@Override
	public void scheduleProcesses(IStrategy strategy) {
		strategy.run(processes);
		determineWaitingTimes();
		Console.print("");
		Dispatcher.instance().extractScheduledProcess(processes);
		
	}
	
	private void determineWaitingTimes() {
		int waitingTime = 0;
		
		for (Process p : this.processes) {
			p.setWaitingTime(waitingTime);
			
			if (p.getWaitingTime() > 50)
				notifyObserver(getStarvedStateObserver(), p);
			
			else if (p.getProcessState() != State.STARVED)
				notifyObserver(getReadyStateObserver(), p);
					
			waitingTime += p.getBurstTime();
			
		}	
	}

//	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
		
	}

//	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
		
	}

	
	private Observer getReadyStateObserver() {
		for (Observer o : this.observers)
			if (o instanceof ReadyStateObserver)
				return o;
		return null;
		
	}
	
	private Observer getStarvedStateObserver() {
		for (Observer o : this.observers)
			if (o instanceof StarvedStateObserver)
				return o;
		return null;
	}
	
	private Observer getNewStateObserver() {
		for (Observer o : this.observers)
			if (o instanceof NewStateObserver)
				return o;
		return null;
	}
	
	

	@Override
	public void notifyObserver(Observer observer, Process process) {
		observer.update(process);
	}

	@Override
	public void addProcess(Process process) {
		this.processes.add(process);
		notifyObserver(getNewStateObserver(), process);
		
	}



}
