package com.eklepser.thelevel.graphics.screen.selection;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.screen.menu.MenuScreen;

public class SelectionProcessor extends InputAdapter {
    private final Game game;
    private int previousKey;

    public SelectionProcessor(Game game) {
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MenuScreen(game));
        }

        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
