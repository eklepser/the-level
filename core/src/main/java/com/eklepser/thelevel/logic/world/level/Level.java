package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.Entity;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final TiledMap map;
    private final List<Entity> entities;
    private final List<Rectangle> walls;
    private final List<Rectangle> zones;

    public Level(String mapPath) {
        this.map = new TmxMapLoader().load(mapPath);
        LevelLoader loader = new LevelLoader(this);
        this.entities = new ArrayList<>();
        this.walls = loader.loadLayer("walls");
        this.zones =  loader.loadLayer("zones");
    }

    public void draw(Batch batch) {
        entities.forEach(entity -> entity.draw(batch, 1.0f));
    }

    public void update(float delta) {
        entities.forEach(entity -> entity.act(delta));
    }

    public List<Entity> getEntities() { return entities; }

    public List<Rectangle> getWalls() { return walls; }

    public void addEntity(Entity entity) { entities.add(entity); }

    public TiledMap getMap() { return map; }
}
