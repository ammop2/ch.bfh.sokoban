import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 20.09.2015.
 */
public class Player extends Field {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        try {
            URL url = getClass().getClassLoader().getResource("pics\\viking-angry-icon.png");
            BufferedImage img = ImageIO.read(url);
            g.drawImage(img, x(), y(), Field.width, Field.height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}