package hit.memoryunits;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;
import java.util.Map;

import hit.util.HardDiskInputStream;
import hit.util.HardDiskOutputStream;

public class HardDisk extends Object {
	private final static int SIZE = 1000;
	private final static String DEFAULT_FILE_NAME = "scr//main//resources//harddisk//HD.txt";
	private static HardDisk instance = new HardDisk();
	private static Map<Long, Page<byte[]>> hdMap;

	private HardDisk() {
		hdMap = new HashMap<Long, Page<byte[]>>(SIZE);
		try {
			readHD();
		} catch (Exception ex) {
			initializeHardDiskMap();
			writeHd();
		}
	}
	
	private void readHD() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream f = new ObjectInputStream(new FileInputStream(DEFAULT_FILE_NAME));
		HardDiskInputStream hdF = new HardDiskInputStream(f);
		hdMap = hdF.readAllPages();
		hdF.close();
	}

	public static HardDisk getInstance() {
		return instance;
	}

	public synchronized Page<byte[]> pageFault(long pageId) throws FileNotFoundException, ClassNotFoundException, IOException {
		readHD();
		return hdMap.get(pageId);
	}

	public synchronized Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, long moveToRamId) throws FileNotFoundException, ClassNotFoundException, IOException {
		readHD();
		hdMap.put(moveToHdPage.getPageId(), moveToHdPage);
		writeHd();
		return pageFault(moveToRamId);
	}

	private void writeHd() {
		try {
			ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(DEFAULT_FILE_NAME));
			HardDiskOutputStream hdF = new HardDiskOutputStream(f);
			hdF.writeAllPages(hdMap);
			hdF.flush();
			hdF.close();
		} catch (Exception ex) {
		}
	}

	private void initializeHardDiskMap() {
		for (int i = 0; i < SIZE; i++) {
			if (i < 123)// the size of byte between -128 to 127
				hdMap.put((long) i, new Page<byte[]>(i, new byte[] { (byte) i, (byte) (i + 1), (byte) (i + 2),
						(byte) (i + 3), (byte) (i + 3), (byte) (i + 4) }));
			else
				hdMap.put((long) i, new Page<byte[]>(i, new byte[] { 0, 0, 0, 0, 0 }));
		}
	}

}
