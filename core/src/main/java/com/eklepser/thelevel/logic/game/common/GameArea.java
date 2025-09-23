package com.eklepser.thelevel.logic.game.common;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.logic.game.level.LevelLoader;
import com.eklepser.thelevel.logic.interaction.collision.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class GameArea {
    protected final Screen screen;
    protected final Configuration conf;
    protected final TiledMap map;
    protected final List<Entity> entities;

    public GameArea(Screen screen, Configuration conf) {
        this.screen = screen;
        this.conf = conf;
        map = new TmxMapLoader().load(conf.getMapPath());

        entities = new ArrayList<>();
    }

    public Screen getScreen() {
        return screen;
    }

    public TiledMap getMap() {
        return map;
    }

    public Configuration getConf() {
        return conf;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
