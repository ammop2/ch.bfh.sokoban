package gui.element;

import main.java.Handler;

/**
 * Created by Pascal on 01.11.2015.
 */
public class WallField extends BaseField{
    public  WallField()
    {
        super(String.format("gui\\images\\themes\\%s\\wall.png", Handler.getCurrentTheme()));
    }
}
