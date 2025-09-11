package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Constants;

import java.util.List;

public class GameField {
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final List<Entity> entities;
    private final Batch batch;

    public GameField(String mapPath, List<Entity> entities) {
        map = new TmxMapLoader().load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,Constants.EDITOR_MENU_SCALE * Constants.VIEWPORT_HEIGHT, 0);
        camera.update();
        renderer.setView(camera);

        this.batch = renderer.getBatch();
        this.entities = entities;
        spawnEntities();
    }

    private void spawnEntities() {
        Entity hero1 = new Entity("world/hero.png", Vector2.Zero, Constants.TILE_SIZE);
        entities.add(hero1);
        Entity hero2 = new Entity("world/hero.png", new Vector2(2, 3), Constants.TILE_SIZE);
        entities.add(hero2);
    }

    public void render() {
        renderer.render();
        batch.begin();
        entities.forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }

    public void act(float delta) {
        entities.forEach(entity -> entity.act(delta));
    }
}
