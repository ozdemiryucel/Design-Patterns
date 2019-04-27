package observer;
import dataAccess.Console;
import domain.State;
import domain.Process;

public class TerminatedStateObserver implements Observer{

	@Override
	public void update(Process process) {
		process.setProcessState(State.TERMINATED);
		Console.print(process.toString());
		
	}




}
