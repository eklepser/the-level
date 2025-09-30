package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.selection.SelectionScreen;
import com.eklepser.thelevel.util.Direction;

public class BuilderProcessor extends InputAdapter {
    private final Game game;
    private final BuilderScreen screen;
    private final TileMap map;
    private int previousKey;

    public BuilderProcessor(Game game, BuilderScreen screen) {
        this.game = game;
        this.screen = screen;
        map = screen.getMap();
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        float zoomChange = amountY * 0.05f;
        float newZoom = screen.getCamera().zoom + zoomChange;
        newZoom = MathUtils.clamp(newZoom, 0.1f, 2.0f);

        screen.getCamera().zoom = newZoom;
        screen.getCamera().update();

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            game.setScreen(new SelectionScreen(game));
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screen.getStage().setKeyboardFocus(null);
        return false;
    }
}
