package gui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.Handler;
import solver.bfh.Controller;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 02.11.2015.
 */
public class Solver implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/playground.fxml"));
            playgroundPane.getChildren().add(root);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label startTime;

    @FXML
    private Label endTime;

    @FXML
    private Label totalTime;

    @FXML
    private Pane playgroundPane;

    @FXML
    private ComboBox<String> searchAlgo;

    @FXML
    private void solve(){
        String algr = "";
        switch(searchAlgo.getSelectionModel().getSelectedIndex())
        {
            case 0: algr = "-b"; break;
            case 1: algr = "-d"; break;
            case 2: algr = "-u"; break;
            case 3: algr = "-gb"; break;
            case 4: algr = "-gm"; break;
            case 5: algr = "-ab"; break;
            case 6: algr = "-am"; break;
            default: algr = "-b"; break;

        }

        Controller.Solve(algr, Handler.getCurrentMap());

    }
}
