package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import main.java.Handler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 02.11.2015.
 */
public class Win implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Button loadButton;

    @FXML
    private void loadMap()
    {
        Handler.loadMap(loadButton.getScene().getWindow());
    }
}
