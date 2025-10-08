package game.scene.level.rendering;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.common.logic.event.EventListener;
import game.common.rendering.TableLayout;
import game.common.rendering.component.ColoredString;
import game.config.Display;
import game.scene.level.logic.Level;
import game.scene.level.logic.command.Command;
import game.scene.level.logic.event.LevelEvent;
import game.scene.level.logic.event.NewCommandEvent;
import game.scene.level.rendering.component.LevelStatusbar;
import game.scene.level.rendering.component.LevelToolbar;
import game.scene.level.rendering.component.editor.EditorLayout;

public final class LevelLayout extends TableLayout implements EventListener<LevelEvent> {
    private final Level level;

    private final EditorLayout editorLayout;

    private final LevelToolbar levelToolbar;
    private final ColoredString infoString;
    private final LevelStatusbar levelStatusbar;

    public LevelLayout(Level level) {
        this.level = level;
        level.subscribe(this);

        levelToolbar = new LevelToolbar(this);
        infoString = new ColoredString();
        levelStatusbar = new LevelStatusbar();

        // Init editor after all others!
        editorLayout = new EditorLayout(this, level);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        // Setup elements:
        infoString.setText("/_2 " + level.getConfig().title);

        levelStatusbar.left();
        ScrollPane scrollPane = new ScrollPane(levelStatusbar);
        scrollPane.setOverscroll(true, false);
        scrollPane.setVelocityX(100);
        scrollPane.setScrollingDisabled(false, true);

        Table statusTable = new Table();
        statusTable.add(scrollPane);

        // Adding elements:
        row().colspan(2);
        add(levelToolbar).width(Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE).left().top().fillX();
        add().fillX();

        row();
        add(editorLayout).width(Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE).top().expandY();
        add(infoString).expand().top();

        row().colspan(2);
        add(statusTable).left().padRight(10).height(32);
    }

    @Override
    public void onEvent(LevelEvent event) {
        if (event instanceof NewCommandEvent commandEvent) {
            Command command = commandEvent.command;

            editorLayout.getCodeLayout().clearCompleting();
            editorLayout.getCodeLayout().setCompletingLine(command.getLineNum(), true);

            levelStatusbar.update(command);
        }
    }

    // Getters:
    public EditorLayout getEditor() {
        return editorLayout;
    }

    public LevelStatusbar getStatusBar() { return levelStatusbar; }
}
