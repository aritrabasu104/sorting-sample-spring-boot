package queue;

public interface CustomQueue<K, V> {
	V getItem(K key);

	void saveItem(K key, V value);
	
	void setSize(Long size);
}
