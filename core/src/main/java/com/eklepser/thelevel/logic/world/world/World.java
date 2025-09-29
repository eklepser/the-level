package com.eklepser.thelevel.logic.world.world;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.graphics.screen.world.WorldScreen;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final GameScreen screen;
    private final TileMap map;
    //private final CollisionManager collisionManager;
    private final Vector2 startPos;
    private final Entity player;
    private final List<Collidable> collidables;
    private int selectedLevelId;
    private final List<LevelConfiguration> levelConfigs;

    public World(WorldConfiguration config, WorldScreen screen) {
        //super(config, game);
        map = screen.getMap();
        startPos = map.getStartPos();
        player = new Entity((int) startPos.x, (int) startPos.y,
            "world/entity/target.png");
        collidables = new ArrayList<>();
        levelConfigs = Configuration.listFrom(LevelConfiguration.class, Resources.LEVEL_CONFIG);
        // Order is important! Screen -> map loader -> collision manager -> processors.
        this.screen = screen;
//        MapLoader.loadCollidables(this, collidables);
//        collisionManager = new CollisionManager(this);
//        screen.addProcessor(new WorldProcessor(game, this));
    }

//    @Override
//    public void draw() {
//        renderer.render();
//        batch.begin();
//        player.draw(batch, 1.0f);
//        batch.end();
//    }

//    @Override
//    public void update(float delta) {
//        collisionManager.update();
//        player.update();
//        player.act(delta);
//    }

//    @Override
//    public CollisionContext getCollisionContext() {
//        return new CollisionContext(collidables, List.of(player), false);
//    }
//
//    // Class logic:
//    public LevelConfiguration getSelectedLevelConfig() {
//        return levelConfigs.stream()
//            .filter(config -> config.id == selectedLevelId)
//            .findFirst()
//            .orElse(null);
//    }

    // Getters & setters:
    public GameScreen getScreen() {
        return screen;
    }

    public Entity getPlayer() {
        return player;
    }

    public int getSelectedLevelId() {
        return selectedLevelId;
    }

    public void setSelectedLevelId(int selectedLevelId) {
        this.selectedLevelId = selectedLevelId;
    }

    public List<LevelConfiguration> getLevelConfigurations() {
        return levelConfigs;
    }
}
