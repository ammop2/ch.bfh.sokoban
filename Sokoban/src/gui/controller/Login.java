package gui.controller;

import gui.java.Sokoban;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.Handler;
import main.java.Map;
import main.java.User;
import main.java.UserSaver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 01.11.2015.
 */
public class Login implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(User user : Handler.getUsers())
        {
            comboBoxExistingPlayer.getItems().add(user.getName());
        }
    }

    @FXML private ComboBox comboBoxExistingPlayer;
    @FXML private TextField lblPlayerName;
    @FXML private javafx.scene.control.Button createPlayerButton;
    @FXML private javafx.scene.control.Button login;

    @FXML
    private void createPlayerAction(){
        String username = lblPlayerName.getText();
        User user = new User(username);
        Handler.getUsers().add(user);
        Handler.setCurrentUser(user);
        UserSaver.saveUser(user);



        Stage stage = (Stage) lblPlayerName.getScene().getWindow();
        stage.close();
        Handler.getMainController().showStats();
    }

    @FXML
    private void userSelectedAction() {
        User user = Handler.getUsers().get(comboBoxExistingPlayer.getSelectionModel().getSelectedIndex());
        Handler.setCurrentUser(user);
        Stage stage = (Stage) lblPlayerName.getScene().getWindow();
        stage.close();
        Handler.getMainController().showStats();
    }

}
