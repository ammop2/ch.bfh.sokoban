package my.solver.java;

import main.java.Direction;
import main.java.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pascal on 06.11.2015.
 */
public class Solver {
    private static MinimalField minimalField;
    private List<List<Direction>> routes;
    private boolean[][] vistedFields;
    private Map map;

    public static MinimalField getMinimalField() {
        return minimalField;
    }

    private void findRoute(int x, int y, List<Direction> cRoute, Direction d)
    {

        if( x < 0  || x > map.getXSize() -1 || y < 0 || y > map.getYSize() -1)
        {
            routes.add(cRoute);
            return;
        }
        if((vistedFields[y][x] == true)) return;

        vistedFields[y][x] = true;
        if(d != null)
        cRoute.add(d);

        findRoute(x - 1, y, cRoute, Direction.LEFT);
        findRoute(x +1, y, cRoute, Direction.RIGHT);
        findRoute(x, y-1, cRoute, Direction.TOP);
        findRoute(x, y+1, cRoute, Direction.BOTTOM);

    }

    public Solver(Map map)
    {
        vistedFields = new boolean[map.getYSize()][map.getXSize()];
        routes = new ArrayList<>();
        this.map = map;
        Solver.minimalField = new MinimalField(map);
    }


    public void solve()
    {
        // 1: calc all pos ways
        findRoute(Solver.getMinimalField().getPlayerXPos(), Solver.getMinimalField().getPlayerYPos(), new ArrayList<>(), null);

        // 2: check possibles ways
        for(List<Direction> lsD : routes)
        {
            Way w = new Way(lsD);
            if(w.checkRoute())
            {
                System.out.print("Way:");

                for(Direction d : lsD)
                {
                    System.out.print(""+d);
                }

                System.out.println("");
            }
        }


    }

}
