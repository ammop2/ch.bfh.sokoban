package main.java;

import java.awt.*;

/**
 * Created by Pascal on 06.10.2015.
 */
public class Stone extends Field {

    public Stone(int fPtr, int fPtrNeighborTop, int fPtrNeighborBottom, int fPtrNeighborLeft, int fPtrNeighborRight) {
        super(fPtr, fPtrNeighborTop, fPtrNeighborBottom, fPtrNeighborLeft, fPtrNeighborRight, false);
    }

    @Override
    public void Render(Graphics g) {

    }
}