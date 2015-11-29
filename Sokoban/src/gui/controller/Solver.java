package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.java.Handler;
import solver.java.MySolver;

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
    private void solve(){
        my.solver.java.Solver solv = new my.solver.java.Solver(Handler.getCurrentMap());
        solv.solve();
    }
}
