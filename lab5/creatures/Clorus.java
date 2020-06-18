package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    private int r;

    private int g;

    private int b;


    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public String name() {
        return "clorus";
    }


    public Clorus() {
        this(1);
    }
    public Color color() {
        r = 34;

        g = 0;


        b = 231;
        return color(r, g, b);
    }


    public void attack(Creature c) {
        this.energy += c.energy();
    }


    public void move() {
        this.energy -= .03;

    }
    public void stay() {
        this.energy += .01;

    }

    public Clorus replicate() {
        Clorus rep = new Clorus(this.energy / 2);
        this.energy = energy / 2;
        return rep;


    }
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipse = new ArrayDeque<>();
        boolean anyPlips = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction key : neighbors.keySet()) {
            Occupant ti = neighbors.get(key);

                if (ti.name().equals("empty")) {
                    emptyNeighbors.addFirst(key);
                }
                if (ti.name().equals("plip")) {
                    plipse.addFirst(key);
                    anyPlips = true;
                }

        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        if (anyPlips) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipse));
        }



        if (this.energy > 1) {
            return new Action(Action.ActionType.REPLICATE,
                    HugLifeUtils.randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors) );
    }
    }

