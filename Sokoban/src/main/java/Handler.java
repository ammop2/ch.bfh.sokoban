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
    private static java.net.URL selectMapUrl;
    private static java.net.URL newMapUrl;
    private static java.net.URL mainUrl;
    private static Mode mode;

    public static Mode getMode() {
        return mode;
    }

    public static void setMode(Mode mode) {
        Handler.mode = mode;
    }

    public static void setMainUrl(URL mainUrl) {
        Handler.mainUrl = mainUrl;
    }

    public static URL getNewMapUrl() {
        return newMapUrl;
    }

    public static void setNewMapUrl(URL newMapUrl) {
        Handler.newMapUrl = newMapUrl;
    }

    public static URL getMainUrl() {
        return mainUrl;
    }

    public static URL getSelectMapUrl() {
        return selectMapUrl;
    }

    public static void setSelectMapUrl(URL selectMapUrl) {
        Handler.selectMapUrl = selectMapUrl;
    }

    public static Playground getPlaygroundController() {
        return playgroundController;
    }


    public static void loadMap(Window window) {
        try
        {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(selectMapUrl);
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

    public static void newMap(Window window) {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(newMapUrl);
            stage.setScene(new Scene(root));
            stage.setTitle("new Map");
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
            maps = MapReader.load("../Maps/");
            currentMap = maps[0];
            themes = ThemeReader.load("src/gui/images/themes");
            currentTheme = "vikings";
        }
        catch (IOException e)
        {
            System.out.println("Error during init Sokoban game. Message");
            e.printStackTrace();
        }
    }

    public static double getFieldSize()
    {

        double columnWidth = (mode == Mode.EDITOR) ? (Main.getbPane().getWidth() -150) / Handler.getCurrentMap().getXSize() -5: Main.getbPane().getWidth() / Handler.getCurrentMap().getXSize() -5;
        double rowHeight = Main.getbPane().getHeight() / Handler.getCurrentMap().getYSize() -5;

        return columnWidth > rowHeight ? rowHeight : columnWidth;
    }
}
