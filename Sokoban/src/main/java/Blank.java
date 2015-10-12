package main.java;

import java.awt.*;

/**
 * Created by Pascal on 09.10.2015.
 */
public class Blank extends Field {

    public Blank(int xPos, int yPos) {
        super(false, xPos, yPos);
    }

    @Override
    public void Render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
        g.setColor(Color.WHITE);
        g.drawRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
    }
}
