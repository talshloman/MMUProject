package hit.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FIFOAlgoImpl<K, V> implements IAlgoCache<K, V> {
	private Map<K, V> cache;
	private LinkedList<K> que;
	private int capacity;
	
	public FIFOAlgoImpl(int capacity){
		this.capacity = capacity;
		this.cache = new HashMap<>(capacity);
		this.que = new LinkedList<>();
	}
	
	@Override
	public V getElement(K key){
		if(cache.containsKey(key)){
			return cache.get(key);
		}
		return null;
	}
	
	@Override
	public V putElement(K key,V value){
		V tmp = null;
		
		if(cache.size() == capacity){
			tmp = cache.remove(que.removeFirst());
		}

		cache.put(key, value);
		que.addLast(key);
		
		return tmp;
	}
	
	@Override
	public void removeElement(K key)
	{
		if(!que.isEmpty()){
			if(que.contains(key))
			{
				que.remove(key);
				cache.remove(key);
			}
		}
	}
	
	public String ToString()
	{
		return cache.toString();
	}

}
