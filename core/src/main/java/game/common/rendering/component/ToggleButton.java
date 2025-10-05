package game.common.rendering.component;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.resources.Assets;

public class ToggleButton extends TextButton {
    private final boolean isPressed = false;

    public ToggleButton(String text) {
        super(text, Assets.getSkin());

    }
}
