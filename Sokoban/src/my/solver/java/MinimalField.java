package my.solver.java;

import main.java.Map;

/**
 * Created by Pascal on 06.11.2015.
 */
public class MinimalField {
    private int[][] minimalField;
    private int nrOfBox;
    private int playerYPos;
    private int playerXPos;

    public int getPlayerXPos() {
        return playerXPos;
    }

    public int getPlayerYPos() {
        return playerYPos;
    }

    public int getNrOfBox() {
        return nrOfBox;
    }

    public int[][] getMinimalField() {
        return minimalField;
    }

    public MinimalField(Map map)
    {
        nrOfBox = 0;
        minimalField = new int[map.getYSize()][map.getXSize()];
        int ptr = 0;
       for(int y = 0; y < map.getYSize(); y++)
       {
           for(int x = 0; x < map.getXSize(); x++)
           {
               minimalField[y][x] = map.getFields()[ptr];
               if(minimalField[y][x] == 3)
               {
                   nrOfBox++;
               }
               else if(minimalField[y][x] == 2)
               {
                   playerXPos = x;
                   playerYPos = y;
               }
               ptr ++;
           }
       }
    }
}
