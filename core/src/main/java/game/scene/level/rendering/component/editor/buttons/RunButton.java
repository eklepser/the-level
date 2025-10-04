package game.scene.level.rendering.component.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.level.rendering.component.editor.EditorLayout;
import game.resources.Assets;

public class RunButton extends TextButton {
    public RunButton(EditorLayout editorLayout) {
        super("Run", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                editorLayout.run();
            }
        });
    }
}
