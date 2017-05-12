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
/*        double desk = field.getDesk();
        Vector deskMove = field.getDeskMove();
        int w = field.getW();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            field.setDeskMove(25);
            System.out.println("1:" + desk);
            if ((desk + deskMove.get(0) + 50) <= w) {
                field.setDesk(desk + deskMove.get(0));
                System.out.println(desk);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            field.setDeskMove(25);
            System.out.println("2:" + desk);
            if ((desk - deskMove.get(0)) >= 0) {
                field.setDesk(desk - deskMove.get(0));
                System.out.println(desk);
            }
        }*/
    }

    public void keyPressed (KeyEvent e) {
        int w = field.getW();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            field.setDeskMove(25);
            Vector deskMove = field.getDeskMove();
            double desk = field.getDesk();
            System.out.println("1:" + desk);
            if ((desk + deskMove.get(0) + 50) <= w) {
                field.setDesk(desk + deskMove.get(0));
                System.out.println(desk);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            field.setDeskMove(-25);
            Vector deskMove = field.getDeskMove();
            double desk = field.getDesk();
            System.out.println("2:" + desk);
            if ((desk + deskMove.get(0)) >= 0) {
                field.setDesk(desk + deskMove.get(0));
                System.out.println(desk);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        field.setDeskMove(0);
    }

}
