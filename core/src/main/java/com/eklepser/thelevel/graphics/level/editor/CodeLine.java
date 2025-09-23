package com.eklepser.thelevel.graphics.level.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eklepser.thelevel.util.Resources;

public class CodeLine extends TextField {
    private final Color lineColor = Color.PURPLE;
    private static final int MAX_LENGTH = 32;

    public CodeLine() {
        super("", Resources.getSkin().get("code-field", TextField.TextFieldStyle.class));
        getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));
        setColor(lineColor);

        setTextFieldFilter((field, c) -> {
            if (c == '\b' || c == '\u007F') return true;
            return field.getText().length() < MAX_LENGTH;
        });
    }

    public void setCompleting(boolean isCompleting) {
        if (isCompleting) setColor(Color.WHITE);
        else setColor(lineColor);
    }

    private float getTextY(BitmapFont font) {
        return getY() + getStyle().background.getTopHeight() +
            (getHeight() - getStyle().background.getTopHeight() - getStyle().background.getBottomHeight()) / 2 +
            font.getCapHeight() / 2;
    }
}
