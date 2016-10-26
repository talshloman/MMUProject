package hit.util;

import java.util.HashMap;
import java.util.Map;
import hit.memoryunits.Page;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HardDiskInputStream extends ObjectInputStream {

	public HardDiskInputStream(ObjectInputStream in) throws IOException {
		super(in);
	}

	public Map<Long, Page<byte[]>> readAllPages() throws IOException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		Map<Long, Page<byte[]>> pg = (HashMap<Long, Page<byte[]>>) this.readObject();
		return pg;

	}
}
