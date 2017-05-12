import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class MyAppFrame extends JFrame {



    public MyAppFrame(Field field) {
        super("MovingCircle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new MyPanel(field);
        getContentPane().add(panel);
        setSize(700, 800);
        this.addKeyListener(new MyKeyListener(field));

        setVisible(true);

    }

}
