package observer;
import dataAccess.Console;
import domain.State;
import domain.Process;


public class NewStateObserver implements Observer{

	@Override
	public void update(Process process) {
		process.setProcessState(State.NEW);
		Console.print(process.toString());
		
	}




}
