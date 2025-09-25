package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eklepser.thelevel.util.Layout;

public abstract class GameMap {
    protected final Configuration config;
    protected final TiledMap map;
    protected final OrthographicCamera camera;
    protected final OrthogonalTiledMapRenderer renderer;
    protected final Batch batch;

    // GameMap common constructor:
    public GameMap(Configuration config) {
        this.config = config;

        // Map setup:
        map = new TmxMapLoader().load(config.mapPath);

        // Camera setup:
        float zoom = config.cameraZoom;
        //float cameraX = (levelWidth - Layout.VIEWPORT_WIDTH * Layout.EDITOR_MENU_SCALE / zoom) / 2.0f;
        float cameraX = config.width * Layout.TILE_SIZE / 2.0f + config.cameraOffsetX;
        float cameraY = config.height * Layout.TILE_SIZE / 2.0f + config.cameraOffsetY;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Layout.VIEWPORT_WIDTH / zoom,
            Layout.VIEWPORT_HEIGHT / zoom);
        camera.position.set(cameraX, cameraY, 0);
        camera.update();

        // Renderer setup:
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        // Batch setup:
        batch = renderer.getBatch();
    }

    // Abstract methods:
    public abstract void draw();

    public abstract void update(float delta);

    // Getters:
    public Configuration getConfig() {
        return config;
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public Batch getBatch() {
        return batch;
    }
}
