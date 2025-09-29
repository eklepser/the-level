//package com.eklepser.thelevel.logic.world.common;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
//import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//import com.eklepser.thelevel.logic.world.collision.CollisionSource;
//import com.eklepser.thelevel.util.Resources;
//
//public abstract class GameMap implements CollisionSource {
//    protected final Game game;
//    protected final Configuration config;
//    protected final TiledMap map;
//    protected final int width;
//    protected final int height;
//    protected final OrthographicCamera camera;
//    protected final OrthogonalTiledMapRenderer renderer;
//    protected final Batch batch;
//
//    // GameMap common constructor:
//    public GameMap(Configuration config, Game game) {
//        this.game = game;
//        this.config = config;
//
//        // Map setup:
//        map = new TmxMapLoader().load(Resources.MAP_DIRECTORY + config.mapName);
//        width = map.getProperties().get("width", Integer.class);
//        height = map.getProperties().get("height", Integer.class);
//
//        // Camera setup:
//        camera = new OrthographicCamera();
//        setupCamera();
//        camera.update();
//
//        // Renderer & batch setup:
//        renderer = new OrthogonalTiledMapRenderer(map);
//        renderer.setView(camera);
//        batch = renderer.getBatch();
//    }
//
//    // Abstract methods:
//    public abstract void setupCamera();
//
//    public abstract void draw();
//
//    public abstract void update(float delta);
//
//    // Getters:
//    public Configuration getConfig() {
//        return config;
//    }
//
//    public TiledMap getMap() {
//        return map;
//    }
//
//    public OrthographicCamera getCamera() {
//        return camera;
//    }
//
//    public OrthogonalTiledMapRenderer getRenderer() {
//        return renderer;
//    }
//
//    public Batch getBatch() {
//        return batch;
//    }
//
//    public Game getGame() {
//        return game;
//    }
//}
