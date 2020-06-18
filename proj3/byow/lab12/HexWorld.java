package byow.lab12;
//import org.junit.Test;
//import static org.junit.Assert.*;

//import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
//import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 * @source Josh Hug's drawhexagon.txt guideline.
 */
public class HexWorld {
    public static void addHexagon(TETile[][] world, int x, int y, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int i = 0; i < 2 * s; i++) {
            int yRowStart = y + i;
            int xRowStart = x + rowOffset(s, i);
            addRow(world, xRowStart, yRowStart, rowWidth(s, i), t);
        }
    }

    private static void addRow(TETile[][] world, int x, int y, int width, TETile t) {
        for (int i = 0; i < width; i++) {
            world[x][y] = TETile.colorVariant(t, 32, 32, 32, new Random(10));
        }
    }

    private static int rowOffset(int s, int i) {
        int currI =  i;
        if (i >= s) {
            currI = 2 * s - currI - 1;
        }
        return -1 * currI;
    }

    private static int rowWidth(int s, int i) {
        int currI = i;
        if (i >= s) {
            currI = 2 * s - currI - 1;
        }
        return s + 2 * currI;
    }
}
