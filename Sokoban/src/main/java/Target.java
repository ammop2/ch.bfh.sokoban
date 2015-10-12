package main.java;

import java.awt.*;

/**
 * Created by Gabriel on 06.10.2015.
 */
public class Target extends Field {

    public Target(int xPos, int yPos) {
        super(false, xPos, yPos);
    }

    @Override
    public void Render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
        g.setColor(Color.YELLOW);
        g.drawRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
    }
}
