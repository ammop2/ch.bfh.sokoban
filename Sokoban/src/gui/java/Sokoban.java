package gui.java;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.Direction;
import main.java.Handler;

/**
 * Created by Pascal on 30.10.2015.
 */
public class Sokoban extends Application implements EventHandler<KeyEvent> {

    private static Stage pStage;

    public static Stage getpStage() {
        return pStage;
    }

    private boolean controlPressed;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Handler.init();
        Sokoban.pStage = primaryStage;

        primaryStage.setTitle("Sokoban, Pascal Ammon, Gabriel Wyss, BFH");

        Handler.setSelectMapUrl(getClass().getResource("../fxml/select_map.fxml"));
        Handler.setNewMapUrl(getClass().getResource("../fxml/new_map.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));
        Parent root = (Parent)loader.load();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(KeyEvent event) {

        Direction d = null;
        switch( event.getCode() ) {
            case UP:
                d = Direction.TOP;
                break;
            case DOWN:
                d = Direction.BOTTOM;
                break;
            case LEFT:
                d = Direction.LEFT;
                break;
            case RIGHT:
                d = Direction.RIGHT;
                break;
            case CONTROL:
                controlPressed = true;
                break;
            case Z:
                Handler.getPlaygroundController().undo();
                break;
                default:
                    controlPressed = false;
                    break;
        }
        if( d!= null)
        {
            System.out.println("Directon" + d);
            Handler.getPlaygroundController().changeFields( Handler.getCurrentFieldList().movePlayer(d));
        }
    }
}
