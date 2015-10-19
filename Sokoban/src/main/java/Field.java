package main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 06.10.2015.
 *
 */
public class Field {


    public boolean isTarget() {
        return isTarget;
    }

    public void setIsTarget(boolean isTarget) {
        this.isTarget = isTarget;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setIsKey(boolean isKey) {
        this.isKey = isKey;
    }

    public boolean isStone() {
        return isStone;
    }

    public void setIsStone(boolean isStone) {
        this.isStone = isStone;
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setIsBlank(boolean isBlank) {
        this.isBlank = isBlank;
    }

    public boolean isAvatar() {
        return isAvatar;
    }

    public void setIsAvatar(boolean isAvatar) {
        this.isAvatar = isAvatar;
    }

    private boolean isTarget = false;
    private boolean isKey = false;
    private boolean isStone = false;
    private boolean isBlank = false;
    private boolean isAvatar = false;


    public static int ElementWidth=40;
    public static int ElementHeight=40;

    private int xPos;
    private  int yPos;


    public Field(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }




    public int getX() {return  this.xPos * Field.ElementWidth;}
    public int getY() {return  this.yPos * Field.ElementHeight;}

    public int getXPos() {return  this.xPos;}
    public int getYPos() {return  this.yPos;}

    public void Render(Graphics g) {
        if (isTarget) {
            g.setColor(Color.YELLOW);
            g.fillRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
            g.setColor(Color.BLACK);
            g.drawRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
        }
        if (isBlank) {
            g.setColor(Color.white);
            g.fillRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
            g.setColor(Color.BLACK);
            g.drawRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
        }
        if (isStone) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
            g.setColor(Color.BLACK);
            g.drawRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
        }

        if (isKey) {
            try {
                URL url = getClass().getClassLoader().getResource("pics\\Viking-Female-icon.png");
                BufferedImage img = ImageIO.read(url);
                g.drawImage(img, getX(), getY(), Field.ElementWidth, Field.ElementHeight, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (isAvatar) {
            try {
                URL url = getClass().getClassLoader().getResource("pics\\viking-angry-icon.png");
                BufferedImage img = ImageIO.read(url);
                g.drawImage(img, getX(), getY(), Field.ElementWidth, Field.ElementHeight, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
