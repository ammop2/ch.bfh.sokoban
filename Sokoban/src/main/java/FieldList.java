package main.java;

import com.sun.javafx.font.FontFactory;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Pascal on 06.10.2015.
 * <p>
 * host for fields
 */
public class FieldList {


    private Field[][] fields;
    private ArrayList<Field> targets;
    private Field avatar;
    private Map map;
    private ArrayList<Direction> moves = new ArrayList<Direction>();
    private ArrayList<Boolean> pushes = new ArrayList<Boolean>();
    private boolean stopGame;


    public FieldList(Map map) {
        targets = new ArrayList<Field>();
        this.map = map;
    }

    public void reversePlay(Graphics g) {

        if (moves.size() > 1) {
            System.out.println("reverse game");
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

    public boolean reverseMovePlayer(Graphics g, Direction direction, boolean push) {
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
        Field a = this.avatar;
        Field b = fields[a.getYPos() + targetY][a.getXPos() + targetX];
        Field c = fields[a.getYPos() + targetY * -1][a.getXPos() + targetX * -1];

        if (!push) {
            a.setIsAvatar(false);
            b.setIsAvatar(true);
            this.avatar = b;
            System.out.println("notpush");
        } else {
            a.setIsAvatar(false);
            b.setIsAvatar(true);
            this.avatar = b;

            c.setIsKey(false);
            a.setIsKey(true);
            a.setIsBlank(true);


            System.out.println("push");

        }


        a.Render(g);
        b.Render(g);
        c.Render(g);
        return true;

    }

    public void draw(Graphics g) {
        fields = new Field[map.getYSize()][map.getXSize()];
        int ptr = 0;
        for (int y = 0; y < map.getYSize(); y++) {

            for (int x = 0; x < map.getXSize(); x++) {
                switch (map.getFields()[ptr]) {
                    case 0:
                        fields[y][x] = new Field(x, y);
                        fields[y][x].setIsBlank(true);
                        break;
                    case 1:
                        fields[y][x] = new Field(x, y);
                        fields[y][x].setIsStone(true);
                        break;
                    case 2:
                        fields[y][x] = avatar = new Field(x, y);
                        fields[y][x].setIsAvatar(true);
                        break;
                    case 3:
                        fields[y][x] = new Field(x, y);
                        fields[y][x].setIsTarget(true);
                        targets.add(fields[y][x]);
                        break;
                    case 4:
                        fields[y][x] = new Field(x, y);
                        fields[y][x].setIsKey(true);
                        break;
                    default:
                        break;
                }
                if (fields[y][x] != null)
                    fields[y][x].Render(g);
                ptr++;
            }
        }
    }

    public void movePlayer(Graphics g, Direction direction) {
        moveField(avatar, g, direction);
    }

    public Field[][] getFields() {
        return fields;
    }

    private boolean won() {
        for (Field target : targets) {
            if (!target.isKey()) return false;
        }
        return true;
    }

    public boolean moveField(Field a, Graphics g, Direction direction) {
        if(stopGame) return false;
        boolean push = false;
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

        if (b.isStone()) return false;

        if (a.isAvatar() && b.isKey()) {
            Field c = fields[a.getYPos() + targetY * 2][a.getXPos() + targetX * 2];
            if (c.isStone() || c.isKey()) return false;
            if (c.isTarget()) {
                if (b.isTarget()) {
                    c.setIsKey(true);
                    b.setIsKey(false);
                    b.setIsAvatar(true);
                    a.setIsAvatar(false);
                    push = true;
                } else {
                    c.setIsKey(true);
                    b.setIsKey(false);
                    b.setIsAvatar(true);
                    a.setIsAvatar(false);
                    a.setIsBlank(b.isBlank());
                    push = true;
                }
            } else if (c.isBlank()) {
                c.setIsKey(true);
                b.setIsKey(false);
                b.setIsAvatar(true);
                a.setIsBlank(true);
                a.setIsAvatar(false);
                push = true;
            }
            avatar = b;
            c.Render(g);
            if (won()) {
                Font font = new Font("Serif", Font.PLAIN, 36);
                g.setFont(font);
                g.setColor(Color.DARK_GRAY);
                g.drawString("YOU WON", 20, 40);
                stopGame=true;

            }
        } else if (a.isAvatar() && b.isTarget()) {
            b.setIsAvatar(true);
            a.setIsAvatar(false);
            avatar = b;
        } else if (a.isAvatar() && b.isBlank()) {
            if (!a.isTarget()) a.setIsBlank(true);
            a.setIsAvatar(false);
            b.setIsAvatar(true);
            avatar = b;
        }


        a.Render(g);
        b.Render(g);
        this.moves.add(direction);
        this.pushes.add(push);
        return true;

    }

    private synchronized boolean checkNeighbours(int x, int y, Field target) {
        if (x < 0 || x >= map.getXSize()) return false;
        if (y < 0 || y >= map.getYSize()) return false;

        Field cField = fields[y][x];


        if (cField.isVisited()) return false;
        cField.setIsVisited(true);
        if (target == cField) {
            return true;
        }

        if (fields[y][x - 1].isBlank()) {
            if (checkNeighbours(x - 1, y, target)) return true;
        }

        if (fields[y][x + 1].isBlank()) {
            if (checkNeighbours(x + 1, y, target)) return true;
        }

        if (fields[y - 1][x].isBlank()) {
            if (checkNeighbours(x, y - 1, target)) return true;
        }
        if (fields[y + 1][x].isBlank()) {
            if (checkNeighbours(x, y + 1, target)) return true;
        }


        return false;
    }

    public void findWay(Graphics g, int targetX, int targetY) {
        Field fTarget = fields[targetY][targetX];
        if (!fTarget.isBlank()) return;
        int x = avatar.getX() / Field.ElementWidth;
        int y = avatar.getY() / Field.ElementHeight;


        if (checkNeighbours(x, y, fTarget)) {
            fTarget.setIsBlank(false);
            fTarget.setIsAvatar(true);
            fields[y][x].setIsAvatar(false);
            fields[y][x].setIsBlank(true);
            avatar = fTarget;
            fTarget.Render(g);
            fields[y][x].Render(g);
        }

        for(int i=0;i<fields.length;i++){
            for(int j=0; j<fields[i].length;j++){
                fields[i][j].setIsVisited(false);
            }
        }
    }


    public void setField(Graphics g, int x, int y, boolean drawingAvatar, boolean drawingStone, boolean drawingTarget, boolean drawingBlank, boolean drawingKey) {
        Field fieldMod = fields[y][x];
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
        fieldMod.Render(g);
    }
}
