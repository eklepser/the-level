package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.game.level.LevelScreen;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.collision.CollisionContext;
import com.eklepser.thelevel.logic.world.collision.CollisionManager;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.graphics.Layout;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final LevelConfiguration config;
    private final LevelScreen screen;
    //private final CollisionManager collisionManager;
    private final List<Entity> entities;
    private final List<Entity> entitiesToAdd;
    private final List<Collidable> collidables;

    public Level(LevelConfiguration config, LevelScreen screen) {
//        super(config, game);
        this.config = config;
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        collidables = new ArrayList<>();
        // Order is important! Screen -> map loader -> collision manager.
        this.screen = screen;
//        MapLoader.loadCollidables(this, collidables);
//        collisionManager = new CollisionManager(this);

        spawnEntity(config.startPosX, config.startPosY);
    }

//    @Override
//    public void draw() {
//        renderer.render();
//        batch.begin();
//        entities.forEach(entity -> entity.draw(batch, 1.0f));
//        batch.end();
//    }

    //@Override
    public void update(float delta) {
        //collisionManager.update();
        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    //@Override
    public CollisionContext getCollisionContext() {
        return new CollisionContext(collidables, entities, true);
    }

    // Class logic:
    public void reset() {
        entities.clear();
        //spawnEntity(config.startPosX, config.startPosY);
    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, "world/entity/target.png");
        entities.add(entity);
    }

    // Getters:
    public LevelScreen getScreen() { return screen; }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public List<Collidable> getCollidables() { return collidables; }

    public Executor getExecutor() {
        return screen.getRoot().getEditor().getExecutor();
    }

    public LevelConfiguration getConfig() {
        return config;
    }
}
