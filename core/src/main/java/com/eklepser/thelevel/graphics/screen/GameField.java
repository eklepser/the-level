package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Constants;

import java.util.List;

public class GameField {
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final List<Entity> entities;

    public GameField(String mapPath, List<Entity> entities) {
        map = new TmxMapLoader().load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map);
        this.entities = entities;

        camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,Constants.EDITOR_MENU_SCALE * Constants.VIEWPORT_WIDTH, 0);
        camera.update();
        renderer.setView(camera);
    }

    public void render() {
        renderer.render();

        Batch batch = (renderer).getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        entities.forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }

    public void act(float delta) {
        entities.forEach(entity -> entity.act(delta));
    }
}
