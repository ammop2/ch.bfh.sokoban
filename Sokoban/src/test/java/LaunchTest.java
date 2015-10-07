package test.java;

import main.java.MapReader;
import main.java.Menu;
import javax.swing.JOptionPane;

import java.io.IOException;

/**
 * Created by u216070 on 07.10.2015.
 */
public class LaunchTest {

    private static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args){
        try {
            Menu menu = new Menu(MapReader.load("C:\\git\\ch.bfh.sokoban\\Maps\\"));
        }
        catch (IOException e){
                infoBox("YOU ARE GAY","There is a file problem");
            }
    }
}

