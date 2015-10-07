package main.java;

import java.awt.*;

/**
 * Created by Pascal on 06.10.2015.
 */
public class Ground extends Field {

    public Ground(int fPtr, int fPtrNeighborTop, int fPtrNeighborBottom, int fPtrNeighborLeft, int fPtrNeighborRight, boolean accessible) {
        super(fPtr, fPtrNeighborTop, fPtrNeighborBottom, fPtrNeighborLeft, fPtrNeighborRight, accessible);
    }

    @Override
    public void Render(Graphics g) {

    }
}
