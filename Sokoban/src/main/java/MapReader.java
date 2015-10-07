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

    public static Map[] load(String path) throws IOException {
        File folder = new File(path);
        //check if path is a folder
        if (!folder.isDirectory()) return null;
        File[] listOfElements = folder.listFiles();
        int numberOfFiles = 0;
        //get the number of files
        for (int i = 0; i < listOfElements.length; i++) {
            if (listOfElements[i].isFile()) {
                numberOfFiles++;
            }
        }
        Map[] maps = new Map[numberOfFiles];
        //create a map for each file
        for (int i = 0; i < listOfElements.length; i++) {
            if (listOfElements[i].isFile()) {
                maps[i] = getMap(listOfElements[i].getAbsolutePath());
            }
        }

        return maps;
    }

    //this method converts one file to a map
    private static Map getMap(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) return null;

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
            for (int x = 0; x < line.length(); x++) {

                switch (line.charAt(x)) {
                    case ' ':
                        fields[ptr] = 0;
                        break;
                    case 'X':
                        fields[ptr] = 1;
                        break;
                    case '@':
                        fields[ptr] = 2;
                        break;
                    case '.':
                        fields[ptr] = 3;
                        break;
                    case '*':
                        fields[ptr] = 4;
                        break;
                    default:
                        break;
                }
                ptr++;
            }
            line = br.readLine();
        }
        return new Map(name, fields, xSize, ySize);
    }
}