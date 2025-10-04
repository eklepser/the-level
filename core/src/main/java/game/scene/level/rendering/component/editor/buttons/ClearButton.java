package game.scene.level.rendering.component.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.level.rendering.component.editor.EditorLayout;
import game.resources.Assets;

public class ClearButton extends TextButton {
    public ClearButton(EditorLayout editorLayout) {
        super("Clear", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                editorLayout.clearRunning();
            }
        });
    }
}
