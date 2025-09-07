package com.eklepser.thelevel.graphics.ui.common;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.eklepser.thelevel.Resources;

public class TextLabel extends Label {
    public TextLabel(String text) {
        super(text, Resources.getSkin().get("text-label", Label.LabelStyle.class));
    }
}
