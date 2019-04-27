package observer;
import dataAccess.Console;
import domain.State;
import domain.Process;

public class BlockedStateObserver implements Observer{

	@Override
	public void update(Process process) {
		process.setProcessState(State.BLOCKED);
		Console.print(process.toString());
		
	}




}
