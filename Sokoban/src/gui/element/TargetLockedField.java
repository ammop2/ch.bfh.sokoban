package gui.element;

import main.java.Handler;

/**
 * Created by Pascal on 01.11.2015.
 */
public class TargetLockedField extends BaseField {
    public TargetLockedField() {
        super(String.format("gui/images/themes/%s/padlock_locked.png", Handler.getCurrentTheme()));
    }
}
