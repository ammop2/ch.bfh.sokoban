package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;


/**
 * Created by Pascal on 06.10.2015.
 *
 * desc: this class represent a Map / GameArea
 */
public class Map {

    private File file;
    private String name;
    private int xSize;
    private int ySize;

    public Map(String fPath)
    {
        this.file = new File(fPath);
    }
    public int getXSize(){
        return xSize;
    }
    public int getYSize(){
        return ySize;
    }

    public char[][] getMap(){
        String[] fElements = file.getName().split("[_.XY]");
        name = fElements[0];
        xSize = Integer.parseInt(fElements[2]);
        ySize = Integer.parseInt(fElements[4]);

        char[][] rMap = new char[ySize][xSize];

        if(!file.exists()) return null;
        try {
            int row = 0;
            for(Object line : Files.lines(file.toPath()).toArray())
            {
                char[] fields = ((String)line).toCharArray();
                int refCtr = 0;
                for(int i = 0; i < fields.length; i++)
                {
                    char field = fields[i];
                    if(field != ' ')
                    {
                        rMap[row][i] = field;
                    }
                }
                row++;
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int y = 1; y < ySize-1; y++){
            for(int x = 1; x < xSize -1; x++) {
                if (rMap[y][x] == 0) {


                    boolean left = false;
                    for (int goToMin = x; goToMin > 0; goToMin--) {
                        if (rMap[y][goToMin] != 0) {
                            left = true;
                            break;
                        }
                    }
                    boolean right = false;
                    for (int goToMax = x; goToMax < xSize; goToMax++) {
                        if (rMap[y][goToMax] != 0) {
                            right = true;
                            break;
                        }
                    }
                    if (right && left) rMap[y][x] = 'A';
                }
            }
        }
        return rMap;
    }
}
