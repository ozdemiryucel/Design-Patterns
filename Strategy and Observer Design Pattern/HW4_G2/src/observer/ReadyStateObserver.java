package observer;
import dataAccess.Console;
import domain.State;
import domain.Process;


public class ReadyStateObserver implements Observer {

	@Override
	public void update(Process process) {
		process.setProcessState(State.READY);
		Console.print(process.toString());
		
	}



}
