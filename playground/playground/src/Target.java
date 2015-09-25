import java.awt.*;

/**
 * Created by Pascal on 21.09.2015.
 */
public class Target extends Field {
    public Target(int row, int column) {
        super(row, column);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x(), y(), Field.width, Field.height);
        g.setColor(Color.BLACK);
        g.drawRect(x(), y(), Field.width, Field.height);
    }
}
