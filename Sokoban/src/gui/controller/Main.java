package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.Handler;
import main.java.Mode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 01.11.2015.
 */
public class Main implements Initializable {

    private static BorderPane bPane;

    public static BorderPane getbPane() {
        return bPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.bPane = borderPane;
    }


    private Stage stage;

    private Parent root;

    @FXML
    private BorderPane borderPane;

    @FXML
    MenuBar myMenuBar;

    @FXML
    private void loadMap(ActionEvent event)
    {
        Handler.setMainUrl(getClass().getResource("../fxml/playground.fxml"));
        Handler.setMode(Mode.PLAY);
        Handler.loadMap(myMenuBar.getScene().getWindow());
    }

    @FXML
    private void solveMap(ActionEvent event)
    {
        Handler.setMainUrl(getClass().getResource("../fxml/solver.fxml"));
        Handler.setMode(Mode.SOLVER);
        Handler.loadMap(myMenuBar.getScene().getWindow());
    }

    @FXML
    private void newMap(ActionEvent event)
    {
        Handler.setMainUrl(getClass().getResource("../fxml/editor.fxml"));
        Handler.setMode(Mode.EDITOR);
        Handler.newMap(myMenuBar.getScene().getWindow());
    }

}