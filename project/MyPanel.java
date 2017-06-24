import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MyPanel extends JPanel implements Listener {
    private Field field;
    private Vector[] block;
    public MyPanel(Field field) {
        this.field = field;
        field.addListener(this);
        block = new Vector[3];
        block=field.getBlocks();

    }


    protected void paintComponent(Graphics g) {
        int w = field.getW();
        int h = field.getH();
        int r = field.getR();
        int bH = field.getBlockH();
        int bW = field.getBlockW();
        int dW = field.getDeskW();


        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.black);
        g.drawRect(1, 1, w - 1, h - 1);
        int k = 0;
        for (int i = 0; i <= 7; i++) {

            g.setColor(Color.red);
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }
        for (int i = 8; i <= 15; i++) {
            g.setColor(new Color(255, 127, 0));
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }
        for (int i = 16; i <= 23; i++) {
            g.setColor(new Color(255, 215, 0));
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }
        for (int i = 24; i <= 31; i++) {
            g.setColor(new Color(50, 205, 50));
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }
        for (int i = 32; i <= 39; i++) {
            g.setColor(new Color(0, 191, 255));
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }
        for (int i = 40; i <= 47; i++) {
            g.setColor(new Color(16, 78, 239));
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }
        for (int i = 48; i <= 55; i++) {
            g.setColor(new Color(158, 78, 238));
            if(block[i] != null) {
                g.fillRect((int) block[i].get(0), (int) block[i].get(1), bW, bH);
            }
        }

        g.setColor(new Color(139, 69, 19));
        g.fillRoundRect((int) field.getDesk(), h-10, dW, 10, 10, 10);

        double x = field.getBall()[0];
        double y = field.getBall()[1];
        g.setColor(Color.black);
        g.fillOval((int) x, (int) y, r, r);

    }

    @Override
    public void modelChanged() {
        this.repaint();
    }
}






