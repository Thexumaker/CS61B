import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.Guard;

public class Gui extends JFrame {
    private class Handlerclass implements MouseListener, MouseMotionListener {
        public void mouseClicked(MouseEvent event) {
            statusbar.setText("Clicked");

        }
        public void mousePressed(MouseEvent event) {
            statusbar.setText("z");
        }
        public void mouseReleased(MouseEvent event) {
            statusbar.setText("z");
        }
        public void mouseEntered(MouseEvent event) {
            statusbar.setText("entered");

        }
        public void mouseExited(MouseEvent event) {
            statusbar.setText("left");

        }
        public void mouseDragged(MouseEvent event) {
            statusbar.setText("z");

        }
        public void mouseMoved(MouseEvent event) {
            statusbar.setText("z");

        }
    }
    private JPanel mousepanel;
    private JLabel statusbar;
    public Gui() {
        super("Test");
        mousepanel = new JPanel();
        mousepanel.setBackground(Color.WHITE);
        add(mousepanel, Color.WHITE);
        statusbar = new JLabel();
        statusbar.setBackground(Color.WHITE);
        add(statusbar, BorderLayout.NORTH);
        Handlerclass handler = new Handlerclass();
        mousepanel.addMouseListener(handler);
        mousepanel.addMouseMotionListener(handler);
    }




}
