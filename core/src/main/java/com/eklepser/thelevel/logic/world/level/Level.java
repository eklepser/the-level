package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.level.LevelScreen;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.CollisionManager;
import com.eklepser.thelevel.logic.world.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final LevelConfiguration config;
    private final LevelScreen screen;
    private final TileMap map;
    private final Vector2 startPos;
    private final List<Entity> entities;
    private final List<Entity> entitiesToAdd;
    private final CollisionManager collisionManager;

    public Level(LevelConfiguration config, LevelScreen screen) {
        this.config = config;
        this.screen = screen;
        map = screen.getMap();

        startPos = map.getStartPos();
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        collisionManager = new CollisionManager(map, entities);

        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    public void update(float delta) {
        collisionManager.update();
        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    // Class logic:
    public void reset() {
        entities.clear();
        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, "world/entity/target.png");
        entities.add(entity);
    }

    // Getters:
    public LevelScreen getScreen() { return screen; }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public Executor getExecutor() {
        return screen.getLayout().getEditor().getExecutor();
    }

    public LevelConfiguration getConfig() {
        return config;
    }
}
