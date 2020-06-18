package bearmaps;
import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> myPoints;

    public NaivePointSet(List<Point> points) {
        myPoints = new ArrayList<>();
        for (Point p:points) {

            myPoints.add(p);

        }

    }
    @Override
    public Point nearest(double x, double y) {
        Point best = myPoints.get(0);
        Point virtual = new Point(x, y);
        for (Point p: myPoints) {
            double currentDistance = Point.distance(p, virtual);
            if (currentDistance < Point.distance(best, virtual)) {
                best = p;
            }
        }
        return best;

    }

}
