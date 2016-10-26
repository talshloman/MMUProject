package hit.algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LFUAlgoCacheImpl<K, V> implements IAlgoCache<K, V> {
	private Map<K, V> cache;
	private Map<K, Integer> cacheCounter;
	private int capacity;

	public LFUAlgoCacheImpl(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>(capacity);
		this.cacheCounter = new HashMap<>();
	}

	@Override
	public V getElement(K key) {
		if (cache.containsKey(key)) {
			Integer count = cacheCounter.get(key);
			cacheCounter.put(key, ++count);
			return cache.get(key);
		}
		return null;
	}

	@Override
	public V putElement(K key, V value) {
		V val = null;
		if (cache.size() == capacity) {
			K leastFrequentKey = this.findLeastFrequent();
			val = cache.remove(leastFrequentKey);
			cacheCounter.remove(leastFrequentKey);
		}

		cache.put(key, value);
		cacheCounter.put(key, 0);

		return val;
	}

	@Override
	public void removeElement(K key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
			cacheCounter.remove(key);
		}
	}

	public String ToString() {
		return cache.toString();
	}

	private K findLeastFrequent() {
		int val = Collections.min(this.cacheCounter.values());
		for (Entry<K, Integer> e : this.cacheCounter.entrySet()) {
			if (e.getValue() == val) {
				return e.getKey();
			}
		}
		return null;
	}
}
