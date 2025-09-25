package com.eklepser.thelevel.logic.world.level;

import com.eklepser.thelevel.logic.world.Configuration;
import com.eklepser.thelevel.logic.world.GameMap;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Collidable;
import com.eklepser.thelevel.util.Layout;

import java.util.List;

public class World extends GameMap {
    private final Entity player;
    private final List<Collidable> collidables;
    private int selectedLevelId;
    private final List<LevelConfiguration> levelConfigs;

    public World(WorldConfiguration config) {
        super(config);
        player = new Entity(config.startPosX, config.startPosY,
            Layout.TILE_SIZE, "world/entity/target.png");
        collidables = MapLoader.loadCollidables(this);
        levelConfigs = Configuration.from(LevelConfiguration.class, "world/level/levels.json");
    }

    @Override
    public void draw() {
        renderer.render();
        batch.begin();
        player.draw(batch, 1.0f);
        batch.end();
    }

    @Override
    public void update(float delta) {
        //collisionManager.updateWorld();
        System.out.println(collidables.size());
        player.update();
        player.act(delta);
    }

    public LevelConfiguration getSelectedLevelConfig() {
        return levelConfigs.stream()
            .filter(config -> config.id == selectedLevelId)
            .findFirst()
            .orElse(null);
    }

    // Getters:
    public Entity getPlayer() {
        return player;
    }

    public int getSelectedLevelId() {
        return selectedLevelId;
    }

    public List<LevelConfiguration> getLevelConfigurations() {
        return levelConfigs;
    }
}
