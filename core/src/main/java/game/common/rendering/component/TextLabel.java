package game.common.rendering.component;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import game.resources.Assets;

public class TextLabel extends Label {
    public TextLabel() {
        super("", Assets.getSkin().get("text-label", Label.LabelStyle.class));
    }

    public TextLabel(String text) {
        super(text, Assets.getSkin().get("text-label", Label.LabelStyle.class));
    }

    public TextLabel(String text, boolean isWrapping) {
        super(text, Assets.getSkin().get("text-label", Label.LabelStyle.class));
        setWrap(isWrapping);
    }
}
