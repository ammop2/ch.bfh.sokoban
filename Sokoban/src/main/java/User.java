package main.java;

import java.util.ArrayList;

/**
 * Created by u216070 on 19.11.2015.
 */
public class User {

    private String name;
    private ArrayList<Result> results;


    public User(String name)
    {
        this.name = name;
        this.results = new ArrayList<Result>();
    }

    public  void addResult(String mapName, int result) {
        results.add(new Result(mapName,result));
    }

    public ArrayList<Result> getResults(){
        return results;
    }

    public String getName(){
        return name;
    }


}