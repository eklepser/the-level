package com.eklepser.thelevel.graphics.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.util.Resources;

public class InputField extends TextField {

    public InputField(String text, int maxLength) {
        super(text, Resources.getSkin().get("code-field",TextField.TextFieldStyle.class));

        setMaxLength(maxLength);
        getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));
    }
}
