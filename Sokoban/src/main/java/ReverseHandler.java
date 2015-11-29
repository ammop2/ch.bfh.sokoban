package main.java;


import gui.controller.ReverseEditor;

/**
 * Created by Pascal on 03.11.2015.
 */
public class ReverseHandler {
    private static int columnCount =12;
    private static int rowCount =12;
    private static ReverseEditor reverseEditor;
    private static boolean pull;

    public static ReverseEditor getReverseEditor() {
        return reverseEditor;
    }

    public static void setReverseEditor(ReverseEditor editor) {
        ReverseHandler.reverseEditor = editor;
    }

    public static int getColumnCount() {
        return columnCount;
    }

    public static void togglePull() {
        if (pull){
            pull=false;
        }else{
            pull=true;
        }
        System.out.println(pull);
    }

    public static boolean getPull(){
        return pull;
    }


    public static int getRowCount() {
        return rowCount;
    }

    public static void save()
    {
        MapSaver.saveMap(Handler.getCurrentMap().getName(), Handler.getCurrentFieldList().getFields(), Handler.getCurrentMap().getXSize(), Handler.getCurrentMap().getYSize(), Handler.getCurrentMap().getDifficulty());
    }

}
