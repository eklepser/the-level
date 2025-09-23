package com.eklepser.thelevel.graphics.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eklepser.thelevel.logic.interaction.collision.LevelCollisionManager;
import com.eklepser.thelevel.logic.game.level.Level;
import com.eklepser.thelevel.util.Layout;

public class LevelMap {
    private final Level level;
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;
    private final Batch batch;
    private final LevelCollisionManager levelCollisionManager;

    public LevelMap(Level level) {
        this.level = level;

        float zoom = level.getConf().getCameraZoom();
        float levelWidth = level.getConf().getSize().x * Layout.TILE_SIZE;
        float cameraX = (levelWidth - Layout.VIEWPORT_WIDTH * Layout.EDITOR_MENU_SCALE / zoom) / 2.0f;
        float cameraY = level.getConf().getSize().y * Layout.TILE_SIZE / 2;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Layout.VIEWPORT_WIDTH / zoom,
            Layout.VIEWPORT_HEIGHT / zoom);
        camera.position.set(cameraX, cameraY, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(level.getMap());
        renderer.setView(camera);
        batch = renderer.getBatch();

        levelCollisionManager = new LevelCollisionManager(level);
    }

    public void draw() {
        renderer.render();
        batch.begin();
        level.draw(batch);
        batch.end();
    }

    public void act(float delta) {
        levelCollisionManager.updateLevel(level);
        level.update(delta);
    }
}
