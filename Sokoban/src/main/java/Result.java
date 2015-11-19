package main.java;

/**
 * Created by u216070 on 19.11.2015.
 */
public class Result {

    private int result;
    private String mapName;

    public Result(String mapName, int result){
        this.result=result;
        this.mapName=mapName;
    }

    public int getResult(){
        return result;
    }

    public String getMapName(){
        return mapName;
    }
}
