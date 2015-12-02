package solver.bfh;

import main.java.FieldTyp;
import main.java.Map;
import main.java.Player;
import solver.stepzhou.SokobanSolver;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by Pascal on 29.11.2015.
 */
public class Controller {
    public static void Solve(String alg, Map map){
        String[] args = new String[]{alg, saveTemp(map)};
        SokobanSolver.parseArguments(args);

    }

    private static String saveTemp(Map map)
    {
        try {
            String path = "tmp/solverMap.txt";
            File file = new File(path);
            PrintWriter writer = new PrintWriter(file);

            // width
            writer.println(map.getXSize());
            // height
            writer.println(map.getYSize());

            //Write map to file
            String line = "";
            int fPtr = 0;
            for(int r = 0; r < map.getYSize(); r++)
            {
                line = "";
                for(int c = 0; c < map.getXSize(); c++)
                {
                    switch(map.getFields()[fPtr])
                    {
                        case 0:
                            line += " ";
                            break;
                        case 1:
                            line += "#";
                            break;
                        case 2:
                            line += "@";
                            break;
                        case 3:
                            line += ".";
                            break;
                        case 4:
                            line += "$";
                            break;
                        default:
                            break;
                    }

                    fPtr++;
                }
                writer.println(line);
            }
            writer.close();

            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
