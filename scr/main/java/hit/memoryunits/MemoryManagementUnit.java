package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import hit.algorithm.IAlgoCache;
import hit.util.MMULogger;

public class MemoryManagementUnit {

	private IAlgoCache<Long, Long> algo;
	private Ram ram;
	private HardDisk hd;
	private MMULogger logger = MMULogger.getInstance();

	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo) {
		this.ram = new Ram(ramCapacity);
		this.algo = algo;
		this.hd = HardDisk.getInstance();
	}

	IAlgoCache<Long, Long> getAlgo() {
		return algo;
	}

	public synchronized Page<byte[]>[] getPages(Long[] pageIds) throws FileNotFoundException, ClassNotFoundException, IOException{
		@SuppressWarnings("unchecked")
		Page<byte[]>[] result = new Page[pageIds.length];

		for (int i = 0; i < pageIds.length; i++) {
			if (algo.getElement(pageIds[i]) == null) {
				if (!ram.isFull()) {
					// adding the missing pageId to the ram
					algo.putElement(pageIds[i],pageIds[i]);
					// adding the missing page to the ram
					result[i] = hd.pageFault(pageIds[i]);
					logger.write("PF " + pageIds[i]+ "\r\n", Level.INFO);
					ram.addPage(result[i]);
				} else {
					// adding the missing pageId to the ram algo
					// saving the id of the revomed page to save on the HD
					Long pageIdToHd = algo.putElement(pageIds[i],pageIds[i]);
					// getting the page
					Page<byte[]> pageToHd = ram.getPage(pageIdToHd);
					ram.removePage(pageToHd);
					result[i] = hd.pageReplacement(pageToHd, pageIds[i]);
					logger.write("PR MTH " + pageIdToHd+" " + "MTR " + pageIds[i]+ "\r\n", Level.INFO);
					ram.addPage(result[i]);
				}
			} else {
				result[i] = ram.getPage(algo.getElement(pageIds[i]));
			}
		}
		return result;
	}

	Ram getRam() {
		return ram;
	}

	void setAlgo(IAlgoCache<Long, Long> algo) {
		this.algo = algo;
	}

	void setRam(Ram ram) {
		this.ram = ram;
	}

	public HardDisk getHd() {
		return hd;
	}

	public void setHd(HardDisk hd) {
		this.hd = hd;
	}

}
