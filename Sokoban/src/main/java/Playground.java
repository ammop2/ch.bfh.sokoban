package main.java;

/**
 * Created by Pascal on 06.10.2015.
 *
 * desc: this class contains the logic to play.
 */
public class Playground {
    private Map map;
    private FieldList fieldList;

    public Playground(Map map){
        this.map = map;
    }
    public void loadMap(){
        char[][] charMap = this.map.getMap();
        int emptyCounter = 0;
        for(int y = 0; y < map.getYSize(); y++)
        {
            for(int x = 0; x < map.getXSize(); x++)
            {
                if(charMap[y][x] == 0) emptyCounter ++;
                else
                {
                    if(charMap[y][x] == 'X')
                    {

                    }
                }
            }
        }

    }

}
