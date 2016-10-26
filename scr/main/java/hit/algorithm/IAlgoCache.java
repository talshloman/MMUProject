
package hit.algorithm;

/**
 * @author Tal Shloman  304847452
 * 		   Koral Shimoni 311201974
 *
 */
public interface IAlgoCache <K, V>  {
	
	public V getElement(K key);

	public V putElement(K key, V value);

	public void removeElement(K key);
}
