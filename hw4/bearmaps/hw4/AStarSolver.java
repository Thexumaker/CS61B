package bearmaps.hw4;
import bearmaps.proj2ab.DoubleMapPQ;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private DoubleMapPQ<Vertex> pq;
    private SolverOutcome outcome;
    private Map<Vertex, Double> distTo;
    private  Map<Vertex, Vertex> edgeTo;
    private List<Vertex> solution;
    private Vertex goal;
    private Vertex smallest;
    private List<Vertex> solutionOrdered;
    private double timeSpent;

    private Stopwatch sw;
    private int numS;
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        sw = new Stopwatch();
        goal = end;
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        numS = 0;
        solution = new ArrayList<>();
        solutionOrdered = new ArrayList<>();
        pq = new DoubleMapPQ<>();
        pq.add(start, 0);
        distTo.put(start, 0.0);
        Vertex temp;
        while (pq.size() != 0  && timeSpent < timeout && !pq.getSmallest().equals(end)) {
            smallest = pq.getSmallest();
            temp = pq.removeSmallest();
            numS += 1;

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(temp);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                relax(e, end, input);

            }
            timeSpent = sw.elapsedTime();

        }
        if (sw.elapsedTime() > timeout) {
            outcome = SolverOutcome.TIMEOUT;
        } else if (pq.size() == 0 && !smallest.equals(end)) {
            outcome = SolverOutcome.UNSOLVABLE;
        } else {
            Vertex p = end;
            while (!p.equals(start)) {
                solution.add(p);
                p = edgeTo.get(p);
            }
            solution.add(p);
            int i = solution.size() - 1;
            while (i >= 0) {
                solutionOrdered.add(solution.remove(i));
                i = i - 1;
            }
            outcome = SolverOutcome.SOLVED;
        }




    }
    private void relax(WeightedEdge e, Vertex goals, AStarGraph<Vertex> input) {
        Vertex p;
        Vertex q;
        double w;
        p = (Vertex) e.from();
        q = (Vertex) e.to();
        w = e.weight();

        if (!distTo.containsKey(q) || distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);


            if (pq.contains(q)) {
                pq.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, goals));

            } else if (!pq.contains(q)) {
                pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, goals));
            }
            edgeTo.put(q, p);


        }









    }

    public SolverOutcome outcome() {
        return outcome;

    }
    public List<Vertex> solution() {
        return solutionOrdered;

    }
    public double solutionWeight() {
        return distTo.get(goal);

    }
    public int numStatesExplored() {
        return numS;

    }
    public double explorationTime() {
        return timeSpent;

    }
}
