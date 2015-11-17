package main.java;


import gui.controller.ReverseEditor;

/**
 * Created by Pascal on 03.11.2015.
 */
public class ReverseHandler {
    private static int rowCount = 12;
    private static int columnCount = 12;
    private static String mapName;
    private static ReverseEditor reverseEditor;
    private static FieldTyp[][] fieldTyps;
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

    public static void setColumnCount(int columnCount) {
        ReverseHandler.columnCount = columnCount;
    }

    public static String getMapName() {
        return mapName;
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

    public static void setMapName(String mapName) {
        ReverseHandler.mapName = mapName;
    }

    public static int getRowCount() {
        return rowCount;
    }

    public static void setRowCount(int rowCount) {
        ReverseHandler.rowCount = rowCount;
    }

    public static void save()
    {
        MapSaver.saveMap(mapName, fieldTyps, columnCount, rowCount);
    }


    public static void init(){
        rowCount=Handler.getCurrentMap().getYSize();
        columnCount=Handler.getCurrentMap().getXSize();
        fieldTyps = new FieldTyp[rowCount][columnCount];
        int[] map = Handler.getCurrentMap().getFields();
        int ptr = 0;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                switch (map[ptr]) {
                    case 0:
                        fieldTyps[i][j] = FieldTyp.BLANK;
                        break;
                    case 1:
                        fieldTyps[i][j] = FieldTyp.WALL;
                        break;
                    case 2:
                        fieldTyps[i][j] = FieldTyp.PLAYER;
                        break;
                    case 3:
                        fieldTyps[i][j] = FieldTyp.TARGET_UNLOCKED;
                        break;
                    case 4:
                        fieldTyps[i][j] = FieldTyp.KEY;
                        break;
                    default:
                        break;
                }
                ptr++;
            }
        }


    }
}
