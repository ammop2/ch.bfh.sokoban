package gui.element;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.java.ChangeItem;
import main.java.EditorHandler;
import main.java.Handler;
import main.java.Mode;

import java.util.ArrayList;

/**
 * Created by Pascal on 01.11.2015.
 */
public class BaseField extends Pane implements EventHandler<MouseEvent> {
    public BaseField(String imagePath)
    {
        setOnMouseClicked(this);
        //setImage(String.format("gui\\images\\themes\\%s\\playground.png", Handler.getCurrentTheme()));
        setImage(imagePath);

    }
    private void setImage(String imagePath)
    {
        Image image = new Image(imagePath);
        ImageView imgView = new ImageView(image);
        imgView.setFitWidth(Handler.getFieldSize());
        imgView.setFitHeight(Handler.getFieldSize());
        this.getChildren().add(imgView);
    }

    @Override
    public void handle(MouseEvent event) {
       GridPane gp = (GridPane)getParent();
        if(gp != null)
        {
            if(Handler.getMode() == Mode.PLAY) {
                Handler.getPlaygroundController().changeFields(Handler.getCurrentFieldList().findWay(gp.getColumnIndex(this), gp.getRowIndex(this)));
            }
            else if(Handler.getMode() == Mode.EDITOR)
            {
                EditorHandler.changeField(gp.getColumnIndex(this), gp.getRowIndex(this));
            }
            }
        System.out.println("field clicked");
    }
}
