package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by u216070 on 19.10.2015.
 */
public class MapSaver {
    private static String path = "C:\\git\\ch.bfh.sokoban\\Maps\\";
    private static PrintWriter out;

    public static boolean saveMap(String name, Field[][] fields, int x, int y) {


        try {
            path = path + name + "_X" + x + "_Y" + y + ".txt";
            System.out.println(path);
            File file = new File(path);
            PrintWriter out = new PrintWriter(file);


            String line = "";
            for (int i = 0; i < fields.length; i++) {
                line = "";
                for (int j = 0; j < fields[i].length; j++) {
                    if (fields[i][j].isBlank()) line = line + ' ';
                    if (fields[i][j].isStone()) line = line + 'X';
                    if (fields[i][j].isAvatar()) line = line + '@';
                    if (fields[i][j].isTarget()) line = line + '.';
                    if (fields[i][j].isKey()) line = line + '*';
                }
                System.out.println("printing now");
                System.out.println(out == null);
                out.println("dasisteintest");
                out.println(line);
                System.out.println(out == null);


        }
            System.out.println(out == null);

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(out == null);
        out.close();
        return true;
    }

}
