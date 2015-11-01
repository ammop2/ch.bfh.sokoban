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
        try
        {
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("../fxml/map_select.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Map select...");
            stage.initOwner(myMenuBar.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}