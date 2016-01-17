package gui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.java.Handler;

import javax.swing.*;
import java.awt.*;
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
    private Pane playgroundPane;


    @FXML
    private javafx.scene.control.TextArea txtOutput;


    @FXML
    private void solve(){
        ssolver.java.Solver solver = new ssolver.java.Solver(Handler.getCurrentMap().getPath());
        txtOutput.setText(solver.solve());
    }
}
