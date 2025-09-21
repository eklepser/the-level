package com.eklepser.thelevel.graphics.game.root;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.graphics.game.editor.Editor;
import com.eklepser.thelevel.logic.world.level.Level;

import static com.eklepser.thelevel.util.Layout.EDITOR_MENU_SCALE;
import static com.eklepser.thelevel.util.Layout.VIEWPORT_WIDTH;

public class RootTable extends Table {
    private final GameScreen screen;
    private final ToolBar toolBar;
    private final TextLabel infoLabel;
    private final StatusBar statusBar;
    private final Editor editor;

    public RootTable(GameScreen screen, Level level) {
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
        infoLabel.setText(editor.getLevel().getConf().name());

        statusBar.left();
        ScrollPane scrollPane = new ScrollPane(statusBar);
        scrollPane.setVelocityX(20);
        scrollPane.setScrollingDisabled(false, true);

        Table statusTable = new Table();
        statusTable.add(new TextLabel("History:"));
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

    public GameScreen getScreen() { return screen; }

    public StatusBar getStatusRow() { return statusBar; }

    public Editor getEditor() { return editor; }
}
