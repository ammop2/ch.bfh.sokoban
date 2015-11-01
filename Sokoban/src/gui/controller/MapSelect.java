package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.Handler;
import main.java.Map;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 01.11.2015.
 */
public class MapSelect implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(Map map : Handler.getMaps())
        {
            mapComboBox.getItems().add(map.getName());
        }
    }

    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private javafx.scene.control.Button startButton;
    @FXML private javafx.scene.control.Label xSizeLabel;
    @FXML private javafx.scene.control.Label ySizeLabel;


    @FXML
    ComboBox mapComboBox;

    @FXML
    private void cancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void mapSelectedAction() {
        Map map = Handler.getMaps()[mapComboBox.getSelectionModel().getSelectedIndex()];
        xSizeLabel.setText(String.format("%s", map.getXSize()));
        ySizeLabel.setText(String.format("%s", map.getYSize()));

        Handler.setCurrentMap(map);
    }

    @FXML
    private void startButtonAction() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/playground.fxml"));
            Main.getbPane().setCenter(root);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}