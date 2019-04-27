package strategy;

import domain.Process;

import java.util.Collections;
import java.util.List;

public class FirstComeFirstServeStrategy implements IStrategy {

	@Override
	public void run(List<Process> processes) {
		Collections.sort(processes, Process.getCompByArrivalTime());
		
	}



}
