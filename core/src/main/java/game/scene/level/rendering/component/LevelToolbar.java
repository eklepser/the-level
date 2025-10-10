package game.scene.level.rendering.component;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.resources.Assets;
import game.scene.common.rendering.TableLayout;
import game.scene.level.rendering.LevelLayout;
import game.scene.level.rendering.component.editor.EditorButtonFactory;
import game.scene.level.window.HelpWindow;

public final class LevelToolbar extends TableLayout {
    private final LevelLayout root;

    public LevelToolbar(LevelLayout root) {
        this.root = root;
        setup();
    }

    @Override
    public void setup() {
        add(new TextButton("View", Assets.getSkin())).expandX().fillX();
        add(new TextButton("Tools", Assets.getSkin())).expandX().fillX();
        add(EditorButtonFactory.createHelpButton(new HelpWindow())).expandX().fillX();
    }
}
