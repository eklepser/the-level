package com.eklepser.thelevel.graphics.ui.code_editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.util.Resources;

public class CodeLine extends TextField {
    final Color lineColor = Color.PURPLE;

    public CodeLine() {
        super("", Resources.getSkin().get("code-field", TextField.TextFieldStyle.class));
        this.getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/components/code_field_cursor.png")));
        this.setColor(lineColor);
    }

    public void setCompleting(boolean isCompleting) {
        if (isCompleting) this.setColor(Color.WHITE);
        else this.setColor(lineColor);
    }
}
