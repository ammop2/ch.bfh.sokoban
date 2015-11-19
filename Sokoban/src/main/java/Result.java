package main.java;

import java.util.Date;

/**
 * Created by u216070 on 19.11.2015.
 */
public class Result {

    private int result;
    private String mapName;
    private Date date;

    public Result(String mapName, int result, Date date){
        this.result=result;
        this.mapName=mapName;
        this.date=date;
    }

    public int getResult(){
        return result;
    }

    public String getMapName(){
        return mapName;
    }

    public Date getDate(){
        return date;
    }
}
