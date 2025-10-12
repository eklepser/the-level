package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import game.data.IO.Assets;

public final class CodeLine extends TextField {
    private final Color lineColor = Color.PURPLE;
    private static final int MAX_LENGTH = 32;

    public CodeLine() {
        super("", Assets.getSkin().get("code-field", TextField.TextFieldStyle.class));
        getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));
        setColor(lineColor);
        setTextFieldFilter((field, c) -> {
            if (c == '\b' || c == '\u007F') return true;
            return field.getText().length() < MAX_LENGTH;
        });
    }

    // Class logic:
    public void setCompleting(boolean isCompleting) {
        if (isCompleting) setColor(Color.WHITE);
        else setColor(lineColor);
    }
}
