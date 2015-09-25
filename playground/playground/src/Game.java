import javax.swing.*;
import java.awt.*;

/**
 * Created by Pascal on 20.09.2015.
 */
public class Game extends JFrame implements Runnable {
    private  GamePanel panel;
    public Game(Map map)
    {
        super("Sokobar");
        panel = new GamePanel(map);
        add(panel);
        addKeyListener(panel);
        this.pack();
        setVisible(true);
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while(isVisible()){
            try {
                panel.repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {}

        }
    }
}
