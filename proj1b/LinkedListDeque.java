public class LinkedListDeque<T> implements Deque<T> {
    private class IntNode {
        private T item;
        private IntNode next;
        private IntNode previous;

        private IntNode(T i, IntNode n, IntNode p) {
            item = i;
            next = n;
            previous = p;
        }
    }

    private IntNode frontSentinal;
    private IntNode backSentinal;
    private int size;

    public LinkedListDeque() {
        frontSentinal = null;
        backSentinal = null;
        size = 0;
    }


    public LinkedListDeque(LinkedListDeque other) {

        IntNode temp = new IntNode(null, null, null);
        temp.previous = temp;
        temp.next = temp;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i)); }


    }



    /* Adds an item to the front of the list. */
    @Override
    public void addFirst(T x) {
        IntNode p = new IntNode(x, null, null);
        if (size == 0) {
            frontSentinal = p;
            backSentinal = p;
        } else {
            frontSentinal.previous = p;
            p.next = frontSentinal;
            frontSentinal = p;
        }

        size += 1;
    }

    /* Adds an item to the end of the list. */
    @Override
    public void addLast(T x) {
        IntNode p = new IntNode(x, null, null);
        if (size == 0) {
            frontSentinal = p;
            backSentinal = p;
        } else {
            backSentinal.next = p;
            p.previous = backSentinal;
            backSentinal = p;

        }
        size += 1;
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode p = frontSentinal;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item);
            System.out.print(' ');

            p = p.next;
        }
        System.out.println(' ');
                                                       
    }                                                  
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            IntNode p = frontSentinal;
            frontSentinal = null;
            backSentinal = null;
            size = 0;
            return p.item;
        }                              
        IntNode p = frontSentinal;
        frontSentinal = frontSentinal.next;
        frontSentinal.previous = null;
        size -= 1;
        return p.item;
    }                                                  
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            IntNode p = frontSentinal;
            frontSentinal = null;
            backSentinal = null;
            size = 0;
            return p.item;
        }
        IntNode p = backSentinal;
        backSentinal = backSentinal.previous;
        backSentinal.next = null;
        size -= 1;
        return p.item;

                                                       
    }                                                  
    @Override
    public T get(int index) {
        if (index < 0 || size == 0 || index >= size) {
            return null;
        }
        IntNode p = frontSentinal;
        while (index > 0 && p.next != null) {
            p = p.next;
            index = index - 1; }
        return p.item;

        /* use iteration*/
                                                       
    }

    private  T helper(IntNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return helper(node.next, index - 1);


    }
                                                       
    public T getRecursive(int index) {
        if (index < 0 || size == 0 || index >= size) {
            return null;
        }
        IntNode p = frontSentinal;
        return helper(p, index);
    }                                                  
}                                                      

