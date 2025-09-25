package com.eklepser.thelevel.logic.world.world;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.Layout;
import com.eklepser.thelevel.graphics.game.world.WorldScreen;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.collision.CollisionContext;
import com.eklepser.thelevel.logic.world.collision.CollisionManager;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.logic.world.common.GameMap;
import com.eklepser.thelevel.logic.world.common.MapLoader;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;
import java.util.List;

public class World extends GameMap {
    private final WorldScreen screen;
    private final CollisionManager collisionManager;
    private final Entity player;
    private final List<Collidable> collidables;
    private int selectedLevelId;
    private final List<LevelConfiguration> levelConfigs;

    public World(WorldConfiguration config, Game game) {
        super(config, game);
        player = new Entity(config.startPosX, config.startPosY,
            "world/entity/target.png");
        collidables = new ArrayList<>();
        levelConfigs = Configuration.from(LevelConfiguration.class, Resources.LEVEL_CONFIG);
        // Order is important! Screen -> map loader -> collision manager -> processors.
        screen = new WorldScreen(game, this);
        MapLoader.loadCollidables(this, collidables);
        collisionManager = new CollisionManager(this);
        screen.addProcessor(new WorldProcessor(game, this));
    }

    @Override
    public void setupCamera() {
        float cameraX = width * Layout.TILE_SIZE / 2.0f;
        float cameraY = height * Layout.TILE_SIZE / 2.0f;
        float zoom = config.cameraZoom;
        camera.setToOrtho(false, Layout.VIEWPORT_WIDTH / zoom,
            Layout.VIEWPORT_HEIGHT / zoom);
        camera.position.set(cameraX, cameraY, 0);
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
        collisionManager.update();
        player.update();
        player.act(delta);
    }

    @Override
    public CollisionContext getCollisionContext() {
        return new CollisionContext(collidables, List.of(player), false);
    }

    // Class logic:
    public LevelConfiguration getSelectedLevelConfig() {
        return levelConfigs.stream()
            .filter(config -> config.id == selectedLevelId)
            .findFirst()
            .orElse(null);
    }

    // Getters & setters:
    public WorldScreen getScreen() {
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
