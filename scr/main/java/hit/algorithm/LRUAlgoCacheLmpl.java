package hit.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUAlgoCacheLmpl<K, V> implements IAlgoCache<K, V> {
	private Map<K, V> cache;
	private LinkedList<K> cacheStack;
	private int capacity;

	public LRUAlgoCacheLmpl(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>(capacity);
		this.cacheStack = new LinkedList<>();
	}

	@Override
	public V getElement(K key) {
		if (cache.containsKey(key)) {
			cacheStack.remove(key);
			cacheStack.addFirst(key);
			return cache.get(key);
		}
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		V tmp = null;

		if (cache.size() == capacity) {
			tmp = cache.remove(cacheStack.removeLast());
		}

		cache.put(key, value);
		cacheStack.addFirst(key);

		return tmp;
	}

	@Override
	public void removeElement(K key) {
		if (cache.containsKey(key)) {
			cacheStack.remove(key);
			cache.remove(key);
		}
	}

	public String ToString() {
		return cache.toString();
	}
}
