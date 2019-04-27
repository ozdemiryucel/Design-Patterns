package domain;
import observable.*;
import dataAccess.*;
import strategy.*;


public class ProcessManagementApplication {
	
	public void run() {
		
		ProcessBatch processBatch = new ProcessBatch();
		
		for (int i = 1; i < 11; i++) 
			processBatch.addProcess(new Process(i));
		
		Console.print("");
		
		
		Console.print("1) First Come First Serve");
		Console.print("2) Shortest Job First"); 
		Console.print("3) Priority"); 
		
		int strategyNo = Console.getInteger("Enter strategy number you want:");
		
		IStrategy strategy = null;
		
		switch (strategyNo) {
			case 1:
				strategy = new FirstComeFirstServeStrategy();
				break;
				
			case 2:
				strategy = new ShortestJobFirstStrategy();
				break;
				
			case 3:
				strategy = new PriorityStrategy();
				break;
	
			default:
				Console.print("There is no such a strategy");
				break;
		}
		
		processBatch.scheduleProcesses(strategy);
	}

}
