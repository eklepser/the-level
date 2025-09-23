package com.eklepser.thelevel.logic.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.graphics.world.WorldScreen;
import com.eklepser.thelevel.logic.game.common.GameArea;
import com.eklepser.thelevel.logic.game.level.LevelConfiguration;
import com.eklepser.thelevel.logic.game.level.LevelLoader;
import com.eklepser.thelevel.logic.game.world.zone.WorldZone;
import com.eklepser.thelevel.logic.interaction.collision.Entity;
import com.eklepser.thelevel.util.ConfigurationLoader;
import com.eklepser.thelevel.util.Layout;

import java.util.List;

public class World extends GameArea {
    private final Entity player;
    private final List<Rectangle> walls;
    private final List<WorldZone> zones;
    private int selectedLevel;
    private final List<LevelConfiguration> levelsInfo;

    public World(WorldScreen screen, WorldConfiguration conf) {
        super(screen, conf);
        zones = WorldLoader.loadZones(this, "zones");
        walls = WorldLoader.loadWalls(this, "walls");
        levelsInfo = ConfigurationLoader.loadLevelConfigurations();
        //walls.forEach(wall -> zones.add(new Wall(wall)));

        player = new Entity(conf.getStartPos().cpy(), Layout.TILE_SIZE, "world/entity/target.png");
        entities.add(player);
//        this.conf = conf;
//        worldScreen = screen;
//        map = new TmxMapLoader().load(conf.mapPath());
//        walls = new ArrayList<>();
//        zones = new ArrayList<>();
    }

    public void draw(Batch batch) {
        player.draw(batch, 1.0f);
    }

    public void update(float delta) {
        player.update();
        player.act(delta);
    }

    public WorldConfiguration getConf() {
        return (WorldConfiguration) conf;
    }

    public WorldScreen getWorldScreen() {
        return (WorldScreen) screen;
    }

    public TiledMap getMap() {
        return map;
    }

    public Entity getPlayer() {
        return player;
    }

    public List<Rectangle> getWalls() {
        return walls;
    }

    public List<WorldZone> getZones() {
        return zones;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public List<LevelConfiguration> getLevelsInfo() {
        return levelsInfo;
    }
}
