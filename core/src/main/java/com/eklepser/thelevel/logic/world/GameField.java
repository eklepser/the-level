package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;
    private final OrthographicCamera camera;
    private final Batch batch;
    private final List<Entity> entities;
    private final CollisionManager collisionManager;

    private final List<Rectangle> walls = new ArrayList<>();

    public GameField(String mapPath, List<Entity> entities) {
        map = new TmxMapLoader().load(mapPath);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, Constants.VIEWPORT_HEIGHT / 2.0f, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);

        collisionManager = new CollisionManager(walls);

        this.batch = renderer.getBatch();
        this.entities = entities;
        loadCollisions();
        spawnEntities();
    }

    private void spawnEntities() {
        Entity hero1 = new Entity(new Vector2(2, 2), Constants.TILE_SIZE, "world/hero.png", collisionManager);
        entities.add(hero1);
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

    private void loadCollisions() {
        MapLayer layer = map.getLayers().get("collisions");
        if (layer == null) {
            System.out.println("Layer 'collisions' not found");
            return;
        }
        System.out.println(layer.getObjects().getCount());
        for (MapObject object : layer.getObjects()) {
            System.out.println();
            if (object instanceof RectangleMapObject rectObj) {
                Rectangle rect = rectObj.getRectangle();
                walls.add(rect);
                System.out.println("New collidable: " + rect);
            }
        }
    }
}
