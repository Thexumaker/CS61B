public class LinkedListDeque<T> {
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
        frontSentinal = new IntNode(null,null,null);
        backSentinal = new IntNode(null,null,null);
        frontSentinal.next = backSentinal;
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
    public void addFirst(T x) {
        IntNode succesor = frontSentinal.next;
        IntNode previous = frontSentinal;
        IntNode temp = new IntNode(x,succesor,previous);
        previous.next = temp;
        succesor.previous = temp;
        size +=1;
    }

    /* Adds an item to the end of the list. */
    public void addLast(T x) {
        IntNode succesor = backSentinal;
        IntNode previous = backSentinal.previous;
        IntNode temp = new IntNode(x, succesor, previous);
        previous.next = temp;
        succesor.previous = temp;
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
        IntNode p = frontSentinal;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item);
            System.out.print(' ');

            p = p.next;
        }
        System.out.println(' ');
                                                       
    }                                                  
                                                       
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        IntNode previous = frontSentinal;
        IntNode successor = frontSentinal.next.next;
        T returnValue = frontSentinal.next.item;
        previous.next = successor;
        successor.previous = previous;
        size--;
        return returnValue;
    }
                                                       
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        IntNode previous = backSentinal.previous.previous;
        IntNode successor = backSentinal;
        T returnValue = backSentinal.previous.item;
        previous.next = successor;
        successor.previous = previous;
        size--;
        return returnValue;



                                                       
    }                                                  
                                                       
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
        IntNode p = frontSentinal.next;
        return helper(p, index);
    }                                                  
}                                                      

