package com.eklepser.thelevel.graphics.game.root;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.game.editor.Editor;
import com.eklepser.thelevel.logic.world.level.Level;

import static com.eklepser.thelevel.util.Layout.EDITOR_MENU_SCALE;
import static com.eklepser.thelevel.util.Layout.VIEWPORT_WIDTH;

public class RootTable extends Table {
    private final Editor editor;
    private final StatusRow statusRow;

    public RootTable(Level level) {
        //setDebug(true);
        statusRow = new StatusRow();
        editor = new Editor(this, level);;
        setFillParent(true);

        add(editor).width(VIEWPORT_WIDTH * EDITOR_MENU_SCALE).top().fillY();
        add().expand();
        row().colspan(2);

        statusRow.left();

        ScrollPane scrollPane = new ScrollPane(statusRow);
        scrollPane.setVelocityX(20);
        scrollPane.setScrollingDisabled(false, true);
        add(scrollPane).left().padRight(10).height(32).row();
    }

    public StatusRow getStatusRow() { return statusRow; }

    public Editor getEditor() { return editor; }
}
