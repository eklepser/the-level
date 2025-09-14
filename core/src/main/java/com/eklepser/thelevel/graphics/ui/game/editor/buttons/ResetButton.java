package com.eklepser.thelevel.graphics.ui.game.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.game.editor.Editor;
import com.eklepser.thelevel.util.Resources;

public class ResetButton extends TextButton {
    public ResetButton(Editor editor) {
        super("Reset", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Resetting");
                editor.getCodeTable().getCodeLines().forEach(
                    codeLine -> codeLine.setCompleting(false));
                editor.getExecutor().stop();
                editor.getStatusLabel().setText("Status: ");
                editor.getRunButton().setRunning(false);
                editor.getLevel().reset();
            }
        });
    }
}
