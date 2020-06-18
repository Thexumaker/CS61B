import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stopwatch;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;

import static edu.princeton.cs.algs4.StdAudio.loop;
import static edu.princeton.cs.algs4.StdAudio.play;
import static java.awt.event.KeyEvent.VK_AT;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.image.ImageObserver.HEIGHT;

public class MouseFollower {

    public static void main(String[] args) {




        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        double tempx = 0;
        double tempy = 0;
        Random rand = new Random();
        StdDraw.setPenColor(StdDraw.CYAN);
        String file = "/tet312.wav";
        loop(file);


        while (true) {
            if (StdDraw.hasNextKeyTyped() ) {
                StdAudio.play(file);


                char c = StdDraw.nextKeyTyped();
                if( c == 'c') {
                    StdDraw.clear();
                }
                else if ( c == 's') {
                    StdDraw.text(.3,.7,"dick");
                }
                else if (c == 'r') {
                    int rand_int1 = rand.nextInt(1000);

                    if (rand_int1 < 500) {
                        System.out.println("gay");
                        StdDraw.setPenColor(StdDraw.BLACK);
                    }
                    else {
                        StdDraw.setPenColor(StdDraw.GREEN);
                    }


                }
                else if (c == 'u') {
                    StdDraw.filledRectangle(x,y, x/2,y/2);

                }

            }


            // mouse click
            if ((x != tempx || y != tempy ) && StdDraw.isMousePressed()){
                tempx = x;
                tempy = y;

                StdDraw.line(x ,y -.01 ,x , y + .01 );

                System.out.println(x + " " + y);

            }


            // mouse location

            x = StdDraw.mouseX();
            y = StdDraw.mouseY();






        }

    }




}
