package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.graphics.level.WorldScreen;
import com.eklepser.thelevel.logic.world.Configuration;
import com.eklepser.thelevel.logic.world.GameMap;
import com.eklepser.thelevel.logic.world.GameScreen;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Collidable;
import com.eklepser.thelevel.util.Layout;

import java.util.List;

public class World extends GameMap {
    private final WorldScreen screen;
    private final Entity player;
    private int selectedLevelId;
    private final List<LevelConfiguration> levelConfigs;
    private final List<Collidable> collidables;

    public World(WorldConfiguration config, Game game) {
        super(config, game);
        player = new Entity(config.startPosX, config.startPosY,
            Layout.TILE_SIZE, "world/entity/target.png");
        levelConfigs = Configuration.from(LevelConfiguration.class, "world/level/levels.json");
        // Order is important! Screen -> collidables -> processors.
        screen = new WorldScreen(game, this);
        collidables = MapLoader.loadCollidables(this);
        screen.addProcessor(new WorldProcessor(game, this));
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
    public WorldScreen getScreen() {
        return screen;
    }

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
