package main.java;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 18.09.2015.
 * Rewritten by Gabriel on 09.10.2015
 */
public class GamePanel extends JPanel implements MouseListener, KeyListener, ActionListener {

    /**
     * Region Properties
     */
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean reverse;
    private boolean groundFieldDrawed = false;
    private int x;
    private int y;
    private boolean find;

    private Map map;
    private FieldList fieldList;

    public FieldList getFieldList() {
        return fieldList;
    }

    public GamePanel(Map map) {
        this.map=map;
        fieldList = new FieldList(map);
        this.setPreferredSize(new Dimension(map.getXSize() * Field.ElementHeight , map.getYSize()  * Field.ElementHeight));


    }

    public void initGroundField(Graphics g)
    {
        //this.fieldList.draw(g);
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
            if (find){
                //fieldList.findWay(g,x,y);
                find=false;
            }

            if(!up && !down && !left && !right &&!reverse)
            {
                //abort if not a ground field.
                return;
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

    @Override
    public void mouseClicked(MouseEvent e) {

        this.x = e.getX() /Field.ElementWidth;
        this.y = e.getY() /Field.ElementHeight-1;
        find=true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


