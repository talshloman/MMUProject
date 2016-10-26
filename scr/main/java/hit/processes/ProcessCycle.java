package hit.processes;

import java.util.List;

public class ProcessCycle extends Object {
	private List<byte[]> data;
	private List<Long> pages;
	private int sleepMs;

	ProcessCycle(List<Long> pages, int sleepMs, List<byte[]> data) {
		this.pages = pages;
		this.sleepMs = sleepMs;
		this.data = data;
	}

	public List<byte[]> getData() {
		return data;
	}

	List<Long> getPages() {
		return pages;
	}

	int getSleepMs() {
		return sleepMs;
	}

	void setData(List<byte[]> data) {
		this.data = data;
	}

	void setPages(List<Long> pages) {
		this.pages = pages;
	}

	void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
