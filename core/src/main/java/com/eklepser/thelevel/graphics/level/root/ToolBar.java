package com.eklepser.thelevel.graphics.level.root;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.eklepser.thelevel.graphics.level.editor.buttons.HelpButton;
import com.eklepser.thelevel.util.Resources;

public class ToolBar extends Table {
    private final RootTable rootTable;

    public ToolBar(RootTable rootTable) {
        this.rootTable = rootTable;

        add(new TextButton("View", Resources.getSkin())).expandX().fillX();
        add(new TextButton("Tools", Resources.getSkin())).expandX().fillX();
        add(new HelpButton(rootTable.getScreen().getHelpWindow())).expandX().fillX();
    }
}
