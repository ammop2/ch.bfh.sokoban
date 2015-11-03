package gui.element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.java.Handler;

/**
 * Created by Pascal on 01.11.2015.
 */
public class PlayerField extends BaseField {
    public PlayerField() {
        super(String.format("gui\\images\\themes\\%s\\avatar.png", Handler.getCurrentTheme()));
    }
}
