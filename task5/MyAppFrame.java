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








        JButton b = new JButton("Start");
        b.addActionListener(new ActionListener() {
            private boolean pulsing = false;
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(40, this);


                if (pulsing) {
                    pulsing = false;
                    timer.stop();
                    b.setText("Start");
                } else {
                    pulsing = true;
                    timer.start();
                    b.setText("Stop");
                }
                getContentPane().repaint();
            }
        });
        getContentPane().add(b, BorderLayout.EAST);



        setSize(700, 800);
        setVisible(true);


    }

}
