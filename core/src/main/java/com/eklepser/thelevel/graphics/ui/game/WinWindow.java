package com.eklepser.thelevel.graphics.ui.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Resources;

public class WinWindow extends Window {
    public WinWindow(Game game) {
        super("", Resources.getSkin());
        float editorWidth = Constants.VIEWPORT_WIDTH * Constants.EDITOR_MENU_SCALE;
        setSize(Constants.VIEWPORT_WIDTH - editorWidth, Constants.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);

        TextLabel winLabel = new TextLabel("YOU WIN!");
        add(winLabel).pad(10).width(Constants.VIEWPORT_WIDTH / 8.0f).align(Align.center);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);

        row();
        add(new CloseLevelButton(game)).width(Constants.VIEWPORT_WIDTH / 8.0f).padBottom(20);
    }

    public void toggle() { setVisible(!isVisible()); }
}
