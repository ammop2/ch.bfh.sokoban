package main.java;

import gui.controller.*;
import gui.controller.Playground;

import java.io.IOException;

/**
 * Created by Pascal on 01.11.2015.
 */


public class Handler {

    private static Map currentMap;
    private static FieldList currentFieldList;
    private static gui.controller.Playground playgroundController;

    public static Playground getPlaygroundController() {
        return playgroundController;
    }

    public static void setPlaygroundController(Playground playgroundController) {
        Handler.playgroundController = playgroundController;
    }

    public static FieldList getCurrentFieldList() {
        return currentFieldList;
    }

    public static Map getCurrentMap() {
        return currentMap;
    }

    public static void setCurrentMap(Map currentMap) {
        Handler.currentFieldList = new FieldList(currentMap);
        Handler.currentMap = currentMap;
    }

    private static Map[] maps;
    public static Map[] getMaps() {
        return maps;
    }

    public static void init()
    {
        try
        {
            maps = MapReader.load("..\\Maps\\");

        }
        catch (IOException e)
        {
            System.out.println("Error during init Sokoban game. Message");
            e.printStackTrace();
        }
    }

    public static double getFieldSize()
    {
        double columnWidth = Main.getbPane().getWidth() / Handler.getCurrentMap().getXSize();
        double rowHeight = Main.getbPane().getHeight() / Handler.getCurrentMap().getYSize();

        return columnWidth > rowHeight ? rowHeight : columnWidth;
    }
}
