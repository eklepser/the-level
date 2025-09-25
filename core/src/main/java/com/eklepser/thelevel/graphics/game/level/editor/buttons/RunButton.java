package com.eklepser.thelevel.graphics.game.level.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.game.level.editor.EditorLayout;
import com.eklepser.thelevel.util.Resources;

public class RunButton extends TextButton {
    public RunButton(EditorLayout editorLayout) {
        super("Run", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                editorLayout.run();
            }
        });
    }
}
