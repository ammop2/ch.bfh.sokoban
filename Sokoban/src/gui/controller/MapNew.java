package gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.Difficulty;
import main.java.EditorHandler;
import main.java.Handler;
import main.java.Map;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 01.11.2015.
 */
public class MapNew implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sliderRow.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                EditorHandler.setRowCount(newValue.intValue());
                lblRow.setText(String.valueOf(newValue.intValue()));
            }
        });

        sliderColumn.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                EditorHandler.setColumnCount(newValue.intValue());
                lblColumn.setText(String.valueOf(newValue.intValue()));
            }
        });

        sliderDifficulty.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int diff =newValue.intValue();
                Difficulty difficulty = Difficulty.EASY;

                switch(diff){
                    case 1:
                        difficulty=Difficulty.EASY;
                        break;
                    case 2:
                        difficulty= Difficulty.MEDIUM;
                        break;
                    case 3:
                        difficulty = Difficulty.HARD;
                        break;
                    default:
                        break;

                }
                EditorHandler.setDifficulty(difficulty);
                lblDifficulty.setText(String.valueOf(newValue.intValue()));
            }
        });

    }

    @FXML private javafx.scene.control.Slider sliderRow;
    @FXML private javafx.scene.control.Slider sliderColumn;
    @FXML private javafx.scene.control.Slider sliderDifficulty;
    @FXML private Label lblRow;
    @FXML private Label lblColumn;
    @FXML private TextField lblName;
    @FXML private Label lblDifficulty;
    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private javafx.scene.control.Button startButton;

    @FXML
    private void lblNameChange()
    {

    }
    @FXML
    private void cancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void startButtonAction() {

        try {
            EditorHandler.setMapName(lblName.getText());
            Parent root = FXMLLoader.load(Handler.getMainUrl());
            Main.getbPane().setCenter(root);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}