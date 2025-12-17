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

    private final LevelScreen levelScreen;

    private final EditorLayout editorLayout;

    private final LevelToolbar levelToolbar;
    private final ColoredString infoString;
    private final LevelStatusbar levelStatusbar;

    private final WinWindow winWindow;

    private boolean editorOnRight = false;

    public LevelLayout(LevelScreen screen, Level level, HelpWindow helpWindow, WinWindow winWindow) {
        this.level = level;
        level.subscribe(this);

        this.levelScreen = screen;

        levelToolbar = new LevelToolbar(screen, helpWindow);
        infoString = new ColoredString();
        levelStatusbar = new LevelStatusbar();

        this.winWindow = winWindow;

        // Init editor after all others!
        editorLayout = new EditorLayout(this, level);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);
        setDebug(true);

        infoString.setText("/_2 " + level.getLevelData().metadata.title);
        infoString.center();

        levelStatusbar.left();

        ScrollPane scrollPane = new ScrollPane(levelStatusbar);
        scrollPane.setOverscroll(true, false);
        scrollPane.setVelocityX(100);
        scrollPane.setScrollingDisabled(false, true);

        Table statusBarWrapper = new Table();
        statusBarWrapper.add(scrollPane).fillX().expandX();

        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        float mainWidth = Display.VIEWPORT_WIDTH - editorWidth;

        if (editorOnRight) {
            add().width(mainWidth).fill();
            add(levelToolbar).width(editorWidth).fillX().top().left();
            row();
            add(infoString).width(mainWidth).expandX().top().fillX();
            add(editorLayout).width(editorWidth).top().expandY().fillX();
        } else {
            add(levelToolbar).width(editorWidth).fillX().top().left();
            add().width(mainWidth).fill();
            row();
            add(editorLayout).width(editorWidth).top().expandY().fillX();
            add(infoString).width(mainWidth).expandX().top().fillX();
        }

        row().colspan(2);
        add(statusBarWrapper).height(32).fillX().expandX();
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
            winWindow.toggle();
        }
        if (event instanceof DeathEvent) {
            levelStatusbar.die();
        }
    }

    // Getters:
    public LevelScreen getLevelScreen() {
        return levelScreen;
    }

    public EditorLayout getEditor() {
        return editorLayout;
    }

    public LevelStatusbar getStatusBar() { return levelStatusbar; }
}
