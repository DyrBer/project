import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel implements Listener {
    private Field field;
    private Vector blocks;
    public MyPanel(Field field) {
        this.field = field;
        field.addListener(this);
        blocks = new Vector();
        blocks = field.getBlocks();

    }


    protected void paintComponent(Graphics g) {
        int w = field.getW();
        int h = field.getH();
        int r = field.getR();


        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.black);
        g.drawRect(1, 1, w - 1, h - 1);
        g.fillRect((int) blocks.get(0), 0, 25, h);
        g.fillRect((int) field.getDesk(), h-10, 50, 10);

        double x = field.getBall()[0];
        double y = field.getBall()[1];
        g.fillOval((int) x, (int) y, r, r);
    }

    @Override
    public void modelChanged() {
        this.repaint();
    }
}






