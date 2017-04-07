import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
public class MyAppFrame extends JFrame {

    private Timer timer;


    public MyAppFrame() {
        super("MovingCircle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new MyPanel();
        getContentPane().add(panel);
        timer = new Timer(40, new ActionListener () {
        public void actionPerformed(ActionEvent ev) {
            getContentPane().repaint();
        }
    });
        timer.start();

        setSize(700, 800);
        setVisible(true);


    }

}
