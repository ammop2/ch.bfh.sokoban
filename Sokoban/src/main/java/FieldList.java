package main.java;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Pascal on 06.10.2015.
 * <p>
 * host for fields
 */
public class FieldList {


    private FieldTyp[][] fields;
    private FieldTyp[][] originalFields;

    private ArrayList<Field> targets;
    private Player player;


    private int targetCount;




    private Map map;
    private ArrayList<Field> changes = new ArrayList<Field>();
    private ArrayList<Direction> moves = new ArrayList<Direction>();
    private ArrayList<Boolean> pushes = new ArrayList<Boolean>();


    private boolean stopGame;


    public FieldList(Map map) {
        targets = new ArrayList<Field>();
        this.map = map;
        init();
    }

    public void reversePlay(Graphics g) {

        if (moves.size() > 1) {
            Direction dir = moves.get(moves.size() - 1);
            boolean push = pushes.get(pushes.size() - 1);

            if (dir == Direction.TOP) {
                reverseMovePlayer(g, Direction.BOTTOM, push);
            }
            if (dir == Direction.LEFT) {
                reverseMovePlayer(g, Direction.RIGHT, push);
            }
            if (dir == Direction.RIGHT) {
                reverseMovePlayer(g, Direction.LEFT, push);
            }
            if (dir == Direction.BOTTOM) {
                reverseMovePlayer(g, Direction.TOP, push);
            }
            moves.remove(moves.size() - 1);
            pushes.remove(pushes.size() - 1);

        }
    }

    public void reverseEdit(Graphics g) {
        if (changes.size() > 0) {
            //    Field oldField = changes.get(changes.size() - 1);
            //    fields[oldField.getYPos()][oldField.getXPos()].setIsStone(oldField.isStone());
            //fields[oldField.getYPos()][oldField.getXPos()].setIsAvatar(oldField.isAvatar());
            //     fields[oldField.getYPos()][oldField.getXPos()].setIsBlank(oldField.isBlank());
            //    fields[oldField.getYPos()][oldField.getXPos()].setIsTarget(oldField.isTarget());
            //    fields[oldField.getYPos()][oldField.getXPos()].setIsKey(oldField.isKey());

            //      fields[oldField.getYPos()][oldField.getXPos()].Render(g);
            //      changes.remove(changes.size() - 1);
        }


    }

    public boolean reverseMovePlayer(Graphics g, Direction direction, boolean push) {
/*        int targetX = 0;
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
        Field a = this.avatar;
        //     Field b = fields[a.getYPos() + targetY][a.getXPos() + targetX];
        //   Field c = fields[a.getYPos() + targetY * -1][a.getXPos() + targetX * -1];

        if (!push) {
            a.setIsAvatar(false);
            b.setIsAvatar(true);
            this.avatar = b;
        } else {
            boolean targetCorrect = (fields[avatar.getYPos() - 1][avatar.getXPos()].isTarget() && fields[avatar.getYPos() + 1][avatar.getXPos()].isTarget()) || (fields[avatar.getYPos()][avatar.getXPos() - 1].isTarget() && fields[avatar.getYPos()][avatar.getXPos() + 1].isTarget());
            a.setIsAvatar(false);
            b.setIsAvatar(true);
            this.avatar = b;

            c.setIsKey(false);
            a.setIsKey(true);
            a.setIsBlank(true);

            if (targetCorrect) {
                a.setIsTarget(true);
                a.setIsBlank(false);
            }
        }

        a.Render(g);
        b.Render(g);
        c.Render(g);*/
        return true;
    }

    public void setPlayer(int x, int y)
    {
        player.setY(y);
        player.setX(x);
    }

