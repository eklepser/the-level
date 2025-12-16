package game.scene.level.rendering.component;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.data.IO.Assets;
import game.scene.common.rendering.TableLayout;
import game.scene.level.rendering.LevelScreen;
import game.scene.level.rendering.component.editor.EditorButtonFactory;
import game.scene.level.window.HelpWindow;
import game.scene.level.window.WinWindow;

public final class LevelToolbar extends TableLayout {

    private final LevelScreen levelScreen;
    private final HelpWindow helpWindow;

    public LevelToolbar(LevelScreen levelScreen, HelpWindow helpWindow) {
        this.levelScreen = levelScreen;
        this.helpWindow = helpWindow;
        setup();
    }

    @Override
    public void setup() {
        row().colspan(2);
        add(EditorButtonFactory.createInvertViewButton(levelScreen)).expandX().fillX().padRight(24);
        add(EditorButtonFactory.createHelpButton(helpWindow)).expandX().fillX();
    }
}
