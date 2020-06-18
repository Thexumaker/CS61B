package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;


import java.util.ArrayList;
import java.util.List;

//@author David Xu
//@Source Josh Hug testpq, testWith1000points, compareTest

public class KDTreeTest {
    private static Random r = new Random(500);
    public KDTree lecture() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree kdr = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        return kdr;

    }
    @Test
    public void testBasic() {
        KDTree kd = lecture();
        Point nearest = kd.nearest(0.0, 7.0);
        Point expected = new Point(1.0, 5.0);
        assertEquals(nearest, expected);


    }
    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);

    }

    private List<Point> randomPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(randomPoint());
        }
        return points;
    }
    private void testpq(int pointCount, int query) {
        List<Point> points = randomPoints(pointCount);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);
        List<Point> queries = randomPoints(query);
        for (Point p : queries) {

            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }
    @Test
    public void testWith1000points() {
        int pc = 10000;
        int q = 200;
        testpq(pc, q);

    }
    private void timewithnq(int ps, int q) {

        List<Point> points = randomPoints(ps);
        KDTree kd = new KDTree(points);
        Stopwatch sw = new Stopwatch();
        List<Point> queries = randomPoints(q);
        for (Point p : queries) {
            Point actual = kd.nearest(p.getX(), p.getY());

        }
        System.out.println("Time elapsed for " + q + "queries on "
                + ps + " points" + sw.elapsedTime());

    }
    @Test
    public void timewith10000PointsAnd2000Que() {
        List<Integer> points = List.of(200000);
        for (int N : points) {
            timewithnq(N, 100000);

        }

    }
    public void compareTest() {
        List<Point> points = randomPoints(50000);
        KDTree kd = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);
        List<Point> queryp = randomPoints(50000);
        Stopwatch sw = new Stopwatch();
        for (Point p : queryp) {
            nps.nearest(p.getX(), p.getY());

        }
        double times = sw.elapsedTime();
        System.out.println("Naive took" + times);

        sw = new Stopwatch();
        for (Point p : queryp) {
            kd.nearest(p.getX(), p.getY());

        }
        double time = sw.elapsedTime();
        assertTrue(time < times);
        System.out.println("kd took" + time);



    }
    @Test
    public void compareTime() {
        compareTest();
    }




}
