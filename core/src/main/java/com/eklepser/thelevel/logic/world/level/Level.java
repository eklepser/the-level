package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Zone;
import com.eklepser.thelevel.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final GameScreen playScreen;
    private final TiledMap map;
    private final Vector2 startPos;
    private final List<Entity> entities;
    private final List<Rectangle> walls;
    private final List<Zone> zones;

    public Level(GameScreen screen, LevelDescription desc) {
        playScreen = screen;
        this.map = new TmxMapLoader().load(desc.mapPath());
        startPos = desc.getStartPos();

        LevelLoader loader = new LevelLoader(this);
        this.entities = new ArrayList<>();
        this.walls = loader.loadWalls("walls");
        this.zones =  loader.loadZones("zones");
        spawnEntity(startPos.cpy());
    }

    public void draw(Batch batch) {
        entities.forEach(entity -> entity.draw(batch, 1.0f));
    }

    public void update(float delta) {
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    public void reset() {
        entities.clear();
        spawnEntity(startPos.cpy());
    }

    public void spawnEntity(Vector2 worldPos) {
        Entity entity = new Entity(worldPos, Constants.TILE_SIZE, "world/hero.png");
        entities.add(entity);
    }

    public List<Entity> getEntities() { return entities; }

    public List<Rectangle> getWalls() { return walls; }

    public TiledMap getMap() { return map; }

    public List<Zone> getZones() { return zones; }

    public GameScreen getPlayScreen() { return playScreen; }
}
