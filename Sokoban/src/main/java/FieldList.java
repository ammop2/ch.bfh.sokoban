package main.java;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Pascal on 06.10.2015.
 *
 * host for fields
 */
public class FieldList {

    private Field[][] fields;
    private ArrayList<Field> targets;
    private Field avatar;
    private  Map map;
    public FieldList(Map map)
    {
        targets = new ArrayList<Field>();
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
                    case 0 : fields[y][x] = new Field(x, y); fields[y][x].setIsBlank(true); break;
                    case 1 : fields[y][x] = new Field(x, y); fields[y][x].setIsStone(true);  break;
                    case 2 : fields[y][x] = avatar = new Field(x, y); fields[y][x].setIsAvatar(true); break;
                    case 3 : fields[y][x] = new Field(x, y);fields[y][x].setIsTarget(true); targets.add(fields[y][x]); break;
                    case 4 : fields[y][x] = new Field(x, y);fields[y][x].setIsKey(true); break;
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

    private boolean won()
    {
        for(Field target : targets)
        {
            if(!target.isKey()) return false;
        }
        return true;
    }

    public boolean moveField(Field a, Graphics g, Direction direction) {
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
                targetY += 1;
                break;
            case RIGHT:
                targetX += 1;
                break;
            default:
                break;
        }
        Field b = fields[a.getYPos() + targetY][a.getXPos() + targetX];

        if(b.isStone()) return false;

        if(a.isAvatar() && b.isKey())
        {
            Field c = fields[a.getYPos() + targetY * 2][a.getXPos() + targetX * 2];
            if(c.isStone() || c.isKey()) return false;
            if(c.isTarget())
            {
                if(b.isTarget())
                {
                  c.setIsKey(true);
                    b.setIsKey(false);
                    b.setIsAvatar(true);
                    a.setIsAvatar(false);
                }
                else
                {
                    c.setIsKey(true);
                    b.setIsKey(false);
                    b.setIsAvatar(true);
                    a.setIsAvatar(false);
                    a.setIsBlank(b.isBlank());
                }
            } else if(c.isBlank())
            {
                c.setIsKey(true);
                b.setIsKey(false);
                b.setIsAvatar(true);
                a.setIsBlank(true);
                a.setIsAvatar(false);
            }
            avatar = b;
            c.Render(g);
            if(won())
            {
                System.out.println("fertig?!");
            }
        }
        else if(a.isAvatar() && b.isTarget())
        {
            b.setIsAvatar(true);
            a.setIsAvatar(false);
            avatar = b;
        }
        else if(a.isAvatar() && b.isBlank())
        {
            if(!a.isTarget()) a.setIsBlank(true);
            a.setIsAvatar(false);
            b.setIsAvatar(true);
            avatar = b;
        }


        a.Render(g);
        b.Render(g);
        return true;
    }
}
