package my.solver.java;

import main.java.Direction;

import java.util.List;

/**
 * Created by Pascal on 06.11.2015.
 */
public class Way {
    private List<Direction> route;
    private List<Move> moves;

    public Way(List<Direction> route)
    {
        this.route = route;
    }

    public boolean checkRoute()
    {
        int x = Solver.getMinimalField().getPlayerXPos();
        int y = Solver.getMinimalField().getPlayerYPos();
        int[][] field = Solver.getMinimalField().getMinimalField();
        for(Direction d : route)
        {
            Move m = new Move(x, y);
            if(!m.makeMove(d, field))
                return false;
            field = m.getPlayGround();
            x = m.getxPos();
            y = m.getyPos();

            if(m.isSolved())
                return true;

        }
        return true;
    }
}
