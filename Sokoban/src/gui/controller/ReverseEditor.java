package gui.controller;

import gui.element.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.ChangeItem;
import main.java.ReverseHandler;
import main.java.FieldTyp;
import main.java.Handler;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 03.11.2015.
 */
public class ReverseEditor implements Initializable {
    @FXML
    GridPane gridPane;


    @FXML
    private void pullClicked()
    {
        ReverseHandler.togglePull();
    }
    @FXML
    private void saveClicked()
    {
        ReverseHandler.save();
        Handler.init();
    }


    public void changeFields(ChangeItem cItem) {
        Node result = null;
        for (Node node : gridPane.getChildren()) {
            if (gridPane.getRowIndex(node) == cItem.getY() && gridPane.getColumnIndex(node) == cItem.getX()) {
                result = node;
                break;
            }
        }
        gridPane.getChildren().remove(result);
        addField(cItem.getX(), cItem.getY(), cItem.getTypNew());
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < ReverseHandler.getColumnCount(); i++)
        {
            gridPane.getColumnConstraints().add(new ColumnConstraints(Handler.getFieldSize()));
        }

        for(int i = 0; i < ReverseHandler.getRowCount(); i++)
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

        ReverseHandler.setReverseEditor(this);
        ReverseHandler.init();

    }
}