package main.java;

import gui.controller.Editor;

/**
 * Created by Pascal on 03.11.2015.
 */
public class EditorHandler {
    private static int rowCount = 12;
    private static int columnCount = 12;
    private static String mapName;
    private static Editor editor;
    private static FieldTyp[][] fieldTyps;

    public static Editor getEditor() {
        return editor;
    }

    public static void setEditor(Editor editor) {
        EditorHandler.editor = editor;
    }

    private static FieldTyp currentTyp = FieldTyp.PLAYGROUND;

    public static FieldTyp getCurrentTyp() {
        return currentTyp;
    }

    public static void setCurrentTyp(FieldTyp currentTyp) {
        EditorHandler.currentTyp = currentTyp;
    }

    public static int getColumnCount() {
        return columnCount;
    }

    public static void setColumnCount(int columnCount) {
        EditorHandler.columnCount = columnCount;
    }



    public static String getMapName() {
        return mapName;
    }

    public static void setMapName(String mapName) {
        EditorHandler.mapName = mapName;
    }

    public static int getRowCount() {
        return rowCount;
    }

    public static void setRowCount(int rowCount) {
        EditorHandler.rowCount = rowCount;
    }

    public static void save()
    {
        MapSaver.saveMap(mapName, fieldTyps, columnCount, rowCount);
    }

    public static void changeField(int x, int y)
    {
        fieldTyps[y][x] = currentTyp;
        editor.changeFields(new ChangeItem(x, y, FieldTyp.PLAYGROUND, currentTyp));
    }

    public static void init(){
        fieldTyps = new FieldTyp[rowCount][columnCount];
        for(int i = 0; i < rowCount; i++)
        {
            for(int z = 0; z < columnCount; z++)
            {
                fieldTyps[i][z] = (i == 0 || z == 0 || i == rowCount-1||z==columnCount-1)? FieldTyp.WALL:FieldTyp.PLAYGROUND;
                editor.changeFields(new ChangeItem(z, i, fieldTyps[i][z],  fieldTyps[i][z]));
            }
        }
    }
}
