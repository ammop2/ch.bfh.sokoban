package main.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pascal on 02.11.2015.
 */
public class ThemeReader {
    public static ArrayList<String> load(String path) throws IOException {
        File folder = new File(path);
        //check if path is a folder
        if (!folder.isDirectory()) return null;


        File[] listOfElements = folder.listFiles();

        ArrayList<String> subFolders =  new ArrayList<>();
        for(File f : listOfElements)
        {
            if(f.isDirectory()) subFolders.add(f.getName());
        }

        return subFolders;
    }
}
