package game.scene.level.rendering;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.config.GraphicsConstants;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.ColoredString;
import game.scene.level.rendering.component.StatusBar;
import game.scene.level.rendering.component.ToolBar;
import game.scene.level.rendering.component.editor.EditorLayout;
import game.scene.level.logic.Level;

public class LevelLayout extends TableLayout {
    private final LevelScreen screen;
    private final EditorLayout editorLayout;
    private final ToolBar toolBar;
    private final ColoredString infoString;
    private final StatusBar statusBar;

    public LevelLayout(LevelScreen screen, Level level) {
        this.screen = screen;
        toolBar = new ToolBar(this);
        infoString = new ColoredString();
        statusBar = new StatusBar();

        // Init editor after all others!
        editorLayout = new EditorLayout(this, level);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        // Setup elements:
        infoString.setText("/_2 " + editorLayout.getLevel().getConfig().title);

        statusBar.left();
        ScrollPane scrollPane = new ScrollPane(statusBar);
        scrollPane.setOverscroll(true, false);
        scrollPane.setVelocityX(100);
        scrollPane.setScrollingDisabled(false, true);

        Table statusTable = new Table();
        statusTable.add(scrollPane);

        // Adding elements:
        row().colspan(2);
        add(toolBar).width(GraphicsConstants.VIEWPORT_WIDTH * GraphicsConstants.EDITOR_MENU_SCALE).left().top().fillX();
        add().fillX();

        row();
        add(editorLayout).width(GraphicsConstants.VIEWPORT_WIDTH * GraphicsConstants.EDITOR_MENU_SCALE).top().expandY();
        add(infoString).expand().top();

        row().colspan(2);
        add(statusTable).left().padRight(10).height(32);
    }

    // Getters:
    public LevelScreen getScreen() { return screen; }

    public EditorLayout getEditor() {
        return editorLayout;
    }

    public StatusBar getStatusBar() { return statusBar; }
}