    private void init()
    {
        originalFields = new FieldTyp[map.getYSize()][map.getXSize()];
        int ptr = 0;

        for (int y = 0; y < map.getYSize(); y++) {
            for (int x = 0; x < map.getXSize(); x++) {
                switch (map.getFields()[ptr]) {
                    case 0:
                        originalFields[y][x] = FieldTyp.BLANK;
                        break;
                    case 1:
                        originalFields[y][x] = FieldTyp.WALL;
                        break;
                    case 2:
                        originalFields[y][x] = FieldTyp.PLAYER;
                        player = new Player(x , y);
                        break;
                    case 3:
                        originalFields[y][x] = FieldTyp.TARGET_UNLOCKED;
                        targetCount++;
                        break;
                    case 4:
                        originalFields[y][x] = FieldTyp.KEY;
                        break;
                    default:
                        break;
                }
                ptr++;
            }
        }
        for (int y = 0; y < map.getYSize(); y++) {
            for (int x = 0; x < map.getXSize(); x++) {
                mapPlaygroundClean(y, x);
            }
        }
        cloneFields();
    }
    private void cloneFields()
    {
        fields = new FieldTyp[ map.getYSize()][ map.getXSize()];
        for (int y = 0; y < map.getYSize(); y++) {
            for (int x = 0; x < map.getXSize(); x++) {
                fields[y][x] = originalFields[y][x];
            }
        }
    }

    private void mapPlaygroundClean(int y, int x)
    {
        if(!(originalFields[y][x] == FieldTyp.BLANK)) return;

        if(y > 0 && x > 0 && y < map.getYSize() && x < map.getXSize())
        {
            //top
            int wCount = 0;
            for(int t = 0; t < y; t++)
            {
                if(originalFields[t][x] == FieldTyp.WALL)
                {
                    wCount++;
                    break;
                }
            }
            //bottom
            if(wCount == 0) return;
            for(int b = y; b < map.getYSize(); b++)
            {
                if(originalFields[b][x] == FieldTyp.WALL)
                {
                    wCount++;
                    break;
                }
            }
            if(wCount < 2) return;
            for(int l = 0; l < x; l++)
            {
                if(originalFields[y][l] == FieldTyp.WALL)
                {
                    wCount++;
                    break;
                }
            }
            if(wCount < 3) return;
            for(int r = x; r < map.getXSize(); r++)
            {
                if(originalFields[y][r] == FieldTyp.WALL)
                {
                    wCount++;
                    break;
                }
            }
            if(wCount < 4) return;
            originalFields[y][x] = FieldTyp.PLAYGROUND;
        }
    }



    public ChangeItem[] movePlayer(Direction direction) {
        ChangeItem[] res = moveField(player.getX(), player.getY(), direction);
        if(res != null) {
            player.setX(getDirectionX(direction) + player.getX());
            player.setY(getDirectionY(direction) + player.getY());
        }

        return  res;
    }

    private boolean won() {
        for (Field target : targets) {
            if (!target.isKey()) return false;
        }
        return true;
    }

    private FieldTyp getNeighbourFieldByDirection(int x, int y, Direction direction)
    {
        return fields[y + getDirectionY(direction)][x + getDirectionX(direction)];
    }

    private FieldTyp getNeighbourNeighbourFieldByDirection(int x, int y, Direction direction)
    {
        return fields[y + getDirectionY(direction) * 2][x + getDirectionX(direction) * 2];
    }

    private int getDirectionY(Direction direction)
    {
        int targetY = 0;
        switch (direction) {
            case TOP:
                targetY -= 1;
                break;
            case BOTTOM:
                targetY += 1;
                break;
            default:
                break;
        }
        return targetY;
    }

    private int getDirectionX(Direction direction)
    {
        int targetX = 0;

        switch (direction) {
            case LEFT:
                targetX -= 1;
                break;
            case RIGHT:
                targetX += 1;
                break;
            default:
                break;
        }
        return targetX;
    }


    public boolean win()
    {
        return targetCount == 0;
    }


