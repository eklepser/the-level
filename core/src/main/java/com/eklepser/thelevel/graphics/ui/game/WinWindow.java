package com.eklepser.thelevel.graphics.ui.game;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Resources;

public class WinWindow extends Window {
    public WinWindow() {
        super("", Resources.getSkin());
        float editorWidth = Constants.VIEWPORT_WIDTH * Constants.EDITOR_MENU_SCALE;
        setSize(Constants.VIEWPORT_WIDTH - editorWidth, Constants.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);

        //String text = Files.readString(Path.of("assets/ui/text/win.txt"));
        TextLabel helpLabel = new TextLabel("YOU WIN");
        helpLabel.setWrap(true);
        add(helpLabel).pad(10).expand().fill();

        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);
    }

    public void toggle() { setVisible(!isVisible()); }
}
