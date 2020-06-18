package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
//@source Matt Owen @since 03-11-19 */, Priority Node
//@source Josh Hug, printFancyHeapDrawing

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {
    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;


        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }





    }
    private ArrayList<PriorityNode> minHeap;
    private Set<T> stuff = new HashSet<>();
    HashMap<T, Integer> hmap = new HashMap<>();

    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        minHeap.add(null);
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        PriorityNode temp = new PriorityNode(item, priority);

        if (contains(item)) {
            throw new java.lang.IllegalArgumentException("dsf");

        }
        minHeap.add(temp);
        hmap.put(temp.getItem(), size() - 2);
        addSwim(size() - 1);




    }
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        int initsize = stuff.size();
        stuff.add(item);
        if (initsize == stuff.size()) {
            return true;
        }
        return false;






    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (size() == 1) {
            throw new NoSuchElementException("PQ is empty");
        }
        return minHeap.get(1).getItem();

    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size() == 1) {
            throw new NoSuchElementException("PQ is empty");
        }
        if (size() == 2) {
            PriorityNode last = minHeap.remove(minHeap.size() - 1);
            return last.getItem();

        }

        PriorityNode last = minHeap.remove(minHeap.size() - 1);
        T temp = minHeap.get(1).getItem();
        minHeap.set(1, last);
        swimDown(1);

        return temp;






    }
    /* Returns the number of items in the PQ. */
    public int size() {
        return minHeap.size();

    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!hmap.containsKey(item)) {
            throw new java.util.NoSuchElementException("nope");

        }
        if (minHeap.get(hmap.get(item)).getPriority() == priority) {
            return;

        }
        double pri = minHeap.get(hmap.get(item)).getPriority();
        PriorityNode temp = new PriorityNode(item, priority);
        minHeap.set(hmap.get(item), temp);
        if (pri < priority) {
            swimDown(hmap.get(item));

        } else if (pri > priority) {

            addSwim(hmap.get(item));

        }




        //hmap.replace(item,hmap.get(item), priority);

        //mistake since we store index not actual priority//double pri = (double) hmap.get(item);






    }








    private int parent(int k) {
        return k / 2;
    }
    private int leftChild(int k) {
        return k * 2;
    }
    private int rightchild(int k) {
        return k * 2 + 1;
    }
    private void swimDown(int k) {
        if (leftChild(k) < size() && rightchild(k) >= size()) {
            if (minHeap.get(leftChild(k)).compareTo(minHeap.get(k)) < 0) {
                Collections.swap(minHeap, k, leftChild(k));
            }

        }
        if (leftChild(k) - 1 > size() || rightchild(k) >= size()) {
            return;
        } else {
            int compare = minHeap.get(rightchild(k)).compareTo(minHeap.get(leftChild(k)));
            if (compare > 0) /*right side bigger */ {
                //check left child
                if (minHeap.get(leftChild(k)).compareTo(minHeap.get(k)) < 0) {
                    Collections.swap(minHeap, k, leftChild(k));
                    swimDown(leftChild(k));
                }

            } else {
                //check right child
                if (minHeap.get(rightchild(k)).compareTo(minHeap.get(k)) < 0) {
                    Collections.swap(minHeap, k, rightchild(k));
                    swimDown(rightchild(k));

                }

            }

        }




    }








    private void addSwim(int k) {


        if ((parent(k) != 0) && minHeap.get(parent(k)).compareTo(minHeap.get(k)) > 0) {
            Collections.swap(minHeap, k, parent(k));
            addSwim(parent(k));


        }

    }
    //@source Josh Hug
    public static void printFancyHeapDrawing(Object[] items) {
        String drawing = fancyHeapDrawingHelper(items, 1, "");
        System.out.println(drawing);
    }


    /* Recursive helper method for toString. */
    private static String fancyHeapDrawingHelper(Object[] items, int index, String soFar) {
        if (index >= items.length || items[index] == null) {
            return "";
        } else {
            String toReturn = "";
            int rightIndex = 2 * index + 1;
            toReturn += fancyHeapDrawingHelper(items, rightIndex, "        " + soFar);
            if (rightIndex < items.length && items[rightIndex] != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + items[index] + "\n";
            int leftIndex = 2 * index;
            if (leftIndex < items.length && items[leftIndex] != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += fancyHeapDrawingHelper(items, leftIndex, "        " + soFar);
            return toReturn;
        }
    }

}
