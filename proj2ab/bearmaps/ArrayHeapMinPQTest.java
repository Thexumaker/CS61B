package bearmaps;

import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAdd() {

        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();
        demo.add(1, 2.0);
        demo.add(2, 1.0);
        demo.add(3, 3.8);
        demo.add(4, 1.0);
        demo.add(5, 420420.0);
        demo.add(6, 6.9);
        demo.add(7, 3000.0);
        demo.add(8, 9.5);
        demo.add(9, 5);
        demo.add(10, 1.23);



    }
    @Test(expected = IllegalArgumentException.class)
    public void exception() {
        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();
        demo.add(2, 9.5);
        demo.add(2, 5);

    }
    @Test(expected = java.util.NoSuchElementException.class)
    public void exceptions() {
        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();
        demo.getSmallest();

    }




    @Test
    public void testTimeadd() {
        Random rand = new Random();
        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();




        long start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i += 1) {

            demo.add(i, rand.nextDouble());

        }


        long end = System.currentTimeMillis();
        assertTrue((end - start) / 1000.0 < Math.log(200000));

        System.out.println("Total time elapsed: " + (end - start) / 1000.0 + " seconds.");


    }
    @Test
    public void testTimeremove() {
        Random rand = new Random();
        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 200000; i += 1) {

            demo.add(i, rand.nextDouble());

        }


        long start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i += 1) {

            demo.removeSmallest();

        }



        long end = System.currentTimeMillis();
        assertTrue((end - start) / 1000.0 < Math.log(200000));

        System.out.println("Total time elapsed: " + (end - start) / 1000.0 + " seconds.");


    }


    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> demo = new ArrayHeapMinPQ<>();
        demo.add("a", 2.0);
        demo.add("b", 4.0);
        demo.add("c", 3.8);
        demo.add("d", 1.0);
        demo.add("u", 6.0);

        assertTrue(demo.contains("a"));
        assertTrue(demo.contains("u"));
        assertTrue(demo.contains("b"));
        assertTrue(demo.contains("c"));
        assertFalse(demo.contains("fds"));

    }
    @Test
    public void testRemove() {

        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();
        demo.add(1, 2.0);
        demo.add(2, 4.0);
        demo.add(3, 3.8);
        demo.add(4, 1.0);
        demo.add(5, 6.0);
        demo.add(6, 2.5);
        demo.add(7, .1);
        demo.add(8, 60);
        demo.add(9, 3.5);
        demo.add(69, .001);
        assertEquals(new Integer(69), demo.removeSmallest());
        assertEquals(new Integer(7), demo.removeSmallest());
        assertEquals(new Integer(4), demo.removeSmallest());
        assertEquals(new Integer(1), demo.removeSmallest());
        assertEquals(new Integer(6), demo.removeSmallest());
        assertEquals(new Integer(9), demo.removeSmallest());
        assertEquals(new Integer(3), demo.removeSmallest());
        assertEquals(new Integer(2), demo.removeSmallest());
        assertEquals(new Integer(5), demo.removeSmallest());
        assertEquals(new Integer(8), demo.removeSmallest());













    }
    @Test
    public void testIdk() {

        ArrayHeapMinPQ<Integer> demo = new ArrayHeapMinPQ<>();
        demo.add(1, 2.0);
        demo.add(2, 1.0);
        demo.add(3, 3.8);
        demo.add(4, 1.0);
        demo.add(5, 420420.0);
        demo.add(6, 6.9);
        demo.add(7, 3000.0);
        demo.add(8, 9.5);
        demo.changePriority(3, .000000001);
        demo.add(9, .001);
        demo.add(10, .00001);
        assertEquals(new Integer(3), demo.removeSmallest());





    }
    @Test
    public void idkdddd() {

        ArrayHeapMinPQ<Character> demo = new ArrayHeapMinPQ<>();
        demo.add('a', 2.0);
        demo.add('b', 2.0);
        demo.add('c', 2.0);


    }

}

