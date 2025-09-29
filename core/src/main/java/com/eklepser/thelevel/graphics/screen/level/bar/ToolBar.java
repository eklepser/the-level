package com.eklepser.thelevel.graphics.screen.level.bar;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.eklepser.thelevel.graphics.screen.Layout;
import com.eklepser.thelevel.graphics.screen.level.LevelLayout;
import com.eklepser.thelevel.graphics.screen.level.editor.buttons.HelpButton;
import com.eklepser.thelevel.util.Resources;

public class ToolBar extends Layout {
    private final LevelLayout root;

    public ToolBar(LevelLayout root) {
        this.root = root;
        setup();
    }

    @Override
    protected void setup() {
        add(new TextButton("View", Resources.getSkin())).expandX().fillX();
        add(new TextButton("Tools", Resources.getSkin())).expandX().fillX();
        add(new HelpButton(root.getScreen().getHelpWindow())).expandX().fillX();
    }
}
