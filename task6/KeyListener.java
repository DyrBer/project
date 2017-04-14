import java.awt.event.KeyEvent;
import java.util.EventListener;

public class KeyListener implements EventListener {
    private Field field = new Field(500, 500);
    int w = field.getW();
    double desk = field.getDesk();
    Vector deskMove = field.getDeskMove();


    public void keyPressed (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
            while ((desk + deskMove.get(0) + 50) <= w) {
                desk += field.deskMove.get(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_KP_LEFT) {
            while ((desk + deskMove.get(0) + 50) >= w) {
                desk -= field.deskMove.get(0);
            }
        }
    };
}
