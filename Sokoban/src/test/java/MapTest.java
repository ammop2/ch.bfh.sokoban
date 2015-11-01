package test.java;

import main.java.Map;
import main.java.MapReader;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Pascal on 06.10.2015.
 *
 * this class should load a MyController and test
 */
public class MapTest {

    public static void main(String[] args) throws IOException {

        Map [] maps = MapReader.load("C:\\git\\ch.bfh.sokoban\\Maps\\");
        for(int i =0; i<maps.length;i++){
            System.out.println("name:" + maps[i].getName());
            System.out.println("X:" + maps[i].getXSize());
            System.out.println("Y:" + maps[i].getYSize());
            Arrays.stream(maps[i].getFields()).forEach(x -> System.out.print(x));
            System.out.println("");
        }

    }
}
