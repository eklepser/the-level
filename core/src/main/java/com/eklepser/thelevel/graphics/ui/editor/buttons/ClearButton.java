package com.eklepser.thelevel.graphics.ui.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.ui.editor.CodeField;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.util.Resources;

public class ClearButton extends TextButton {
    public ClearButton(Executor executor, TextLabel statusLabel, CodeField codeField) {
        super("Clear", Resources.getSkin());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clearing");
                executor.stop();
                codeField.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                codeField.getCodeLines().forEach(codeLine -> codeLine.setText(""));
                statusLabel.setText("Status: ");
            }
        });
    }
}
