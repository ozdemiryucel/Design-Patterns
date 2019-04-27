package observer;
import dataAccess.Console;
import domain.State;
import domain.Process;

public class RunningStateObserver implements Observer {

	@Override
	public void update(Process process) {
		process.setProcessState(State.RUNNING);
		Console.print(process.toString());
		
	}



}
