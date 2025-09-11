package com.eklepser.thelevel.graphics.ui.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.ui.editor.CodeTable;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.util.Resources;

public class StopButton extends TextButton {
    public StopButton(Executor executor, TextLabel statusLabel, CodeTable codeTable) {
        super("Stop", Resources.getSkin());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Stopping");
                codeTable.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                executor.stop();
                statusLabel.setText("Status: ");
            }
        });
    }
}
