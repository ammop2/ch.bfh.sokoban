package main.java;

import java.awt.*;

/**
 * Created by Pascal on 06.10.2015.
 *
 */
public abstract class Field {


    public static int ElementWidth=40;
    public static int ElementHeight=40;

    private int xPos;
    private  int yPos;

    private boolean accessible;

    public Field(boolean accessible, int xPos, int yPos)
    {
        this.accessible = accessible;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getX() {return  this.xPos * Field.ElementWidth;}
    public int getY() {return  this.yPos * Field.ElementHeight;}

    public int getXPos() {return  this.xPos;}
    public int getYPos() {return  this.yPos;}

    public abstract void Render(Graphics g);


    public void update(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
