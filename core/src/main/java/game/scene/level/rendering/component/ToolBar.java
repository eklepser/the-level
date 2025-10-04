package game.scene.level.rendering.component;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.scene.common.rendering.TableLayout;
import game.scene.level.rendering.component.editor.buttons.HelpButton;
import game.resources.Assets;
import game.scene.level.rendering.LevelLayout;

public class ToolBar extends TableLayout {
    private final LevelLayout root;

    public ToolBar(LevelLayout root) {
        this.root = root;
        setup();
    }

    @Override
    public void setup() {
        add(new TextButton("View", Assets.getSkin())).expandX().fillX();
        add(new TextButton("Tools", Assets.getSkin())).expandX().fillX();
        add(new HelpButton(root.getScreen().getHelpWindow())).expandX().fillX();
    }
}
