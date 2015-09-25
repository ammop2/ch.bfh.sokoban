import java.awt.*;

/**
 * Created by Pascal on 20.09.2015.
 */
public class Stone extends Field {

    public Stone(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x(), y(), Field.width, Field.height);
        g.setColor(Color.BLACK);
        g.drawRect(x(), y(), Field.width, Field.height);
    }


}