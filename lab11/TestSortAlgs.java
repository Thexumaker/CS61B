import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {








    }

    @Test
    public void testMergeSort() {
        Queue<Integer> tas = new Queue<Integer>();
        tas.enqueue(10);
        tas.enqueue(2);
        tas.enqueue(5);
        tas.enqueue(7);
        tas.enqueue(1);
        Queue<Integer> sorted = new Queue<>();

        sorted = MergeSort.mergeSort(tas);




        assertTrue(isSorted(sorted));

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
