package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by u216070 on 16.10.2015.
 */
public class EditPanel extends JPanel implements KeyListener, ActionListener {

    private boolean drawingBlank=false;
    private boolean drawingAvatar=false;
    private boolean drawingKey=false;
    private boolean drawingTarget=false;
    private boolean drawingStone=false;


    private boolean groundFieldDrawed = false;
    private Map originalMap;
    private FieldList fieldList;
    private Field[][] fields;
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
            }else{
                drawingAvatar = true;
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_K){
            if(drawingKey){
                drawingKey=false;
            }else{
                drawingKey = true;
            }

        }

        if(e.getKeyCode()==KeyEvent.VK_B){
            if(drawingBlank){
                drawingBlank=false;
            }else{
                drawingBlank= true;
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_T){
            if(drawingTarget){
                drawingTarget=false;
            }else{
                drawingTarget=true;
            }
        }

        if (e.getKeyCode()==KeyEvent.VK_S){
            if(drawingStone){
                drawingStone=false;
            }else{
                drawingStone=true;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //drawingAvatar = drawingBlank = drawingKey = drawingStone = drawingStone = false;
    }
}
