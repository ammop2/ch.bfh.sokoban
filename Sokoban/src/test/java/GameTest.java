package test.java;

import main.java.GameContainer;
import main.java.Map;
import main.java.MapReader;

import java.io.IOException;

/**
 * Created by Pascal on 09.10.2015.
 */
public class GameTest {
    public static void main(String[] args) throws IOException {
        System.out.println("HI");
        Map map = MapReader.getMap("C:\\github\\ch.bfh.sokoban\\Maps\\Maze1_X22_Y11.txt");
        GameContainer gc = new GameContainer(map);
        gc.run();
    }
}
