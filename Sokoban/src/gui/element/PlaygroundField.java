package gui.element;

import main.java.Handler;

/**
 * Created by Pascal on 01.11.2015.
 */
public class PlaygroundField extends BaseField {
    public PlaygroundField()
    {
        super(String.format("gui/images/themes/%s/playground.png", Handler.getCurrentTheme()));
    }
}
