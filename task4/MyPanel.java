import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {
    private Graphics g;
    private double x;
    private double y;
    private int w = 150;
    private int h = 500;
    private Field field = new Field(w, h);
    int i = 0;

    protected void paintComponent(Graphics g) {


        if (i !=0) {
            System.out.println("BOOOOO");
            field.play();
        }
        i++;
        super.paintComponent(g);
        this.g = g;
        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.black);
        g.drawRect(1, 1, w - 1, h - 1);
        g.fillRect((int) field.getDesk(), 490, 50, 10);

        x = field.getBall()[0];
        y = field.getBall()[1];
        g.fillOval((int) x, (int) y, 10,10);



    }

}






