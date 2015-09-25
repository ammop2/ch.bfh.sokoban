import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pascal on 21.09.2015.
 */
public class KeyField extends Field {

    private boolean blocked = false;

    public  boolean isBlocked()
    {
        return blocked;
    }
    public  void  setBlocked(boolean blocked)
    {
        this.blocked = blocked;
    }

    public KeyField(int row, int column) {
        super(row, column);
    }


    @Override
    public void draw(Graphics g) {
        try {
            URL url = getClass().getClassLoader().getResource("pics\\Viking-Female-icon.png");
            BufferedImage img = ImageIO.read(url);
            g.drawImage(img, x(), y(), Field.width, Field.height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
