package hit.processes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import hit.memoryunits.MemoryManagementUnit;
import hit.memoryunits.Page;
import hit.processes.ProcessCycle;
import hit.util.MMULogger;

public class Process implements Runnable {

	private int id;
	private MemoryManagementUnit mmu;
	private ProcessCycles processCycles;
	private MMULogger logger = MMULogger.getInstance();

	public Process(int id, MemoryManagementUnit mmu, ProcessCycles processCycles) {
		this.id = id;
		this.mmu = mmu;
		this.processCycles = processCycles;
	}

	int getId() {
		return id;
	}

	@Override
	public synchronized void run() {
		List<ProcessCycle> ProcessCycle = processCycles.getProcessCycles();
		int ProcessCycleSize = ProcessCycle.size();
		for (int i = 0; i < ProcessCycleSize; i++) {
			System.out.println("ProcessId: " + id + ", Cycle: " + (i + 1));
			ProcessCycle proCy = ProcessCycle.get(i);
			List<Long> page = proCy.getPages();
			try {
			Page<byte[]>[] pages = mmu.getPages(page.toArray(new Long[page.size()]));
			for (int j = 0; j < pages.length; j++) {
				pages[j].setContent(proCy.getData().get(j));
				logger.write("P" + id + " R " + pages[j].getPageId() + " W " + Arrays.toString(pages[j].getContent()) + "\r\n", Level.INFO);
				System.out.println("page " + pages[j].getPageId() + ": " + Arrays.toString(pages[j].getContent()));
			}
				// Let the thread sleep for a while.
				Thread.sleep(proCy.getSleepMs());
			} catch (InterruptedException | ClassNotFoundException | IOException e) {
				logger.write("sleep interruption, shouldn't happen...", Level.SEVERE);
				e.printStackTrace();
			}
		}
	}

	void setId(int id) {
		this.id = id;
	}

	public ProcessCycles getProcessCycles() {
		return processCycles;
	}

	public void setProcessCycles(ProcessCycles processCycles) {
		this.processCycles = processCycles;
	}

	public MemoryManagementUnit getMmu() {
		return mmu;
	}

	public void setMmu(MemoryManagementUnit mmu) {
		this.mmu = mmu;
	}

}
