package test.java;

import main.java.Map;
import main.java.MapReader;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Pascal on 06.10.2015.
 *
 * this class should load a map and test
 */
public class MapTest {

    public static void main(String[] args) throws IOException {

        Map map = MapReader.load("C:\\github\\ch.bfh.sokoban\\Maps\\Maze1_X22_Y11.txt");
        System.out.println("name:" + map.getName());
        System.out.println("X:" + map.getXSize());
        System.out.println("Y:" + map.getYSize());
        Arrays.stream(map.getFields()).forEach(x -> System.out.print(x));
    }
}
