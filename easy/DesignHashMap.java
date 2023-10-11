package easy;
// 706

import java.util.ArrayList;
import java.util.List;

public class DesignHashMap<K, V> {

    private List<Entry<K, V>>[] buckets;
    private final int INITAIL_CAPACITY = 100;


    private static class Entry<K, V> {
        private K key;
        private V value;


        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor 
    public DesignHashMap() {
        buckets = new List[INITAIL_CAPACITY];
        for (int i = 0; i < INITAIL_CAPACITY; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void put(K key, V value) {
        int index = getIndex(key, buckets.length);
        for (Entry<K,V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[index].add(new Entry<>(key, value));
    }

    // helper 
    private int getIndex(K key, int capacity) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = getIndex(key, buckets.length);
        for (Entry<K,V> entry : buckets[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // no such key in Map
    }

    public void remove(K key) {
        int index = getIndex(key, buckets.length);
        buckets[index].removeIf(entry -> entry.key.equals(key));
    }

    private void resize() {
        int newCapacity = buckets.length * 2; // Double the capacity
        List<Entry<K, V>>[] newTable = new List[newCapacity];
    
        for (int i = 0; i < buckets.length; i++) {
            List<Entry<K, V>> bucket = buckets[i];
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int index = getIndex(entry.key, newCapacity);
    
                    if (newTable[index] == null) {
                        newTable[index] = new ArrayList<>();
                    }
    
                    newTable[index].add(entry);
                }
            }
        }
    
        buckets = newTable; // Update the reference to the new table
    }

    
    public static void main(String[] args) {
        DesignHashMap<Object, Object> map = new DesignHashMap<>();

        map.put(1,2);
        map.put(2, "a");
        map.put(10,2);
        System.out.println(map.get(2));
        map.put(2, 3);
        System.out.println(map.get(2));
    }


}
