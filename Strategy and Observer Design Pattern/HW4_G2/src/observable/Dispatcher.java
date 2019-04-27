package observable;

import java.util.List;

import observer.*;
import domain.Process;
import domain.State;

public class Dispatcher implements Observable {
	
	private static Dispatcher dispatcher = null;
	
	private Observer runningObserver;
	private Observer blockedObserver;
	
	private Dispatcher() {
		runningObserver = new RunningStateObserver();
		blockedObserver = new BlockedStateObserver();
	}
	
	public static Dispatcher instance() {
		if (dispatcher == null) 
			dispatcher = new Dispatcher();
		return dispatcher;
	}
	
	public void extractScheduledProcess(List <Process> processes) {
		
		for (Process p : processes)
			if (p.getProcessState() == State.READY) {
				notifyObserver(runningObserver, p);
				
				CPU.instance().execute(p);
			}
		
			else if (p.getProcessState() == State.STARVED) {
				notifyObserver(blockedObserver, p);
				
			}
		
	}


	@Override
	public void notifyObserver(Observer observer, Process process) {
		observer.update(process);
		
	}

}
