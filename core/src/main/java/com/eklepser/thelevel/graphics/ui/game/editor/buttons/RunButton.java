package com.eklepser.thelevel.graphics.ui.game.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.game.editor.Editor;
import com.eklepser.thelevel.util.Resources;

public class RunButton extends TextButton {
    private boolean isRunning = false;

    public RunButton(Editor editor) {
        super("Run", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                editor.resetRunning();
                editor.run();
                isRunning = true;
            }
        });
    }

    public void setRunning(boolean running) { isRunning = running; }
}
