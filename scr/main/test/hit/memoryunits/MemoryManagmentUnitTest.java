package hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import hit.memoryunits.MemoryManagementUnit;
import hit.memoryunits.Page;
import hit.algorithm.IAlgoCache;
import hit.algorithm.LRUAlgoCacheLmpl;

public class MemoryManagmentUnitTest {

	private final static Integer RAM_CAPACITY = 100;

	@Test
	public void testMmu()throws FileNotFoundException, ClassNotFoundException, IOException{

		IAlgoCache<Long, Long> lruAlgo = new LRUAlgoCacheLmpl<Long, Long>(RAM_CAPACITY);
		MemoryManagementUnit mmu = new MemoryManagementUnit(RAM_CAPACITY, lruAlgo);

		Page<byte[]>[] pages = mmu.getPages(new Long[] { (long) 31, (long) 51, (long) 103, (long) 115 });

		for (int i = 0; i < pages.length; i++) {
			System.out.println("Page ID: " + pages[i].getPageId());
			System.out.println(Arrays.toString(pages[i].getContent()));
		}
	}
}
