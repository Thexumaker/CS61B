package es.datastructur.synthesizer;
import java.util.Iterator;



public class ArrayRingBuffer<T> implements  BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;

    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        if (last == (capacity - 1)) {
            last = 0;
        } else {
            last += 1;
        }
        fillCount += 1;
    }
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T returnValue = rb[first];
        if (first == (capacity - 1)) {

            first = 0;
        } else {
            first += 1;
        }
        fillCount -= 1;
        return returnValue;
    }

    @Override
    public T peek() {

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }
    public boolean equals(Object o) {
        if (o != this) {
            return false;
        }
        return true;
    }
    public Iterator<T> iterator() {
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T> {
        private int index;
        public boolean hasNext() {
            return index < capacity;
        }
        public T next() {
            T returnItem = rb[index];
            index += 1;
            return returnItem;
        }
    }

}


