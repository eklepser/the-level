package com.eklepser.thelevel.graphics.screen.level.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.level.editor.CommandsLayout;
import com.eklepser.thelevel.util.Resources;

public class ShowCommandsButton extends TextButton {
    private final CommandsLayout commandsLayout;
    private boolean activated = false;

    public ShowCommandsButton(CommandsLayout commandsLayout) {
        super("?", Resources.getSkin());
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
