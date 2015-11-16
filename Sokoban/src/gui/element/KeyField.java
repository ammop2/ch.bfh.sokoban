package gui.element;

import main.java.Handler;

/**
 * Created by Pascal on 01.11.2015.
 */
public class KeyField extends BaseField {
    public KeyField() {
        super(String.format("gui/images/themes/%s/key.png", Handler.getCurrentTheme()));
    }
}
