package observer;
import dataAccess.Console;
import domain.State;
import domain.Process;

public class StarvedStateObserver implements Observer{

	@Override
	public void update(Process process) {
		process.setProcessState(State.STARVED);
		Console.print(process.toString());
		
	}

}
