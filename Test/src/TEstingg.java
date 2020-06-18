import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

import static java.awt.event.KeyEvent.VK_ENTER;

public class TEstingg {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        getName();

    }
    private static void getName() {
        String name = "";
        while(true) {
            while (StdDraw.hasNextKeyTyped()) {
                char temp = StdDraw.nextKeyTyped();
                System.out.println("ok");
                if (temp == 'q') {
                    break;
                }

                name = name + temp;


                //StdDraw.text(.3,.7,"temp");

            }
            displayname(name);

        }


    }
    private static void displayname(String n) {

        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(.4,.96 ,  .5, .5);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(.4,  .96, n);
        StdDraw.show();




    }
}
