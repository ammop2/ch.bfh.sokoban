package main.java;

/**
 * Created by Pascal on 01.11.2015.
 */
public class ChangeItem {
    private int x;
    private int y;
    private FieldTyp typOld;
    private FieldTyp typNew;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FieldTyp getTypOld() {
        return typOld;
    }

    public FieldTyp getTypNew() {
        return typNew;
    }

    public ChangeItem(int x, int y, FieldTyp typOld, FieldTyp typNew)
    {
        this.x = x;
        this.y = y;
        this.typNew = typNew;
        this.typOld = typOld;
    }
}
