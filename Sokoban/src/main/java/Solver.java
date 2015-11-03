package main.java;


import javafx.application.Platform;
import javafx.concurrent.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Pascal on 29.10.2015.
 */

class SolverContainer extends JFrame implements Runnable, ActionListener {

    private  Map map;
    public SolverContainer(Map map)
    {
        this.map = map;
    }

    private JLabel lbl;

    @Override
    public void run() {
        setTitle("Sokoban Game - Sokoban");
        setSize(400, 400);
        //GamePanelSolver panel = new GamePanelSolver(this.MyController);

        lbl = new JLabel("display");

        lbl.setSize(90, 25);
        add(lbl);
        //add(panel);
        setVisible(true);
        displayPath();
    }
    private void displayPath()
    {
        System.out.print("test");
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("test");
                            lbl.setText("bla" + finalI);
                        }
                    });
                    i++;
                    Thread.sleep(1000);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("hi");
       this.removeAll();
    }
}
class GamePanelSolver extends GamePanel {
    private ArrayList<Coordinate> path;
    public GamePanelSolver(Map map)
    {
        super(map);
    }

    public void setPath(ArrayList<Coordinate> path) {
        this.path = path;
    }

    private void displayPath(Graphics g) throws InterruptedException {
        FieldList fl = super.getFieldList();
        for(int i = 0; i < path.size(); i++)
        {
            System.out.println(path.size() + "" + i + "/" + (i + 1));
            int x = path.get(i).getX();
            System.out.print(x);
            int xDiff = path.get(i).getX() -path.get(i+ 1).getX();
            int yDiff = path.get(i).getY() - path.get(i + 1).getY();
            Direction d = Direction.TOP;
            if(xDiff > 0) d = Direction.LEFT;
            if(xDiff < 0) d = Direction.RIGHT;
            if(yDiff > 0) d = Direction.BOTTOM;
           //fl.moveField(fl.getField(path.get(i).getX(), path.get(i).getY()), g, d);
            Thread.sleep(500);
            System.out.println("print next step");
        }
    }
    private void moveField(int xOld, int xNew, int yOld, int yNew)
    {
    }
    private boolean init = false;
    @Override
    public void paintComponent(Graphics g) {
        if(!init) {
            init = true;
            super.initGroundField(g);
        }
        else
        {
            try {
                displayPath(g);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}


public class Solver {

    private Map map;
    private ArrayList<Coordinate> targets;
    private ArrayList<Coordinate> keys;
    private ArrayList<Coordinate> path;
    private ArrayList<Coordinate> history;

    private Coordinate player;

    public Solver(Map map)
    {
        this.map = map;
        this.keys = new ArrayList<>();
        this.targets = new ArrayList<>();
        this.history = new ArrayList<>();
        this.path = new ArrayList<>();
    }

    public ArrayList<Coordinate> Solve(){

        System.out.println("try to solve a MyController");
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
        ChangeItem[] res = new ChangeItem[history.size()];
        return path;
    }
    private void displaySolution()
    {
        SolverContainer solverContainer = new SolverContainer(this.map);
        solverContainer.run();
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
            path.add(new Coordinate(x, y));
            return true;
        }
        this.history.add(new Coordinate(x, y));
        // 1. Go to Top
        if(bruteforce(x, y -1))
        {
            System.out.println("x:" + x + " y:" + y);
            path.add(new Coordinate(x, y));
            return true;
        }
        // 2 Go to Bottom
        if(bruteforce(x, y +1))
        {
            System.out.println("x:" + x + " y:" + y);
            path.add(new Coordinate(x, y));
            return true;
        }
        // 3. go Left
        if(bruteforce(x -1, y)) {
            System.out.println("x:" + x + " y:" + y);
            path.add(new Coordinate(x, y));
            return true;
        }

        // 4. go Right
        if(bruteforce(x + 1, y)) {
            System.out.println("x:" + x + " y:" + y);
            path.add(new Coordinate(x, y));
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
