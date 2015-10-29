package main.java;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Created by Pascal on 29.10.2015.
 */

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class Solver {

    private Map map;
    private ArrayList<Coordinate> targets;
    private ArrayList<Coordinate> keys;
    private ArrayList<Coordinate> history;

    private Coordinate player;

    public Solver(Map map)
    {
        this.map = map;
        this.keys = new ArrayList<>();
        this.targets = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public void Solve(){

        System.out.println("try to solve a map");
        System.out.println("X Size, Y Size" + map.getXSize() + "," + map.getYSize());
        findTargetsAndKeys();

        for(Coordinate t : targets){
            System.out.println("target, x:" + t.getX() + " y:" + t.getY());
        }

        for(Coordinate t : keys){
            System.out.println("key, x:" + t.getX() + " y:" + t.getY());
        }

        System.out.println("bruteforce!");
        for(Coordinate k : keys){
          bruteforce(k.getX(), k.getY());
        }


    }

    private boolean bruteforce(int x, int y)
    {
        if( x < 0 || y < 0 || x >= this.map.getXSize() || y >= this.map.getYSize())
        {
            return false;
        }
        for(Coordinate h : history)
        {
            if(h.getX() == x && h.getY() == y)
            {
                return false;
            }
        }

        if(map.getFields()[y * map.getXSize() + x] == 3)
        {
            System.out.println("juhu x:" + x + " y:" + y);
            return true;
        }
        this.history.add(new Coordinate(x, y));
        // 1. Go to Top
        if(bruteforce(x, y -1))
        {
            System.out.println("x:" + x + " y:" + y);
            return true;
        }
        // 2 Go to Bottom
        if(bruteforce(x, y +1))
        {
            System.out.println("x:" + x + " y:" + y);
            return true;
        }
        // 3. go Left
        if(bruteforce(x -1, y)) {
            System.out.println("x:" + x + " y:" + y);
            return true;
        }

        // 4. go Right
        if(bruteforce(x + 1, y)) {
            System.out.println("x:" + x + " y:" + y);
            return true;
        }
        return false;
    }

    private void findTargetsAndKeys()
    {
        for(int y = 0; y < map.getYSize(); y ++)
        {
            for (int x = 0; x < map.getXSize(); x++) {
                int f =map.getFields()[y * map.getXSize() + x];
                if(f == 3) targets.add(new Coordinate(x, y));
                else if(f == 4) keys.add(new Coordinate(x, y));
                else if(f == 1) history.add(new Coordinate(x, y));
                else if(f == 2) player = new Coordinate(x, y);
            }
        }
    }


}
