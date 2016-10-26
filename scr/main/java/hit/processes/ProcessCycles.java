package hit.processes;

import java.util.List;

public class ProcessCycles {

	private List<ProcessCycle> processCycles;

	ProcessCycles(List<ProcessCycle> processCycles) {
		this.processCycles = processCycles;
	}

	List<ProcessCycle> getProcessCycles() {
		return processCycles;
	}

	void setProcessCycles(List<ProcessCycle> processCycles) {
		this.processCycles = processCycles;
	}

}
