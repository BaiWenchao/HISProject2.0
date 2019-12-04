package logic.DataStructure;

public class MapEntry<K,V> {
    private final K key;
    private final V value;

    public MapEntry(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

}
