package hit.memoryunits;

import java.util.HashMap;
import java.util.Map;

public class Ram {
	private int initialCapacity;
	private Map<Long, Page<byte[]>> pages;

	public Ram(int initialCapacity) {
		this.setInitialCapacity(initialCapacity);
		if (initialCapacity > 0)
			pages = new HashMap<>(initialCapacity);
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}

	public Map<Long, Page<byte[]>> getPages(Long pageId) {
		return pages;
	}

	public void addPage(Page<byte[]> addPage) {
		pages.put(addPage.getPageId(), addPage);
	}

	public void addPages(Page<byte[]>[] addPages) {
		for (int i = 0; i < addPages.length; i++) {
			pages.put(addPages[i].getPageId(), addPages[i]);
		}
	}

	public Page<byte[]> getPage(Long pageId) {
		return this.pages.get(pageId);
	}

	public Page<byte[]>[] getPages(Long pageId[]) {
		@SuppressWarnings("unchecked")
		Page<byte[]>[] result = new Page[pageId.length];
		for (int i = 0; i < pageId.length; i++) {
			result[i] = pages.get(pageId[i]);
		}
		return result;
	}

	public void removePage(Page<byte[]> pageId) {
		pages.remove(pageId.getPageId());
	}

	public void removePages(Page<byte[]> pageId[]) {
		for (int i = 0; i < pageId.length; i++) {
			pages.remove(pageId[i].getPageId());
		}
	}

	public boolean isFull() {
		return pages.size() >= this.getInitialCapacity();
	}

}
