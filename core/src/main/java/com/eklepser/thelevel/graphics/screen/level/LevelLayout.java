package com.eklepser.thelevel.graphics.screen.level;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.screen.level.bar.StatusBar;
import com.eklepser.thelevel.graphics.screen.level.bar.ToolBar;
import com.eklepser.thelevel.graphics.util.ColoredString;
import com.eklepser.thelevel.graphics.screen.level.editor.EditorLayout;
import com.eklepser.thelevel.logic.world.level.Level;

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
        infoString.setText(editorLayout.getLevel().getConfig().title);

        statusBar.left();
        ScrollPane scrollPane = new ScrollPane(statusBar);
        scrollPane.setOverscroll(true, false);
        scrollPane.setVelocityX(100);
        scrollPane.setScrollingDisabled(false, true);

        Table statusTable = new Table();
        statusTable.add(scrollPane);

        // Adding elements:
        row().colspan(2);
        add(toolBar).width(VIEWPORT_WIDTH * EDITOR_MENU_SCALE).left().top().fillX();
        add().fillX();

        row();
        add(editorLayout).width(VIEWPORT_WIDTH * EDITOR_MENU_SCALE).top().expandY();
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
