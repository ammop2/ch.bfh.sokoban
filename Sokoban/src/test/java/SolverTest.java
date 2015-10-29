package test.java;

import main.java.Map;
import main.java.MapReader;
import main.java.Solver;

import java.io.IOException;

/**
 * Created by Pascal on 29.10.2015.
 */
public class SolverTest {
    public static void main(String[] args) throws IOException {
        Map[] maps = MapReader.load("..\\Maps\\");
        Solver s = new Solver(maps[0]);
        s.Solve();
    }
}
