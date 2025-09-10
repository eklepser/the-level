package com.eklepser.thelevel.graphics.ui.common;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelpWindow extends Window {
    public HelpWindow() {
        super("", Resources.getSkin());
        float editorWidth = Constants.VIEWPORT_WIDTH * Constants.EDITOR_MENU_SCALE;
        this.setSize(Constants.VIEWPORT_WIDTH - editorWidth, Constants.VIEWPORT_HEIGHT);
        this.setPosition(editorWidth, 0);
        try {
            String text = Files.readString(Path.of("assets/ui/text/help-info.txt"));
            TextLabel helpLabel = new TextLabel(text);
            helpLabel.setWrap(true);
            this.add(helpLabel).pad(10).expand().fill();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setResizable(true);
        this.setVisible(false);
        this.setColor(0.5f, 0, 0.75f, 0.9f);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
