package main.java;

import java.awt.*;

/**
 * Created by Pascal on 06.10.2015.
 *
 * host for fields
 */
public class FieldList {

    private Field[][] fields;
    private Field avatar;
    private  Map map;
    public FieldList(Map map)
    {
        this.map = map;
    }
    public void draw(Graphics g){
        fields = new Field[map.getYSize()][map.getXSize()];
        int ptr = 0;
        for(int y = 0; y < map.getYSize(); y++)
        {

            for(int x = 0; x < map.getXSize(); x++)
            {
                switch (map.getFields()[ptr])
                {
                    case 0 : fields[y][x] = new Blank(x, y);  break;
                    case 1 : fields[y][x] = new Stone(x, y);  break;
                    case 2 : fields[y][x] = avatar = new Avatar(x, y); break;
                    case 3 : fields[y][x] = new Target(x, y); break;
                    case 4 : fields[y][x] = new Key(x, y); break;
                    default: break;
                }
                if(fields[y][x] != null)
                    fields[y][x].Render(g);
                ptr++;
            }
        }
    }
    public void movePlayer(Graphics g, Direction direction) {
        moveField(avatar, g, direction);
    }

    public void moveField(Field a, Graphics g, Direction direction) {
        int targetX = 0;
        int targetY = 0;
        switch (direction) {
            case TOP:
                targetY -= 1;
                break;
            case LEFT:
                targetX -= 1;
                break;
            case BOTTOM:
                targetY += 0;
                break;
            case RIGHT:
                targetX += 0;
                break;
            default:
                break;
        }
        Field b = fields[a.getYPos() + targetY][a.getXPos() + targetX];
        clone(a, b);
        a.Render(g);
        b.Render(g);

    }


    private void clone(Field A, Field B)
    {
        int aX = A.getXPos();
        int aY = A.getYPos();

        int bX = B.getXPos();
        int bY = B.getYPos();

        System.out.println(aX);
        System.out.println(bX);

        A.update(bX, bY);
        B.update(aX, aY);


        System.out.println( A.getXPos());
        System.out.println(B.getXPos());

    }
}
