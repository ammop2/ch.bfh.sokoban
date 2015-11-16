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

    private int xSize;
    private int ySize;
    private int[] fields;
    private String name;
    private String path;

    public String getPath() {
        return path;
    }


    public Map(String name, int[] fields, int xSize, int ySize, String path)
    {
        this.name = name;
        this.xSize = xSize;
        this.ySize = ySize;
        this.fields = fields;
        this.path = path;
    }

    public  String getName() {
        return name;
    }

    public int getXSize(){
        return xSize;
    }
    public int getYSize()
    {
        return ySize;
    }
    public int[] getFields()
    {
        return fields;
    }
}
