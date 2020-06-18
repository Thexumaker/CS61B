package byow.Core;

import byow.SaveDemo.World;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        Random r;
        long seed = readSeed(input);
        if (seed == -1) {
            return null;
        }
        r = new Random(seed);
        generateWorld(r, finalWorldFrame);
        return finalWorldFrame;
    }

    private long readSeed(String input) {
        String seed = "";
        int i = 0;
        while (i < input.length()) {
            if (input.charAt(i) == 'n') {
                i++;
                while (input.charAt(i) != 's') {
                    seed += input.charAt(i);
                    i++;
                }
                break;
            } else if (input.charAt(i) == ':' && i + 1 < input.length()
                    && input.charAt(i + 1) == 'q') {
                System.exit(0);
            }
            i++;
        }
        if (seed.length() == 0) {
            return -1;
        }
        return Long.valueOf(seed);
    }

    private static TETile[][] generateWorld(Random r, TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        randomWalk(r, world);
        return world;
    }

    private static class Cursor {
        int x;
        int y;
        int direction;
        int steps;
        private Cursor(Random r) {
            x = RandomUtils.uniform(r, 1, WIDTH - 1);
            y = RandomUtils.uniform(r, 1, HEIGHT - 1);
//            makeFloor(x, y);
//            world[x][y] = Tileset.AVATAR;
        }
    }

    private static void randomWalk(Random r, TETile[][] world) {
        Cursor c = new Cursor(r);
        int numOfWalks = RandomUtils.uniform(r, (WIDTH + HEIGHT) / 2, WIDTH + HEIGHT);
        int numOfRooms = RandomUtils.uniform(r, numOfWalks / 8, numOfWalks / 2);
        int roomCounter = numOfWalks / numOfRooms;
        while (numOfWalks > 0) {
            c.direction = r.nextInt(4); // NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3.
            c.steps = r.nextInt((WIDTH + HEIGHT) / 4);
            checkOutOfBounds(c);
            moveCursor(c, world);
            numOfWalks--;
            if (numOfWalks != 0 && numOfWalks % roomCounter == 0) {
                makeRoom(c.x, c.y, r, world);
            }
        }
    }

    /**
     * Sets the number of steps to be the maximum possible value before the cursor goes out
     * of bounds, dependent on the direction that the cursor is moving towards.
     *
     * NOTE: Still need to surround floor with wall, so out of bounds is one tile AWAY from
     * border, NOT directly on the border.
     */
    private static void checkOutOfBounds(Cursor c) {
        // NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3.
        if (c.direction == 0) {
            int bound = HEIGHT - 2;
            if (c.y + c.steps >= bound) {
                c.steps = bound - c.y;
            }
        } else if (c.direction == 1) {
            int bound = WIDTH - 2;
            if (c.x + c.steps >= bound) {
                c.steps = bound - c.x;
            }
        } else if (c.direction == 2) {
            int bound = 1;
            if (c.y - c.steps <= bound) {
                c.steps = c.y - bound;
            }
        } else {
            int bound = 1;
            if (c.x - c.steps <= bound) {
                c.steps = c.x - bound;
            }
        }
    }

    /**
     * Reads the (x,y) parameters of the cursor and marks that tile as a floor.
     */
    private static void moveCursor(Cursor c, TETile[][] world) {
        // NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3.
        while (c.steps != 0) {
            if (c.direction == 0) {
                c.y++;
            } else if (c.direction == 1) {
                c.x++;
            } else if (c.direction == 2) {
                c.y--;
            } else {
                c.x--;
            }
            makeFloor(c.x, c.y, world);
            c.steps--;
        }
    }

    /**
     * Makes a floor and surrounds it with walls.
     */
    private static void makeFloor(int x, int y, TETile[][] world) {
        world[x][y] = Tileset.FLOOR;
        makeWall(x + 1, y, world);
        makeWall(x - 1, y, world);
        makeWall(x, y + 1, world);
        makeWall(x, y - 1, world);
        makeWall(x + 1, y + 1, world);
        makeWall(x + 1, y - 1, world);
        makeWall(x - 1, y + 1, world);
        makeWall(x - 1, y - 1, world);
    }

    /**
     * Makes a wall at a certain tile ONLY if the tile is a Tileset.NOTHING tile.
     */
    private static void makeWall(int x, int y, TETile[][] world) {
        if (world[x][y].equals(Tileset.NOTHING)) {
            world[x][y] = Tileset.WALL;
        }
    }

    /**
     * Makes a room with random dimensions for every X walks with (x,y) as one of the corners.
     */
    private static void makeRoom(int x, int y, Random r, TETile[][] world) {
        int width = RandomUtils.uniform(r, 1, WIDTH / 4);
        int length = RandomUtils.uniform(r, 1, HEIGHT / 4);
        int corner = findClosestCorner(x, y);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                switch (corner) {
                    case 0:
                        makeFloor(x + i, y - j, world);
                        break;
                    case 1:
                        makeFloor(x - i, y - j, world);
                        break;
                    case 2:
                        makeFloor(x + i, y + j, world);
                        break;
                    case 3:
                        makeFloor(x - i, y + j, world);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Returns an integer corresponding to the closest corner. Returns arbitrarily if two
     * corners are equally close.
     */
    private static int findClosestCorner(int x, int y) {
        // NORTHWEST = 0, NORTHEAST = 1, SOUTHWEST = 2, SOUTHEAST = 3.
        if (y < HEIGHT - y) {
            if (x < WIDTH - x) {
                return 2;
            }
            return 3;
        } else {
            if (x < WIDTH - x) {
                return 0;
            }
            return 1;
        }
    }
    private void mouse(TETile[][] world) {
        //get x and y coordinates of the mouse
        int xcord = (int) StdDraw.mouseX();
        int  ycord = (int) StdDraw.mouseY();
        int tempx = 0;
        int tempy = 0;
        StdDraw.enableDoubleBuffering();

        while (true) {

            // mouse click
            if (xcord != tempx || ycord != tempy){
                tempx = xcord;
                tempy = ycord;
                if (xcord == tempx && ycord == tempy) {


                }

                if ( world[xcord][ycord].equals( Tileset.NOTHING)) {
                    //draw on hud what ever it is
                    //this might be incorrect not sure about the hud yet
                    //StdDraw.enableDoubleBuffering();
                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.text(2, 1, "This is nothing");
                    StdDraw.show();

                    // need to adjust the x y coordinates
                }

                else if ( world[xcord][ycord] == Tileset.AVATAR) {
                    //draw on hud what ever it is

                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.text(1, 1, "This is our avatar");
                }
                else if (world[xcord][ycord].equals(Tileset.FLOOR)) {
                    //draw on hud what ever it is

                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.text(1, 1, "This is floor");
                }
                else if ( world[xcord][ycord] == Tileset.WALL) {
                    //draw on hud what ever it is

                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.text(1, 1, "This is Walld");
                }

            }

            xcord = (int) StdDraw.mouseX();
            ycord = (int) StdDraw.mouseY();




        }
    }


    //in order to get world coordinates
    private TETile[][] getTile() {
        return this.finalWorldFrame;

    }
}
