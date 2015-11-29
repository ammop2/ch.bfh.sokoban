package main.java;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by u216070 on 19.11.2015.
 */

    public class UserSaver {
        private static String dir = "../Users/";
        private static PrintWriter writer;

        public static boolean saveUser(User user) {
            System.out.println(user.getName());

            try {
                String path = dir + user.getName()  + ".txt";
                File file = new File(path);
                PrintWriter writer = new PrintWriter(file);
                ArrayList<Result> results = user.getResults();


                //Write user to file
                String line = "";
                writer.print(line);
                for (Result result : results) {
                    line = "";
                    line = line + result.getMapName() + " " + result.getResult();
                    writer.println(line);
                }
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }

}


