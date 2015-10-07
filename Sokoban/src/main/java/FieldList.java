package main.java;

/**
 * Created by Pascal on 06.10.2015.
 *
 * host for fields
 */
public class FieldList {

    private Field[] fields;
    private int pos;
    public FieldList(int nrOfFields)
    {

    }
    public void addField(Field field)
    {
        fields[pos] = field;
        pos++;
    }
}
