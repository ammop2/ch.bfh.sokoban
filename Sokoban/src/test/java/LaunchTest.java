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

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

            Menu menu = new Menu("..\\Maps\\");
        menu.init();

    }
}

