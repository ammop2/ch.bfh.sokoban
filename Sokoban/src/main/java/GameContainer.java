package main.java;

import javax.swing.*;

/**
 * Created by u216070 on 07.10.2015.
 */
public class GameContainer extends JFrame implements Runnable {
    private  GamePanel panel;
    public GameContainer(Map map)
    {
        super("Sokoban");
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