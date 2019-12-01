package logic.DataStructure;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**只支持String为键*/

public class MyHashMap<K extends String,V> implements MyMap<K, V>{
    private MapEntry<K,V>[] array;
    private int size;
    private List<K> keyList = new ArrayList<>();

    public MyHashMap() {
        array = new MapEntry[7];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        while(true) {
            // 计算的index可用
            if(array[index] == null) {
                MapEntry newEntry = new MapEntry<>(key, value);
                array[index] = newEntry;
                ++size;
                checkForExpansion();
                keyList.add(key);
                return null;
            }
            else if(array[index].getKey().equals(key)) {
                // 计算的index上有对象，但是key相同，则取代之，返回原先对象
                V oldValue = array[index].getValue();
                array[index] = new MapEntry<>(key, value);
                return oldValue;
            }
            else {
                // 计算的index无对象，且key不同，则递增index，如果index到头了，返回0
                index = incIndex(index);
            }
        }
    }

    public List<K> getKeyList() {
        return keyList;
    }

    @Override
    public V get(K key)
    {
        int index = getIndex(key);
        int collisions = 0;
        while(true)
        {

            if(array[index] == null)
            {
                //System.out.println("Collisions: " + collisions);
                return null;
            }
            else if(array[index].getKey().equals(key))
            {
                //System.out.println("Collisions: " + collisions);
                return array[index].getValue();
            }
            else
            {
                index = incIndex(index);
                ++collisions;
            }
        }
    }

    @Override
    public V remove(K key)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int incIndex(int index)
    {
        ++index;
        if(index >= array.length)
        {
            index = 0;
        }
        return index;
    }

    private int getIndex(K key) {
        int val = 0;
        char[] ch = key.toCharArray();
        for(char c : ch){
            val += c;
        }
        return Math.abs(val) % array.length;
    }

    private void checkForExpansion()
    {
        if((size * 10) / array.length > 2)
        {
            MapEntry<K,V>[] oldArray = array;
            array = new MapEntry[array.length*2];
            size = 0;
            for(int i = 0; i < oldArray.length; ++i)
            {
                if(oldArray[i] != null)
                {
                    put(oldArray[i].getKey(), oldArray[i].getValue());
                }
            }
        }
    }

    private static class MapEntry<K,V>
    {
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
}
