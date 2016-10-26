package hit.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class IAlgoCachTest 
{
	@Test
	public void IAlgoCacheTest()
	{
		IAlgoCache<Integer, Integer> cache = new  LFUAlgoCacheImpl<>(3);
		Integer res = -1;
		putOrGet(cache,1, 1);
		putOrGet(cache,0, 0);
		putOrGet(cache,7, 7);
		putOrGet(cache,0, 0);
		res = putOrGet(cache,8, 8);
		
		System.out.println(res);
		Assert.assertEquals(new Integer(1), res);
		Assert.assertNull(cache.getElement(1));
		
		putOrGet(cache, 8, 8);
		res = putOrGet(cache, 9, 9);
		
		System.out.println(res);
		Assert.assertEquals(new Integer(7), res);
		Assert.assertNull(cache.getElement(7));
		
		putOrGet(cache, 1, 1);
		putOrGet(cache, 1, 1);
		putOrGet(cache, 1, 1);
		putOrGet(cache, 9, 9);
		putOrGet(cache, 9, 9);
		putOrGet(cache, 9, 9);
		res = putOrGet(cache, 2, 2);
		
		Assert.assertNull(cache.getElement(8));
	}
	
	private Integer putOrGet(IAlgoCache<Integer, Integer> cache, Integer key, Integer value) 
	{
		if(cache.getElement(key) == null)
		{
			return cache.putElement(key, value);
		}
		return -1;
	}
}

