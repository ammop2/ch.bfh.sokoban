import java.awt.*;

/**
 * Created by Pascal on 20.09.2015.
 */
public class Ground extends Field {

    public Ground(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x(), y(), Field.width, Field.height);
        g.setColor(Color.BLACK);
        g.drawRect(x(), y(), Field.width, Field.height);
    }


}
