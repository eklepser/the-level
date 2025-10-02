package com.eklepser.thelevel.graphics.util;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.eklepser.thelevel.util.Resources;

public class TextLabel extends Label {
    public TextLabel() {
        super("", Resources.getSkin().get("text-label", Label.LabelStyle.class));
    }

    public TextLabel(String text) {
        super(text, Resources.getSkin().get("text-label", Label.LabelStyle.class));
    }

    public TextLabel(String text, boolean isWrapping) {
        super(text, Resources.getSkin().get("text-label", Label.LabelStyle.class));
        setWrap(isWrapping);
    }
}
