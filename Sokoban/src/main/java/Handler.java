package main.java;

import gui.controller.*;
import gui.controller.Playground;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pascal on 01.11.2015.
 */


public class Handler {

    private static Map currentMap;
    private static FieldList currentFieldList;
    private static gui.controller.Playground playgroundController;
    private static java.net.URL mapUrl;
    private static java.net.URL mainUrl;

    public static void setMainUrl(URL mainUrl) {
        Handler.mainUrl = mainUrl;
    }

    public static URL getMainUrl() {
        return mainUrl;
    }

    public static URL getMapUrl() {
        return mapUrl;
    }

    public static void setMapUrl(URL mapUrl) {
        Handler.mapUrl = mapUrl;
    }

    public static Playground getPlaygroundController() {
        return playgroundController;
    }

    public static void solveMap()
    {
       Solver solver = new Solver(currentMap);
    }

    public static void loadMap(Window window) {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(mapUrl);
            stage.setScene(new Scene(root));
            stage.setTitle("Map select...");
            stage.initOwner(window);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private static ArrayList<String> themes;

    public static ArrayList<String> getThemes() {
        return themes;
    }

    private static String currentTheme;

    public static String getCurrentTheme() {
        return currentTheme;
    }

    public static void setCurrentTheme(String currentTheme) {
        Handler.currentTheme = currentTheme;
    }

    public static void init()
    {
        try
        {
            maps = MapReader.load("..\\Maps\\");
            themes = ThemeReader.load("src\\gui\\images\\themes");




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
