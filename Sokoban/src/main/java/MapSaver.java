package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by u216070 on 19.10.2015.
 */
public class MapSaver {
    private static String dir = "../Maps/";
    private static PrintWriter writer;

    public static boolean saveMap(String name, FieldTyp[][] fields, int x, int y) {


        try {
            String path = dir + name + "_X" + x + "_Y" + y + ".txt";
            File file = new File(path);
            PrintWriter writer = new PrintWriter(file);


            String line = "";
            for (int i = 0; i < fields.length; i++) {
                line = "";
                for (int j = 0; j < fields[i].length; j++) {
                    if (fields[i][j] == FieldTyp.BLANK || fields[i][j] == FieldTyp.PLAYGROUND) line = line + ' ';
                    if (fields[i][j] == FieldTyp.WALL) line = line + 'X';
                    if (fields[i][j] == FieldTyp.PLAYER) line = line + '@';
                    if (fields[i][j] == FieldTyp.TARGET_LOCKED || fields[i][j] == FieldTyp.TARGET_UNLOCKED) line = line + '.';
                    if (fields[i][j]  == FieldTyp.KEY) line = line + '*';
                }
                writer.println(line);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean saveMap(String name, int[] fieldsint, int x, int y) {
    FieldTyp[][] fields=new FieldTyp[y][x];

        int ptr = 0;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                switch (fieldsint[ptr]) {
                    case 0:
                        fields[i][j] = FieldTyp.BLANK;
                        break;
                    case 1:
                        fields[i][j] = FieldTyp.WALL;
                        break;
                    case 2:
                        fields[i][j] = FieldTyp.PLAYER;
                        break;
                    case 3:
                        fields[i][j] = FieldTyp.TARGET_UNLOCKED;
                        break;
                    case 4:
                        fields[i][j] = FieldTyp.KEY;
                        break;
                    default:
                        break;
                }
                ptr++;
            }
        }

       return saveMap(name, fields,x,y);
    }

}
