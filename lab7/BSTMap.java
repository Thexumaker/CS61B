import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private K keys;
    private V values;
    private BSTMap<K, V> left;
    private BSTMap<K, V> right;

    public BSTMap() {
        size = 0;
        keys = null;
        values = null;
        left = null;
        right = null;

    }

    public BSTMap(K k, V v) {
        size = 1;
        keys = k;
        values = v;
        left = null;
        right = null;

    }
    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        keys = null;
        values = null;
        left = null;
        right = null;

    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (size == 0) {
            return false;
        }
        if (key.equals(this.keys)) {
            return true;
        }
        if (key.compareTo(this.keys) < 0) {
            return left.containsKey(key);
        }
        return right.containsKey(key);


    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (size == 0) {
            return null;
        }
        if (key.equals(this.keys)) {

            return this.values;
        }
        if (key.compareTo(this.keys) < 0) {
            return left.get(key);
        }
        return right.get(key);



    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;

    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (size == 0) {
            this.keys = key;
            this.values = value;
            size += 1;
        }
        if (this.keys.equals(key)) {
            this.values = value;
        } else if (key.compareTo(this.keys) < 0) {
            if (left == null) {
                left = new BSTMap<K, V>(key, value);
                size += 1;
            } else  {
                int si = left.size();
                left.put(key, value);
                if (left.size() > si) {
                    size += 1;
                }
            }

        } else {
            if (right == null) {
                right = new BSTMap<K, V>(key, value);
                size += 1;
            } else  {
                int si = right.size();
                right.put(key, value);
                if (right.size() > si) {
                    size += 1;
                }
            }


        }


    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new java.lang.UnsupportedOperationException("no");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new java.lang.UnsupportedOperationException("no");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new java.lang.UnsupportedOperationException("no");
    }
    public Iterator<K> iterator() {
        throw new java.lang.UnsupportedOperationException("no");
    }
    public void printInOrder() {
        if (size == 0) {
            return;
        }
        if (left != null) {
            left.printInOrder();
        }
        System.out.print(keys);
        if (right != null) {
            right.printInOrder();
        }
        System.out.print(keys);
    }
}
