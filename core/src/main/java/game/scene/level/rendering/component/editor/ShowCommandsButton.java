package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.data.IO.Assets;

public final class ShowCommandsButton extends TextButton {
    private boolean activated = false;

    public ShowCommandsButton(CommandsLayout commandsLayout) {
        super("?", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                activated = !activated;
                commandsLayout.update(activated);
            }
        });
    }
}
