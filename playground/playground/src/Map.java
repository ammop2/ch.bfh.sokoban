/**
 * Created by u216070 on 20.09.2015.
 */
public class Map {

    String name;
    int[][] fields;

    public Map(String name, int[][] fields){
        this.name = name;
        this.fields= fields;
    }

    public String getName(){
        return name;
    }

    public int[][] getFields(){
        return fields;
    }

}
