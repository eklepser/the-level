package com.eklepser.thelevel.graphics.screen.level.window;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.eklepser.thelevel.graphics.screen.level.ExitLevelButton;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.util.Resources;

public class WinWindow extends Window {
    private final Game game;
    private final TextLabel winLabel;
    private final ExitLevelButton exitLevelButton;

    public WinWindow(Game game) {
        super("", Resources.getSkin());
        this.game = game;
        winLabel = new TextLabel("YOU WIN!");
        exitLevelButton = new ExitLevelButton(game);

        setupLayout();
    }

    private void setupLayout() {
        float editorWidth = TableLayout.VIEWPORT_WIDTH * TableLayout.EDITOR_MENU_SCALE;
        setSize(TableLayout.VIEWPORT_WIDTH - editorWidth, TableLayout.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);

        add(winLabel).pad(10).width(TableLayout.VIEWPORT_WIDTH / 8.0f).align(Align.center);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);

        row();
        add(exitLevelButton).width(TableLayout.VIEWPORT_WIDTH / 8.0f)
            .height(TableLayout.VIEWPORT_HEIGHT / 16.0f).padBottom(20);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
