package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Wall;
import com.eklepser.thelevel.logic.world.zone.Zone;
import com.eklepser.thelevel.util.Layout;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final LevelConfiguration conf;
    private final GameScreen playScreen;
    private final TiledMap map;
    private final List<Entity> entities;
    private final List<Entity> entitiesToAdd;
    private final List<Rectangle> walls;
    private final List<Zone> zones;

    public Level(GameScreen screen, LevelConfiguration conf) {
        this.conf = conf;
        playScreen = screen;
        this.map = new TmxMapLoader().load(conf.mapPath());

        LevelLoader loader = new LevelLoader(this);
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        walls = loader.loadWalls("walls");
        zones = loader.loadZones("zones");
        walls.forEach(wall -> zones.add(new Wall(wall)));
        spawnEntity(conf.getStartPos().cpy());
    }

    public void draw(Batch batch) {
        entities.forEach(entity -> entity.draw(batch, 1.0f));
    }

    public void update(float delta) {
        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
            System.out.println("entities:");
            System.out.println(entities);
        }
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    public void reset() {
        entities.clear();
        spawnEntity(conf.getStartPos().cpy());
    }

    public void spawnEntity(Vector2 worldPos) {
        Entity entity = new Entity(worldPos, Layout.TILE_SIZE, "world/entity/target.png");
        entities.add(entity);
    }

    public LevelConfiguration getConf() { return conf; }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public List<Rectangle> getWalls() { return walls; }

    public TiledMap getMap() { return map; }

    public List<Zone> getZones() { return zones; }

    public GameScreen getPlayScreen() { return playScreen; }
}
