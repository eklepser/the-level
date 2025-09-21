package com.eklepser.thelevel.graphics.game.root;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.eklepser.thelevel.util.Resources;

public class ToolBar extends Table {
    public ToolBar() {
        add(new TextButton("View", Resources.getSkin())).expandX().fillX();
        add(new TextButton("Tools", Resources.getSkin())).expandX().fillX();
        add(new TextButton("Help", Resources.getSkin())).expandX().fillX();
    }
}
