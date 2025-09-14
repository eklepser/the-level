package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
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
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, Constants.VIEWPORT_HEIGHT / 2.0f, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(level.getMap());
        renderer.setView(camera);
        this.batch = renderer.getBatch();

        collisionManager = new CollisionManager(level);

        spawnEntities();
    }

    public void draw() {
        renderer.render();
        batch.begin();
        level.draw(batch);
        batch.end();
    }

    public void act(float delta) {
       level.update(delta);
    }

    private void spawnEntities() {
        Entity hero1 = new Entity(new Vector2(2, 2), Constants.TILE_SIZE, "world/hero.png", collisionManager);
        level.addEntity(hero1);
    }
}
