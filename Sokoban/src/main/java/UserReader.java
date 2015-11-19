package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by u216070 on 19.11.2015.
 */
public class UserReader {


    public static User[] load(String path) throws IOException {

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
        User[] users = new User[numberOfFiles];
        //create a MyController for each file
        for (int i = 0; i < listOfElements.length; i++) {
            if (listOfElements[i].isFile()) {
                users[i] = getUser(listOfElements[i].getAbsolutePath());
            }
        }

        return users;
    }


    public static User getUser(String path) throws IOException {
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        if (!file.exists()) return null;


        String name = file.getName();
        name = name.split(Pattern.quote("."))[0];
        User user = new User(name);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        while (line != null) {
         System.out.println(line);
            String[] result= line.split("\\s+");
            user.addResult(result[0],Integer.parseInt(result[1]));
            line = br.readLine();
        }
        return user;
    }
}
