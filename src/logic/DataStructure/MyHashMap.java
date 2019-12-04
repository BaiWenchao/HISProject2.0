package logic.DataStructure;

import java.util.Set;
import java.util.TreeSet;

public class MyHashMap<K,V> {

    private class Entry{
        K k;
        V v;
        private Entry(K key, V value){
            k = key;
            v = value;
        }
        public K getKey(){
            return k;
        }
        public V getVal(){
            return v;
        }
    }

    private Object[] entries;
    private Set<String> keySet = new TreeSet<>();


    private int size;
    public MyHashMap() {
        clear();
    }

    public Set<String> getKeySet() {
        return keySet;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int hash(K key){
        // 获取键的哈希值
        return Math.abs(key.hashCode())%entries.length;
    }

    public int size() {
        // 返回哈希表的大小
        return size;
    }

    public V get(K key) {
        // 获得某一个键的值
        if(key == null){
            return null;
        }
        int h = hash(key);
        Entry e = (Entry)entries[h];
        if ( e != null && e.getKey().equals(key)  ) {
            return e.getVal();
        }
        return null;
    }

    public Object[] getEntries() {
        return entries;
    }

    public void setEntries(Object[] entries) {
        this.entries = entries;
    }

    public void setKeySet(Set<String> keySet) {
        this.keySet = keySet;
    }

    public V remove(K key) {
        if(key == null){
            return null;
        }
        int h = hash(key);
        Entry t = (Entry)entries[h];
        if(t != null && t.getKey().equals(key)){
            size--;
            entries[h]=null;
            keySet.remove(key.toString());
            return t.getVal();
        }
        return null;
    }

    public void put(K key, V value) {
        if(key == null || value == null) {
            return;
        }
        int h = hash(key);
        if(entries[h] == null){
            entries[h] = new Entry(key, value);
            keySet.add(key.toString());
            size++;
            return;
        }
        if(((Entry)entries[h]).getKey().equals(key)){
            entries[h] = new Entry(key, value);
            return;
        }
        // 当发生冲突就进行再散列
        grow(2*entries.length);
        put(key, value);
    }

    public void grow(int s){
        // 再散列方法
        Object[] temp = new Object[s];
        for(int i=0; i<entries.length; i++){
            if(entries[i] != null){
                Entry e = (Entry)entries[i];
                int p = Math.abs(e.getKey().hashCode()) % temp.length;
                if(temp[p] != null){
                    grow(2*s);
                    return;
                }
                temp[p]=e;
            }

        }

        entries = temp;
    }

    public void clear()
    {
        entries = new Object[20];
        size=0;
    }

}