package game.scene.level.rendering;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.config.Display;
import game.scene.common.logic.event.EventListener;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.ColoredString;
import game.scene.level.logic.Level;
import game.scene.level.logic.command.Command;
import game.scene.level.logic.event.*;
import game.scene.level.rendering.component.LevelStatusbar;
import game.scene.level.rendering.component.LevelToolbar;
import game.scene.level.rendering.component.editor.EditorLayout;
import game.scene.level.window.HelpWindow;
import game.scene.level.window.WinWindow;

public final class LevelLayout extends TableLayout implements EventListener<LevelEvent> {
    private final Level level;

    private final EditorLayout editorLayout;

    private final LevelToolbar levelToolbar;
    private final ColoredString infoString;
    private final LevelStatusbar levelStatusbar;

    private boolean editorOnRight = false;

    public LevelLayout(LevelScreen screen, Level level, HelpWindow helpWindow) {
        this.level = level;
        level.subscribe(this);

        levelToolbar = new LevelToolbar(screen, helpWindow);
        infoString = new ColoredString();
        levelStatusbar = new LevelStatusbar();

        // Init editor after all others!
        editorLayout = new EditorLayout(this, level);

        setup();
    }

    @Override
    public void setup() {
        setDebug(true);
        setFillParent(true);

        infoString.setText("/_2 " + level.getLevelData().metadata.title);
        infoString.center();

        levelStatusbar.left();
        ScrollPane scrollPane = new ScrollPane(levelStatusbar);
        scrollPane.setOverscroll(true, false);
        scrollPane.setVelocityX(100);
        scrollPane.setScrollingDisabled(false, true);

        Table statusTable = new Table();
        statusTable.add(scrollPane);

        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        float mainWidth = Display.VIEWPORT_WIDTH - editorWidth;

        columnDefaults(0).width(editorOnRight ? mainWidth : editorWidth).expandX();
        columnDefaults(1).width(editorOnRight ? editorWidth : mainWidth).expandX();

        if (editorOnRight) {
            add().fill();
            add(levelToolbar).fillX().top().left();
            row();
            add(infoString).expandX().top().fillX();
            add(editorLayout).top().expandY().fillX();
        } else {
            add(levelToolbar).fillX().top().left();
            add().fill();
            row();
            add(editorLayout).top().expandY().fillX();
            add(infoString).expandX().top().fillX();
        }

        row().colspan(2);
        add(statusTable).left().padRight(10).height(32).expandX().fillX();
    }

    public void toggleEditorSide(boolean editorOnRight) {
        this.editorOnRight = editorOnRight;
        clear();
        setup();
    }

    @Override
    public void onEvent(LevelEvent event) {
        if (event instanceof ExecutionStartEvent startEvent) {
            levelStatusbar.start();
            String status = startEvent.translationResult.status();
            editorLayout.getStatusLabel().setText("Status:\n" + status);
        }
        if (event instanceof LevelTurnEvent commandEvent) {
            Command command = commandEvent.command;

            editorLayout.getCodeLayout().clearCompleting();
            editorLayout.getCodeLayout().setCompletingLine(command.getLineNum(), true);

            levelStatusbar.update(command);
        }
        if (event instanceof WinEvent) {
            levelStatusbar.win();
        }
        if (event instanceof DeathEvent) {
            levelStatusbar.die();
        }
    }

    // Getters:
    public EditorLayout getEditor() {
        return editorLayout;
    }

    public LevelStatusbar getStatusBar() { return levelStatusbar; }
}
