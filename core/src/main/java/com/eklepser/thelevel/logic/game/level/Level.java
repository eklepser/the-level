package com.eklepser.thelevel.logic.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.logic.game.common.GameArea;
import com.eklepser.thelevel.logic.interaction.collision.Entity;
import com.eklepser.thelevel.logic.game.level.zone.Wall;
import com.eklepser.thelevel.logic.game.level.zone.LevelZone;
import com.eklepser.thelevel.util.Layout;

import java.util.ArrayList;
import java.util.List;

public class Level extends GameArea {
    private final List<Entity> entitiesToAdd;
    private final List<Rectangle> walls;
    private final List<LevelZone> zones;
    private final LevelScreen screen;

    public Level(LevelScreen screen, LevelConfiguration conf) {
        super(screen, conf);
        this.screen = screen;
        walls = LevelLoader.loadWalls(this, "walls");
        zones = LevelLoader.loadZones(this, "zones");
        entitiesToAdd = new ArrayList<>();
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

    public LevelScreen getScreen() { return screen; }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public List<Rectangle> getWalls() { return walls; }

    public TiledMap getMap() { return map; }

    public List<LevelZone> getZones() { return zones; }

}
