package observable;

import strategy.IStrategy;
import domain.Process;

public interface ObservableProcessBatch extends Observable {
	
	public void addProcess(Process process);
	public void scheduleProcesses(IStrategy strategy);
	
}

