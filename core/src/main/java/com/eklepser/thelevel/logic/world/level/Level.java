package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.CollisionContext;
import com.eklepser.thelevel.logic.world.collision.CollisionManager;
import com.eklepser.thelevel.logic.world.common.GameMap;
import com.eklepser.thelevel.logic.world.common.MapLoader;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.util.Layout;

import java.util.ArrayList;
import java.util.List;

public class Level extends GameMap {
    private final LevelScreen screen;
    private final List<Entity> entities;
    private final List<Entity> entitiesToAdd;
    private final CollisionManager collisionManager;
    private final List<Collidable> collidables;

    public Level(LevelConfiguration config, Game game) {
        super(config, game);
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        collidables = new ArrayList<>();
        // Order is important! Screen -> map loader -> collision manager.
        screen = new LevelScreen(game, this);
        MapLoader.loadCollidables(this, collidables);
        collisionManager = new CollisionManager(this);

        spawnEntity(config.startPosX, config.startPosY);
    }

    @Override
    public void draw() {
        renderer.render();
        batch.begin();
        entities.forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }

    @Override
    public void update(float delta) {
        collisionManager.update();
        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
            System.out.println("entities:");
            System.out.println(entities);
        }
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    @Override
    public CollisionContext getCollisionContext() {
        return new CollisionContext(collidables, entities);
    }

    // Class logic:
    public void reset() {
        entities.clear();
        spawnEntity(config.startPosX, config.startPosY);
    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, Layout.TILE_SIZE, "world/entity/target.png");
        entities.add(entity);
    }

    // Getters:
    public LevelScreen getScreen() { return screen; }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public List<Collidable> getCollidables() { return collidables; }

    public Executor getExecutor() {
        return screen.getRootTable().getEditor().getExecutor();
    }
}
