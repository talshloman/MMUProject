package hit.processes;

import java.util.List;

public class RunConfiguration {

	private List<ProcessCycles> processesCycles;

	public RunConfiguration(List<ProcessCycles> processesCycles) {
		this.processesCycles = processesCycles;
	}

	public List<ProcessCycles> getProcessesCycles() {
		return processesCycles;
	}

	void setProcessesCycles(List<ProcessCycles> processesCycles) {
		this.processesCycles = processesCycles;

	}
}
