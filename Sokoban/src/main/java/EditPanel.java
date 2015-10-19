package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by u216070 on 16.10.2015.
 */
public class EditPanel extends JPanel implements KeyListener, ActionListener, MouseListener {

    private boolean drawingBlank=false;
    private boolean drawingAvatar=false;
    private boolean drawingKey=false;
    private boolean drawingTarget=false;
    private boolean drawingStone=false;
    private int x;
    private int y;


    private boolean groundFieldDrawed = false;
    private Map originalMap;
    private FieldList fieldList;
    private Map newMap;

    public EditPanel(Map map) {

        if(map!=null){
            this.originalMap=map;
            fieldList = new FieldList(map);
            this.setPreferredSize(new Dimension(map.getXSize() * Field.ElementHeight , map.getYSize()  * Field.ElementHeight));
        } else{
            //code for creating map from scratch
        }
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
            fieldList.setField(g, x, y, drawingAvatar,drawingStone,drawingTarget,drawingBlank,drawingKey);
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
        if(e.getKeyCode()==KeyEvent.VK_A){
            if(drawingAvatar){
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingStone=false;
                drawingTarget=false;
            }else{
                drawingAvatar = true;
                drawingBlank=false;
                drawingStone=false;
                drawingTarget=false;
                drawingKey=false;
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_K){
            if(drawingKey){
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingStone=false;
                drawingTarget=false;
                System.out.println("key is off");
            }else{
                drawingKey = true;
                drawingAvatar=false;
                drawingBlank=false;
                drawingStone=false;
                drawingTarget=false;
                System.out.println("key is on");
            }

        }

        if(e.getKeyCode()==KeyEvent.VK_B){
            if(drawingBlank){
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingStone=false;
                drawingTarget=false;
            }else{
                drawingBlank= true;
                drawingAvatar=false;
                drawingKey=false;
                drawingStone=false;
                drawingTarget=false;
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_T){
            if(drawingTarget){
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingStone=false;
                drawingTarget=false;
            }else{
                drawingTarget=true;
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingStone=false;
            }
        }

        if (e.getKeyCode()==KeyEvent.VK_S){
            if(drawingStone){
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingStone=false;
                drawingTarget=false;
            }else{
                drawingStone=true;
                drawingAvatar=false;
                drawingBlank=false;
                drawingKey=false;
                drawingTarget=false;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //drawingAvatar = drawingBlank = drawingKey = drawingStone = drawingStone = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x= e.getX()/40;
        y=e.getY()/40-1;

    }

    @Override
    public void mousePressed(MouseEvent e) {
System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exited");
    }
}
