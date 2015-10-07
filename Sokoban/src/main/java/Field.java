package main.java;

import java.awt.*;

/**
 * Created by Pascal on 06.10.2015.
 *
 */
public abstract class Field {

    private int fPtr;
    private int fPtrNeighborTop;
    private int fPtrNeighborBottom;
    private int fPtrNeighborLeft;
    private int fPtrNeighborRight;
    private boolean accessible;

    public Field(int fPtr, int fPtrNeighborTop, int fPtrNeighborBottom, int fPtrNeighborLeft, int fPtrNeighborRight, boolean accessible)
    {
        this.fPtr = fPtr;
        this.fPtrNeighborTop = fPtrNeighborTop;
        this.fPtrNeighborBottom = fPtrNeighborBottom;
        this.fPtrNeighborLeft = fPtrNeighborLeft;
        this.fPtrNeighborRight = fPtrNeighborRight;
    }

    public abstract void Render(Graphics g);

}
