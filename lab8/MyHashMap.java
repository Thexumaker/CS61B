import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java. util. Iterator;

// so basically each linked list takes in entries which have keys and values


public class MyHashMap<K, V>  implements Map61B<K, V> {
    private int size;
    private int count;
    private double lF;
    private HashSet<K> keySet = new HashSet<K>();
    private int numItems =0;

    private LinkedList[] table;

    private static class Entry<K,V> {
        private K key;
        private V val;


        public Entry(K k, V v) {
            key = k;
            val = v;

        }

        public K getKey() {
            return key;
        }

        public V getval() {
            return val;
        }
        public void setKey(K k) {
            key = k;
        }
        public void setVal(V v) {
            val = v;
        }
    }



    public MyHashMap() {
        size =16;
        lF = .75;
        count = 16;

        table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }


    }
    public MyHashMap(int initialSize) {
        size = initialSize;
        count = initialSize;
        lF = .75;

        table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }



    }
    public MyHashMap(int initialSize, double loadFactor) {
        size = initialSize;
        count = initialSize;
        lF = loadFactor;
        table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }


    }
    public void clear() {
        for (int i =0; i < size; i++) {
            if (table[i] == null) {
                continue;
            }
            int orig = table[i].size();
            table[i].clear();
            Entry<K,V> temp = new Entry<K,V>(null,null);
            for (int j =0; j < orig;j++) {

                table[i] = null;

            }
        }
        size = 0;
        numItems = 0;

    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return get(key)!=null;


    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {

        int hashcode = key.hashCode();
        int position = Math.floorMod(hashcode,count);
        if (table[position] == null) {
            return null;
        }
        for (int i = 0; i < table[position].size(); i++) {
            Entry<K,V> temp = (Entry<K, V>) table[position].get(i);
            if (key.equals(temp.getKey())) {
                return temp.getval();
            }

        }
        return null;
    }



    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return numItems;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    private void reSize() {
        int oldSize = size;
        size = size *2;
        count = count *2;
        LinkedList[] oldTable = table;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] == null) {
                continue;
            }
            while (!oldTable[i].isEmpty()) {
                Entry<K,V> temp = (Entry<K, V>) oldTable[i].getFirst();
                int hashed = temp.getKey().hashCode();
                int position = Math.floorMod(hashed,count);
                if (table[position] == null) {
                    table[position] = new LinkedList<Entry<K, V>>();

                    table[position].addLast(temp);
                    oldTable[i].removeFirst();
                }
                else{
                    table[position].addLast(temp);
                    oldTable[i].removeFirst();

                }




            }

        }

    }
    public void put(K key, V value) {
        keySet.add(key);
        Entry<K,V> temp = new Entry<>(key, value);
        int hashed = key.hashCode();
        int position = Math.floorMod(hashed,count);
        if (table[position] == null) {
            table[position] = new LinkedList<Entry<K,V>>();
            table[position].addLast(temp);
            numItems += 1;

        }

        else if (table[position] != null) {
            boolean repeat = false;
            for (int i = 0; i < table[position].size(); i++) {
                Entry<K,V> temps = (Entry<K,V>)table[position].get(i);
                if (temps.getKey() == key) {
                    table[position].remove(temps);
                    numItems -= 1;

                }
            }

            table[position].addLast(temp);
            numItems += 1;


        }
        if ((double)numItems / (double)size > lF) {
            reSize();


        }



    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keySet;

    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new java.lang.UnsupportedOperationException("fdsfds");
    }
    public V remove(K key, V value) {
        throw new java.lang.UnsupportedOperationException("fdsfds");

    }
    public Iterator<K> iterator() {
        throw new java.lang.UnsupportedOperationException("no");
    }

}
