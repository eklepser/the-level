package com.eklepser.thelevel.graphics.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.eklepser.thelevel.logic.world.World;
import com.eklepser.thelevel.logic.interaction.collision.WorldCollisionManager;
import com.eklepser.thelevel.util.Layout;

public class WorldMap {
    private final World world;
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;
    private final Batch batch;
    private final WorldCollisionManager collisionManager;
    //private final CollisionManager collisionManager;

    public WorldMap(World world) {
        this.world = world;

        float zoom = world.getConf().getCameraZoom();
        float cameraX = world.getConf().getSize().x * Layout.TILE_SIZE / 2;
        float cameraY = world.getConf().getSize().y * Layout.TILE_SIZE / 2;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Layout.VIEWPORT_WIDTH / zoom,
            Layout.VIEWPORT_HEIGHT / zoom);
        camera.position.set(cameraX, cameraY, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(world.getMap());
        renderer.setView(camera);
        this.batch = renderer.getBatch();

        collisionManager = new WorldCollisionManager(world);
    }

    public void draw() {
        renderer.render();
        batch.begin();
        world.draw(batch);
        batch.end();
    }

    public void act(float delta) {
        collisionManager.updateWorld();
        world.update(delta);
    }
//
//    public void act(float delta) {
//        collisionManager.update();
//        level.update(delta);
//    }
}
