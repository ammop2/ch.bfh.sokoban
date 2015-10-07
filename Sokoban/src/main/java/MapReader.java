package main.java; /**
 * Created by Gabriel on 20.09.2015.
 * Modifed Gabriel, Pascal
 */

import main.java.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MapReader {

    public static Map load(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) return null;

        String[] fElements = file.getName().split("[_.XY]");
        String name = fElements[0];
        int xSize = Integer.parseInt(fElements[2]);
        int ySize = Integer.parseInt(fElements[4]);
        int[] fields = new int[xSize * ySize];

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        int ptr = 0;
        while (line != null) {
            for(int x = 0; x < line.length(); x++){

                switch(line.charAt(x))
                {
                    case ' ':fields[ptr] = 0;break;
                    case 'X':fields[ptr] = 1;break;
                    case '@':fields[ptr] = 2;break;
                    case '.':fields[ptr] = 3;break;
                    case '*':fields[ptr] = 4;break;
                    default:break;
                }
                ptr++;
            }
            line = br.readLine();
        }
        return new Map(name, fields, xSize, ySize);
    }
}