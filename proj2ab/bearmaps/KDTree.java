package bearmaps;

import java.util.List;

//@author david xu
//@Source Josh Hug kdtree constructor


public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private static final boolean VERTICAL = true;
    private Node root;
    private class Node {

        private Point p;

        private Node leftChild; // refers to the down child
        private Node rightChild; // refers to the up child
        private boolean orientation;
        private Node(Point givenP, boolean or) {
            p = givenP;
            orientation = or;
        }


    }
    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, HORIZONTAL);

        }

    }
    private Node add(Point p, Node n, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        if (p.equals(n.p)) {
            return n;
        }
        int comparison = comparePoints(p, n.p, orientation);
        if (comparison < 0) {
            n.leftChild = add(p, n.leftChild, !orientation);

        } else if (comparison >= 0) {
            n.rightChild = add(p, n.rightChild, !orientation);
        }
        return n;

    }
    private int comparePoints(Point a, Point b, boolean orientation) {
        if (orientation == VERTICAL) {
            return Double.compare(a.getX(), b.getX());
        }
        return Double.compare(a.getY(), b.getY());



    }
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = nearestHelper(root, goal, root);
        return best.p;

    }
    private Node nearestHelper(Node n, Point goal, Node best) {
        Node goodSide;
        Node badSide;

        if (n == null) {
            return best;
        }
        if (Point.distance(n.p, goal) < Point.distance(best.p, goal)) {
            best = n;
        }
        if (n.orientation == VERTICAL) {
            if (goal.getX() < n.p.getX()) {
                goodSide = n.leftChild;
                badSide = n.rightChild;
            } else {
                goodSide = n.rightChild;
                badSide = n.leftChild;

            }


        } else {
            if (goal.getY() < n.p.getY()) {
                goodSide = n.leftChild;
                badSide = n.rightChild;

            } else {
                goodSide = n.rightChild;
                badSide = n.leftChild;

            }
        }
        best = nearestHelper(goodSide, goal, best);


        if (n.orientation == HORIZONTAL) {
            Point bestinworst = new Point(goal.getX(), n.p.getY());
            if (Point.distance(best.p, goal) > Point.distance(bestinworst, goal)) {
                best = nearestHelper(badSide, goal, best);
            }

        } else {
            Point temp = new Point(n.p.getX(), goal.getY());
            if (Point.distance(best.p, goal) > Point.distance(temp, goal)) {
                best = nearestHelper(badSide, goal, best);
            }

        }





        return best;
    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);




        KDTree kdr = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point nearest = kdr.nearest(0.0, 7.0);
        System.out.println(nearest.getX());
        System.out.println(nearest.getY());

    }


}
