package main.java;

import java.awt.*;

/**
 * Created by Pascal on 06.10.2015.
 */
public class Stone extends Field {

    public Stone(int xPos, int yPos) {
        super(false, xPos, yPos);
    }

    @Override
    public void Render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), Field.ElementWidth, Field.ElementHeight);
    }
}
