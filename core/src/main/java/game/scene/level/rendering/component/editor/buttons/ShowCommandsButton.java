package game.scene.level.rendering.component.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.level.rendering.component.editor.CommandsLayout;
import game.resources.Assets;

public class ShowCommandsButton extends TextButton {
    private final CommandsLayout commandsLayout;
    private boolean activated = false;

    public ShowCommandsButton(CommandsLayout commandsLayout) {
        super("?", Assets.getSkin());
        this.commandsLayout = commandsLayout;

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                activated = !activated;
                commandsLayout.update(activated);
            }
        });
    }
}
