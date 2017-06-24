import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args){
        Field model = new Field(600, 500);
        new MyAppFrame(model);

        Timer timer = new Timer(15, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                model.play();
            }
        });
        timer.start();
    }
}