    public ChangeItem[] moveField(int startX, int startY, Direction direction) {
        ChangeItem[] result = null;

        FieldTyp a = fields[startY][startX];
        FieldTyp b = getNeighbourFieldByDirection(startX, startY, direction);

        if (a != FieldTyp.PLAYER || b == FieldTyp.WALL) return result;

        FieldTyp c = getNeighbourNeighbourFieldByDirection(startX, startY, direction);
        if(b == FieldTyp.PLAYGROUND || b == FieldTyp.TARGET_UNLOCKED)
        {
            result = new ChangeItem[]{new ChangeItem(startX, startY, FieldTyp.PLAYER, originalFields[startY][startX] == FieldTyp.TARGET_UNLOCKED ? FieldTyp.TARGET_UNLOCKED : FieldTyp.PLAYGROUND), new ChangeItem(startX + getDirectionX(direction), startY + getDirectionY(direction), FieldTyp.PLAYGROUND, FieldTyp.PLAYER)};
        }
        else {
            if (b == FieldTyp.KEY && c == FieldTyp.TARGET_UNLOCKED) {
                result = new ChangeItem[]{new ChangeItem(startX, startY, FieldTyp.PLAYER, originalFields[startY][startX] == FieldTyp.TARGET_UNLOCKED ? FieldTyp.TARGET_UNLOCKED : FieldTyp.PLAYGROUND),
                        new ChangeItem(startX + getDirectionX(direction), startY + getDirectionY(direction), FieldTyp.KEY, FieldTyp.PLAYER),
                        new ChangeItem(startX + getDirectionX(direction)*2, startY + getDirectionY(direction)*2, FieldTyp.TARGET_UNLOCKED, FieldTyp.TARGET_LOCKED)};
                targetCount--;
            }
            else if (b == FieldTyp.KEY && c == FieldTyp.PLAYGROUND) {
                result = new ChangeItem[]{new ChangeItem(startX, startY, FieldTyp.PLAYER, originalFields[startY][startX] == FieldTyp.TARGET_UNLOCKED ? FieldTyp.TARGET_UNLOCKED : FieldTyp.PLAYGROUND),
                        new ChangeItem(startX + getDirectionX(direction), startY + getDirectionY(direction), FieldTyp.KEY, FieldTyp.PLAYER),
                        new ChangeItem(startX + getDirectionX(direction)*2, startY + getDirectionY(direction)*2, FieldTyp.PLAYGROUND, FieldTyp.KEY)};
            }
            else if (b == FieldTyp.TARGET_LOCKED && c == FieldTyp.TARGET_UNLOCKED) {
                result = new ChangeItem[]{new ChangeItem(startX, startY, FieldTyp.PLAYER, originalFields[startY][startX] == FieldTyp.TARGET_UNLOCKED ? FieldTyp.TARGET_UNLOCKED : FieldTyp.PLAYGROUND),
                        new ChangeItem(startX + getDirectionX(direction), startY + getDirectionY(direction), FieldTyp.TARGET_LOCKED, FieldTyp.PLAYER),
                        new ChangeItem(startX + getDirectionX(direction)*2, startY + getDirectionY(direction)*2, FieldTyp.TARGET_UNLOCKED, FieldTyp.TARGET_LOCKED)};
            }
        }

        if(result != null)
        {
            for(ChangeItem cItem : result)
            {
                fields[cItem.getY()][cItem.getX()] = cItem.getTypNew();
            }
        }
        return result;

    }

    public void undo(ChangeItem[] changes)
    {
        for(ChangeItem cItem : changes)
        {
            fields[cItem.getY()][cItem.getX()] = cItem.getTypOld();
        }
    }




    private  boolean checkNeighbours(int x, int y, int targetX, int targetY, ArrayList<FieldTyp> visitedFields) {
        if (x < 0 || x >= map.getXSize()) return false;
        if (y < 0 || y >= map.getYSize()) return false;

        if(x == targetX && y == targetY)
        {
            return true;
        }

        FieldTyp cField = fields[y][x];

        if(visitedFields.contains(cField))
        {
            return false;
        }

        visitedFields.add(cField);

        if (fields[y][x - 1] == FieldTyp.PLAYGROUND) {
            System.out.println("bla");
            if(checkNeighbours(x - 1, y, targetX, targetY, visitedFields))
            {
                return true;
            }

        }

        if (fields[y][x + 1] == FieldTyp.PLAYGROUND) {
            if(checkNeighbours(x + 1, y, targetX, targetY, visitedFields))
            {
                return true;
            }
        }

        if (fields[y - 1][x] == FieldTyp.PLAYGROUND) {
            if(checkNeighbours(x, y -1, targetX, targetY, visitedFields))
            {
                return true;
            }
        }
        if (fields[y + 1][x] == FieldTyp.PLAYGROUND) {
            if(checkNeighbours(x, y + 1, targetX, targetY, visitedFields))
            {
                return true;
            }
        }

        return false;
    }

