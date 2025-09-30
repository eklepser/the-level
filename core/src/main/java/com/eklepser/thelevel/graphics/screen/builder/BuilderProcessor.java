package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.eklepser.thelevel.graphics.render.TileMap;

public class BuilderProcessor extends InputAdapter {
    private final BuilderScreen screen;
    private final TileMap map;
    private int previousKey;

    public BuilderProcessor(BuilderScreen screen, TileMap map) {
        this.screen = screen;
        this.map = map;
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
}
