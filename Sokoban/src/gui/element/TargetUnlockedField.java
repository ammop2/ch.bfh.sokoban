package gui.element;

import main.java.Handler;

/**
 * Created by Pascal on 01.11.2015.
 */
public class TargetUnlockedField extends BaseField {
    public TargetUnlockedField() {
        super(String.format("gui/images/themes/%s/padlock_unlocked.png", Handler.getCurrentTheme()));
    }
}
