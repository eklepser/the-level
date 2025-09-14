package com.eklepser.thelevel.graphics.ui.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eklepser.thelevel.logic.world.collision.CollisionManager;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.util.Constants;

public class GameField {
    private final Level level;
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;
    private final Batch batch;
    private final CollisionManager collisionManager;

    public GameField(Level level) {
        this.level = level;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH * 0.6f, Constants.VIEWPORT_HEIGHT * 0.6f);
        float levelWidth = level.getSize().x * Constants.TILE_SIZE;
        float cameraX = (levelWidth - Constants.VIEWPORT_WIDTH * Constants.EDITOR_MENU_SCALE * 0.6f) / 2.0f;
        float cameraY = level.getSize().y * Constants.TILE_SIZE / 2;
        camera.position.set(cameraX, cameraY, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(level.getMap());
        renderer.setView(camera);
        this.batch = renderer.getBatch();

        collisionManager = new CollisionManager(level);
    }

    public void draw() {
        renderer.render();
        batch.begin();
        level.draw(batch);
        batch.end();
    }

    public void act(float delta) {
        collisionManager.update();
        level.update(delta);
    }
}
