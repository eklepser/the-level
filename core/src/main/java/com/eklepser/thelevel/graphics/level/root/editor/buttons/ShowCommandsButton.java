package com.eklepser.thelevel.graphics.level.root.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.level.root.editor.CommandsPanel;
import com.eklepser.thelevel.util.Resources;

public class ShowCommandsButton extends TextButton {
    private final CommandsPanel commandsPanel;
    private boolean activated = false;

    public ShowCommandsButton(CommandsPanel commandsPanel) {
        super("?", Resources.getSkin());
        this.commandsPanel = commandsPanel;

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                activated = !activated;
                commandsPanel.update(activated);
            }
        });
    }
}
