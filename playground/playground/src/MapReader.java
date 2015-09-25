/**
 * Created by u216070 on 20.09.2015.
 */

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;


public class MapReader {

    private String path = "C:\\git\\Project1\\maps.txt";
    private ArrayList<Map> maps = new ArrayList<Map>();

    public MapReader() {
        String lines = "";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                lines = lines + line + "\n";
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);

        }
        //finally {
        //    try {
        //        br.close();
        //        fr.close();
        //    } catch (IOException ex) {


        //    }
        //}


        String games[] = lines.split("%");
        for (int i = 0; i < games.length; i++) {
            if (games[i].length() > 15) this.convert(games[i]);
        }


    }

    private void convert(String m) {
        String zeilen[] = m.split("\n");
        String name = zeilen[1];



        String strwidth = zeilen[3].substring(zeilen[3].length() - 2, zeilen[3].length());
        String strheight = zeilen[4].substring(zeilen[4].length() - 2, zeilen[4].length());

        int width = Integer.parseInt(strwidth);
        int height = Integer.parseInt(strheight);

        int[][] fields= new int[height][width];
        int zeilenr=8;
        for (int i = 0; i < height; i++) {
System.out.println(zeilen[i+zeilenr]);
            for(int j=0; j< zeilen[i+zeilenr].length(); j++){
                if(zeilen[i+zeilenr].charAt(j)==' ') fields[i][j]= 99;
                if(zeilen[i+zeilenr].charAt(j)=='X') fields[i][j]= 1;
                if(zeilen[i+zeilenr].charAt(j)=='@') fields[i][j]= 2;
                if(zeilen[i+zeilenr].charAt(j)=='.') fields[i][j]= 3;
                if(zeilen[i+zeilenr].charAt(j)=='*') fields[i][j]= 4;

            }

        }


        System.out.print(zeilen[1] + " " + width + " " + height + "\n");

        if(fields.length>2)this.maps.add(new Map(name,fields));
    }

    public ArrayList<Map> getMaps(){
        return maps;
    }

    public Map getMapByName(String mapName){

        for(Map map : maps){
            if(map.getName().equalsIgnoreCase(mapName)){
                return map;
            }

        }
        return null;
    }
}
