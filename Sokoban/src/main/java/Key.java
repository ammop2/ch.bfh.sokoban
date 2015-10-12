package main.java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 06.10.2015.
 */
public class Key extends Field {

    public Key(int xPos, int yPos) {
        super(false, xPos, yPos);
    }

    @Override
    public void Render(Graphics g) {
        try {
            URL url = getClass().getClassLoader().getResource("pics\\Viking-Female-icon.png");
            BufferedImage img = ImageIO.read(url);
            g.drawImage(img, getX(), getY(), Field.ElementWidth, Field.ElementHeight, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
