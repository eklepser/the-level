package com.eklepser.thelevel.graphics.game.root;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.game.editor.Editor;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.util.Layout;

import static com.eklepser.thelevel.util.Layout.EDITOR_MENU_SCALE;

public class RootTable extends Table {
    private final Editor editor;
    private final StatusRow statusRow;

    public RootTable(Level level) {
        statusRow = new StatusRow();
        this.editor = new Editor(this, level);;
        setFillParent(true);

        add(editor).width(Layout.VIEWPORT_WIDTH * EDITOR_MENU_SCALE).top().fillY();
        add().expand();
        row().colspan(2);
        add(statusRow).right().padRight(10).row();
    }

    public StatusRow getStatusRow() { return statusRow; }

    public Editor getEditor() { return editor; }
}
