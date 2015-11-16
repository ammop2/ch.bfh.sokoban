package solver.java;

import gui.controller.Solver;
import gui.java.Sokoban;
import javafx.application.Platform;
import javafx.concurrent.Task;
import main.java.*;
import main.java.Direction;

/**
 * Created by Pascal on 03.11.2015.
 */
public class MySolver {
    private Solver solver;

    public MySolver(Solver solver){
        this.solver = solver;
    }

    int pPosX;
    int pPosY;

    private char[] cmds;
    private int ptr = 0;
    private int i = 0;

    private void move()
    {
        char c = cmds[ptr];
        main.java.Direction d = null;
        switch(c)
        {
            case 'u':
                d = Direction.BOTTOM;
                break;
            case 'r':
                d = Direction.RIGHT;
                break;
            case 'l':
                d = Direction.LEFT;
                break;
            case 't':
                d = Direction.TOP;
                break;
        }
        if(d != null)
        {
            Handler.getCurrentFieldList().movePlayer(d);
        }
        ptr++;
    }

    public void Solve()
    {
       String path = SokobanSolver.solve(Handler.getCurrentMap());
        cmds = path.toCharArray();

        for(char c : cmds) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {


                        @Override
                        public void run() {
                            move();
                        }
                    });
                }
            }).start();
        }
    }

    private void findPlayer()
    {

    }
}
