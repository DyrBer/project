import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class MyKeyListener implements KeyListener {
    private Field field;



    public MyKeyListener(Field field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Vector ballMove = field.getBallMove();

        }
        double dW = field.getDeskW();
        int w = field.getW();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            field.setDeskMove(10);
            Vector deskMove = field.getDeskMove();
            double desk = field.getDesk();
            if ((desk + deskMove.get(0) + dW) <= w) {
                field.setDesk(desk + deskMove.get(0));
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            field.setDeskMove(-10);
            Vector deskMove = field.getDeskMove();
            double desk = field.getDesk();
            if ((desk + deskMove.get(0)) >= 0) {
                field.setDesk(desk + deskMove.get(0));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        field.setDeskMove(0);
    }

}
