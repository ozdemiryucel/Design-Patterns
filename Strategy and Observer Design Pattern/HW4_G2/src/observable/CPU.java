package observable;

import observer.Observer;
import observer.TerminatedStateObserver;
import domain.Process;

public class CPU implements Observable {
	
	private static CPU cpu = null;
	private Observer terminatedObserver;
	
	private CPU() {
		terminatedObserver = new TerminatedStateObserver();
	}
	
	public static CPU instance() {
		if (cpu == null) 
			cpu = new CPU();
		return cpu;
	}

	public void execute(Process p) {
		notifyObserver(terminatedObserver, p);
		
	}


	@Override
	public void notifyObserver(Observer observer, Process process) {
		observer.update(process);
		
	}

}
