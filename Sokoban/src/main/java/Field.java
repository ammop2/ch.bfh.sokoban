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

    public int getfPtr() {
        return fPtr;
    }

    public void setfPtr(int fPtr) {
        this.fPtr = fPtr;
    }

    public int getfPtrNeighborTop() {
        return fPtrNeighborTop;
    }

    public void setfPtrNeighborTop(int fPtrNeighborTop) {
        this.fPtrNeighborTop = fPtrNeighborTop;
    }

    public int getfPtrNeighborBottom() {
        return fPtrNeighborBottom;
    }

    public void setfPtrNeighborBottom(int fPtrNeighborBottom) {
        this.fPtrNeighborBottom = fPtrNeighborBottom;
    }

    public int getfPtrNeighborLeft() {
        return fPtrNeighborLeft;
    }

    public void setfPtrNeighborLeft(int fPtrNeighborLeft) {
        this.fPtrNeighborLeft = fPtrNeighborLeft;
    }

    public int getfPtrNeighborRight() {
        return fPtrNeighborRight;
    }

    public void setfPtrNeighborRight(int fPtrNeighborRight) {
        this.fPtrNeighborRight = fPtrNeighborRight;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

}
