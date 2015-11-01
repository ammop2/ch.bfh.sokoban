package gui.controller;

import gui.element.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.ChangeItem;
import main.java.FieldTyp;
import main.java.Handler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 01.11.2015.
 */
public class Playground implements Initializable {

    @FXML
    private GridPane gridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Handler.setPlaygroundController(this);
        for(int i = 0; i < Handler.getCurrentMap().getXSize(); i++)
        {
            gridPane.getColumnConstraints().add(new ColumnConstraints(Handler.getFieldSize()));
        }

        for(int i = 0; i < Handler.getCurrentMap().getYSize(); i++)
        {
            gridPane.getRowConstraints().add(new RowConstraints(Handler.getFieldSize()));
        }

        for(int r = 0; r < Handler.getCurrentMap().getYSize(); r++)
        {
            for(int c = 0; c < Handler.getCurrentMap().getXSize(); c++)
            {
                addField(c, r, Handler.getCurrentFieldList().getField(c, r));
            }
        }
    }

    private void addField(int c, int r, FieldTyp typ)
    {
        if(typ == FieldTyp.KEY)
        {
            gridPane.add(new KeyField(), c, r);
        }
        else if(typ == FieldTyp.PLAYER)
        {
            gridPane.add(new PlayerField(), c, r);
        }
        else if(typ == FieldTyp.TARGET_LOCKED)
        {
            gridPane.add(new TargetLockedField(), c, r);
        }
        else if(typ == FieldTyp.TARGET_UNLOCKED)
        {
            gridPane.add(new TargetUnlockedField(), c, r);
        }
        else if(typ == FieldTyp.WALL)
        {
            gridPane.add(new WallField(), c, r);
        }
        else if(typ == FieldTyp.PLAYGROUND)
        {
            gridPane.add(new PlaygroundField(), c, r);
        }
    }

    public void changeFields(ChangeItem[] changes)
    {
        if(changes == null) return;
        for(ChangeItem cItem : changes)
        {
            Node result = null;
            for(Node node : gridPane.getChildren() ){
                if(gridPane.getRowIndex(node) == cItem.getY() && gridPane.getColumnIndex(node) == cItem.getX()) {
                    result = node;
                    break;
                }
            }
            gridPane.getChildren().remove(result);
            addField(cItem.getX(), cItem.getY(), cItem.getTypNew());
        }

    }
}
