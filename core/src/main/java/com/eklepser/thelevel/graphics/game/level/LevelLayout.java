package com.eklepser.thelevel.graphics.game.level;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.Layout;
import com.eklepser.thelevel.graphics.game.level.bar.StatusBar;
import com.eklepser.thelevel.graphics.game.level.bar.ToolBar;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.graphics.game.level.editor.EditorLayout;
import com.eklepser.thelevel.logic.world.level.Level;

public class LevelLayout extends Layout {
    private final LevelScreen screen;
    private final EditorLayout editorLayout;
    private final ToolBar toolBar;
    private final TextLabel infoLabel;
    private final StatusBar statusBar;

    public LevelLayout(LevelScreen screen, Level level) {
        this.screen = screen;
        toolBar = new ToolBar(this);
        infoLabel = new TextLabel();
        statusBar = new StatusBar();
        // Init editor after all others!
        editorLayout = new EditorLayout(this, level);
        setup();
    }

    @Override
    protected void setup() {
        setFillParent(true);
        // Setup elements:
        infoLabel.setText(editorLayout.getLevel().getConfig().name);

        statusBar.left();
        ScrollPane scrollPane = new ScrollPane(statusBar);
        scrollPane.setOverscroll(true, false);
        scrollPane.setVelocityX(100);
        scrollPane.setScrollingDisabled(false, true);

        Table statusTable = new Table();
        statusTable.add(scrollPane);

        // Adding elements:
        row();
        add(toolBar).fillX().left();
        add(infoLabel);

        row();
        add(editorLayout).width(VIEWPORT_WIDTH * EDITOR_MENU_SCALE).top().expandY();
        add().expand();

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
