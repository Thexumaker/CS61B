public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = items.length - 1;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        first = other.first;
        last = other.last;
        for (int i = 0; i < other.size(); i++) {
            items[i] = (T) other.get(i);
        }
    }

    public void addFirst(T item) {
        if (size == items.length) {
            if (size < 16) {
                resize(size + 1);
            } else {
                resize(size * 2);
            }
        }
        T[] temp = (T[]) new Object[items.length];
        temp[0] = item;
        for (int i = 0; i < size; i++) {
            temp[i + 1] = items[i];
        }
        items = temp;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            if (size < 16) {
                resize(size + 1);
            } else {
                resize(size * 2);
            }
        }
        items[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);
            System.out.print(' ');
        }
        System.out.println(' ');
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        double sized = (double) size;
        double percentageUsed = sized / items.length;
        if (percentageUsed < .25) {
            resize(items.length / 2);
        }
        T firstValue = items[0];
        T[] emptyValue = (T[]) new Object[0];
        if (size == 1) {
            size = 0;
            items = emptyValue;
            return firstValue;
        }
        T[] temp = (T[]) new Object[items.length - 1];
        for (int i = 0; i < size - 1; i++) {
            temp[i] = items[i + 1];
        }
        size -= 1;
        items = temp;
        return firstValue;
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        double sized = (double) size;
        double percentageUsed = sized / items.length;
        if (percentageUsed < .25) {
            resize(items.length / 2);
        }
        T[] temp = (T[]) new Object[items.length - 1];
        if (size == 1) {
            T value = items[0];
            size = 0;
            items = temp;
            return value;
        }
        T lastValue = items[size - 1];
        for (int i = 0; i < (size - 1); i++) {
            temp[i] = items[i];
        }
        size -= 1;
        items = temp;
        return lastValue;
    }

    public T get(int index) {
        if (index < 0 || size == 0 || index >= size) {
            return null;
        }
        return items[index];
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
}
