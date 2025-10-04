package game.scene.level.rendering.component.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.level.window.HelpWindow;
import game.resources.Assets;

public class HelpButton extends TextButton {
    public HelpButton(HelpWindow helpWindow) {
        super("Help", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                helpWindow.toggle();
            }
        });
    }
}
