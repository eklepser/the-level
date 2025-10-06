package game.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.entity.Entity;
import game.common.logic.zone.Zone;
import game.common.rendering.GameScreen;
import game.common.tilemap.BaseConfiguration;
import game.common.tilemap.TileMap;
import game.common.tilemap.ZoneTile;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel {
    protected final BaseConfiguration config;
    protected final GameScreen screen;

    protected final TileMap map;
    protected final Vector2 startPos;

    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public AbstractLevel(BaseConfiguration config, GameScreen screen) {
        this.config = config;
        this.screen = screen;

        map = config.tileMap;
        startPos = map.getStartPos();

        zones = new ArrayList<>();
        entities = new ArrayList<>();
        //zones = loadZones(map, );
    }

    public void loadZones(TileMap map) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(Zone.from(tile));
        }
    }
}
