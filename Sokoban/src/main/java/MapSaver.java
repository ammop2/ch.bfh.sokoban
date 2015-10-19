package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by u216070 on 19.10.2015.
 */
public class MapSaver {
    private static String dir = "..\\Maps\\";
    private static PrintWriter writer;

    public static boolean saveMap(String name, Field[][] fields, int x, int y) {


        try {
            String path = dir + name + "_X" + x + "_Y" + y + ".txt";
            System.out.println(path);
            File file = new File(path);
            PrintWriter writer = new PrintWriter(file);


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
                writer.println(line);


        }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(writer == null);

        return true;
    }

}
