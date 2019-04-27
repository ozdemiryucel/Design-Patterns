package strategy;

import domain.Process;

import java.util.Collections;
import java.util.List;

public class PriorityStrategy implements IStrategy {

	@Override
	public void run(List<Process> processes) {
		Collections.sort(processes, Process.getCompByPriority());
		
	}




}
