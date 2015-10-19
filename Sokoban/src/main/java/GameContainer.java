package main.java;

import javax.swing.*;

/**
 * Created by u216070 on 07.10.2015.
 */
public class GameContainer extends JFrame implements Runnable {
    private  GamePanel gpanel;
    private  EditPanel epanel;
    public GameContainer(Map map, boolean edit)
    {
        super("Sokoban");
        if(edit){
            epanel = new EditPanel(map);
            add(epanel);
            addMouseListener(epanel);
        } else {
            gpanel = new GamePanel(map);
            add(gpanel);
            addKeyListener(gpanel);
        }

        this.pack();
        setVisible(true);
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while(isVisible()){
            try {
                if(gpanel!=null){
                    gpanel.repaint();
                }else{
                    epanel.repaint();
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {}

        }
    }
}