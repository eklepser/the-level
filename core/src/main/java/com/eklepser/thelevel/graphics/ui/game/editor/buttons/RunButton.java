package com.eklepser.thelevel.graphics.ui.game.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.util.Resources;

public class RunButton extends TextButton {
    private boolean isRunning = false;

    public RunButton(Executor executor, TextLabel statusLabel) {
        super("Run", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isRunning) return;
                String status = executor.checkAndExecute();
                statusLabel.setText(status);
                isRunning = true;
            }
        });
    }

    public void setRunning(boolean running) { isRunning = running; }
}