    public ChangeItem[] findWay(int targetX, int targetY) {
       FieldTyp tTyp = fields[targetY][targetX];
        if (tTyp != FieldTyp.PLAYGROUND) return null;

        if(checkNeighbours(player.getX(), player.getY(), targetX, targetY, new ArrayList<>()))
        {
            ChangeItem[] res = new ChangeItem[]{new ChangeItem(player.getX(), player.getY(), FieldTyp.PLAYER, FieldTyp.PLAYGROUND), new ChangeItem(targetX, targetY, FieldTyp.PLAYGROUND, FieldTyp.PLAYER)};
            player.setX(targetX);
            player.setY(targetY);
            return res;
        }
        return null;
    }


    public void setField(Graphics g, int x, int y, boolean drawingAvatar, boolean drawingStone, boolean drawingTarget, boolean drawingBlank, boolean drawingKey) {
       /* Field fieldMod = fields[y][x];

        //save a copy of the field to revert it later
        Field oldField = new Field(x, y);
        oldField.setIsBlank(fieldMod.isBlank());
        oldField.setIsTarget(fieldMod.isTarget());
        oldField.setIsAvatar(fieldMod.isAvatar());
        oldField.setIsKey(fieldMod.isKey());
        oldField.setIsStone(fieldMod.isStone());
        changes.add(oldField);

        //check only one avatar can be in the game
        Field findAvatar = null;
        if (drawingAvatar) {

            for (int i = 0; i < fields.length; i++) {
                for (int j = 0; j < fields[i].length; j++) {
                    if (fields[i][j].isAvatar()) findAvatar = fields[i][j];
                }
            }
            if (findAvatar != null) {
                findAvatar.setIsAvatar(false);
                findAvatar.setIsBlank(true);
                findAvatar.Render(g);
            }

            fieldMod.setIsAvatar(true);
            fieldMod.setIsBlank(false);
            fieldMod.setIsKey(false);
            fieldMod.setIsTarget(false);
            fieldMod.setIsStone(false);


        } else if (drawingStone) {
            //Render the Blank, so you don't have residue colors
            fieldMod.setIsBlank(true);
            fieldMod.Render(g);

            fieldMod.setIsAvatar(false);
            fieldMod.setIsBlank(false);
            fieldMod.setIsKey(false);
            fieldMod.setIsTarget(false);
            fieldMod.setIsStone(true);
        } else if (drawingTarget) {
            //Render the Blank, so you don't have residue colors
            fieldMod.setIsBlank(true);
            fieldMod.Render(g);

            fieldMod.setIsAvatar(false);
            fieldMod.setIsBlank(false);
            fieldMod.setIsKey(false);
            fieldMod.setIsTarget(true);
            fieldMod.setIsStone(false);
        } else if (drawingBlank) {
            fieldMod.setIsAvatar(false);
            fieldMod.setIsBlank(true);
            fieldMod.setIsKey(false);
            fieldMod.setIsTarget(false);
            fieldMod.setIsStone(false);

        } else if (drawingKey) {
            //Render the Blank, so you don't have residue colors
            fieldMod.setIsBlank(true);
            fieldMod.Render(g);

            fieldMod.setIsAvatar(false);
            fieldMod.setIsBlank(false);
            fieldMod.setIsKey(true);
            fieldMod.setIsTarget(false);
            fieldMod.setIsStone(false);
        }
        fieldMod.Render(g);*/
    }
    public FieldTyp getField(int x, int y)
    {
        return fields[y][x];
    }
}
