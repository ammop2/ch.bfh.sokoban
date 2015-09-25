import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Pascal on 20.09.2015.
 */
public abstract class Field extends Rectangle2D.Double {


    public static int height = 30;
    public static int width = 30;

    private  int row;
    private  int column;
    public Field(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    public int x()
    {
        return column * width;
    }
    public int y()
    {
        return row * height;
    }
    public  int getRow()
    {
        return row;
    }
    public  int getColumn()
    {
        return column;
    }
    public void setRow(int row)
    {
        this.row = row;
    }
    public void setColumn(int column)
    {
        this.column = column;
    }
    public abstract void draw(Graphics g);


}
