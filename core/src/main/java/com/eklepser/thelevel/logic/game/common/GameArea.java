package com.eklepser.thelevel.logic.game.common;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.interaction.collision.Entity;
import com.eklepser.thelevel.logic.interaction.collision.Zone;

import java.util.List;

public abstract class GameArea {
    protected final Screen screen;
    protected final TiledMap map;
    protected final List<Rectangle> wall;
    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public GameArea(Screen screen, Configuration conf) {

    }
}
