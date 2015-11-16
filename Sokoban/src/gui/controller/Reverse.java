package gui.controller;

import gui.element.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.ChangeItem;
import main.java.FieldTyp;
import main.java.Handler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Pascal on 01.11.2015.
 */
public class Reverse implements Initializable {


    @FXML
    private GridPane gridPane;
    private ArrayList<ChangeItem[]> changeHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Handler.setReverseController(this);
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
        changeHistory = new ArrayList<>();
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
        if(Handler.getCurrentFieldList().win())
        {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/win.fxml"));
                Main.getbPane().setCenter(root);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        changeHistory.add(changes);
    }

    public void undo()
    {
        if(changeHistory.size() > 0) {
            ChangeItem[] changes = changeHistory.get(changeHistory.size() - 1);
            for (ChangeItem cItem : changes) {
                {
                    Node result = null;
                    for (Node node : gridPane.getChildren()) {
                        if (gridPane.getRowIndex(node) == cItem.getY() && gridPane.getColumnIndex(node) == cItem.getX()) {
                            result = node;
                            break;
                        }
                    }
                    gridPane.getChildren().remove(result);
                    if(cItem.getTypOld() == FieldTyp.PLAYER)
                    {
                        Handler.getCurrentFieldList().setPlayer(cItem.getX(), cItem.getY());
                    }
                    addField(cItem.getX(), cItem.getY(), cItem.getTypOld());
                }
            }
            Handler.getCurrentFieldList().undo(changes);
            changeHistory.remove(changeHistory.size() - 1);
        }
    }
}
