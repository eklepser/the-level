package com.eklepser.thelevel.graphics.ui.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.util.Layout;
import com.eklepser.thelevel.util.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelpWindow extends Window {
    public HelpWindow(Game game) {
        super("", Resources.getSkin());
        float editorWidth = Layout.VIEWPORT_WIDTH * Layout.EDITOR_MENU_SCALE;
        setSize(Layout.VIEWPORT_WIDTH - editorWidth, Layout.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);
        try {
            String text = Files.readString(Path.of("assets/ui/text/help-info.txt"));
            TextLabel helpLabel = new TextLabel(text);
            helpLabel.setWrap(true);
            add(helpLabel).pad(10).expand().fill();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        row();
        add(new CloseLevelButton(game)).width(Layout.VIEWPORT_WIDTH / 4.0f)
            .height(Layout.VIEWPORT_HEIGHT / 16.0f).padBottom(40);
        setResizable(true);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);
    }

    public void toggle() { setVisible(!isVisible()); }
}
