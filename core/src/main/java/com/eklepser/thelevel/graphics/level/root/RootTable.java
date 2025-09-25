package com.eklepser.thelevel.graphics.level.root;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.graphics.level.editor.Editor;
import com.eklepser.thelevel.logic.world.level.Level;

import static com.eklepser.thelevel.util.Layout.EDITOR_MENU_SCALE;
import static com.eklepser.thelevel.util.Layout.VIEWPORT_WIDTH;

public class RootTable extends Table {
    private final LevelScreen screen;
    private final ToolBar toolBar;
    private final TextLabel infoLabel;
    private final StatusBar statusBar;
    private final Editor editor;

    public RootTable(LevelScreen screen, Level level) {
        this.screen = screen;
        toolBar = new ToolBar(this);
        infoLabel = new TextLabel("");
        statusBar = new StatusBar();
        editor = new Editor(this, level);

        setFillParent(true);
        setupLayout();
    }

    private void setupLayout() {
        // Setup elements:
        infoLabel.setText(editor.getLevel().getConfig().name);

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
        add(editor).width(VIEWPORT_WIDTH * EDITOR_MENU_SCALE).top().expandY();
        add().expand();

        row().colspan(2);
        add(statusTable).left().padRight(10).height(32);
    }

    public LevelScreen getScreen() { return screen; }

    public StatusBar getStatusBar() { return statusBar; }

    public Editor getEditor() { return editor; }
}
