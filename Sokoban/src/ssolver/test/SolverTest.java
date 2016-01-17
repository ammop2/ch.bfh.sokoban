package ssolver.test;

import ssolver.java.Solver;

/**
 * Created by Pascal on 16.01.2016.
 */
public class SolverTest {
    public static void main(String[] args) {
        Solver solver = new Solver("C:\\tmp\\Maze1_X22_Y11.txt");
        System.out.print(solver.solve());

    }
}
