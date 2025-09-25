package com.eklepser.thelevel.graphics.level.root.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.level.root.editor.Editor;
import com.eklepser.thelevel.util.Resources;

public class RunButton extends TextButton {
    public RunButton(Editor editor) {
        super("Run", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                editor.run();
            }
        });
    }
}
