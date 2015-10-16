package main.java;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 18.09.2015.
 * Rewritten by Gabriel on 09.10.2015
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    /**
     * Region Properties
     */
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean reverse;
    private boolean groundFieldDrawed = false;

    private Map map;
    private FieldList fieldList;

    public GamePanel(Map map) {
        this.map=map;
        fieldList = new FieldList(map);
        this.setPreferredSize(new Dimension(map.getXSize() * Field.ElementHeight , map.getYSize()  * Field.ElementHeight));


    }

    private void initGroundField(Graphics g)
    {
        this.fieldList.draw(g);
    }

    @Override
    public void paintComponent(Graphics g){
        if(!groundFieldDrawed) {
            super.paintComponent(g);
            initGroundField(g);
            groundFieldDrawed = true;
        }
        else
        {
            if(!up && !down && !left && !right &&!reverse)
            {
                //abort if not a ground field.
                return;
            }
            if(up)
            {
                this.fieldList.movePlayer(g, Direction.TOP);
            }
            if(down)
            {
                this.fieldList.movePlayer(g, Direction.BOTTOM);
            }
            if(left)
            {
                this.fieldList.movePlayer(g, Direction.LEFT);
            }
            if(right)
            {
                this.fieldList.movePlayer(g, Direction.RIGHT);
            }
            if(reverse){
                this.fieldList.reversePlay(g);
            }
            reverse = up = down = left = right = false;

        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
            up = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            down = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            left = true;
        }

        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            right = true;
        }

        if (e.getKeyCode()==KeyEvent.VK_Z){
            reverse=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        reverse = up = down = left = right = false;
    }
}


