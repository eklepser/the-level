package com.eklepser.thelevel.graphics.ui.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.common.HelpWindow;
import com.eklepser.thelevel.util.Resources;

public class HelpButton extends TextButton {
    public HelpButton(HelpWindow helpWindow) {
        super("Help", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                helpWindow.toggle();
            }
        });
    }
}
