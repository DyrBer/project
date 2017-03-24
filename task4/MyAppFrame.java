import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MyAppFrame extends JFrame {

    public MyAppFrame() {
        super("MovingCircle");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new MyPanel();
        getContentPane().add(panel);

        JButton b = new JButton("Step");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().repaint();
            }
        });
        getContentPane().add(b, BorderLayout.EAST);



        setSize(700, 800);
        setVisible(true);


    }

}
