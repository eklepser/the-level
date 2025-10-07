package game.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.entity.Entity;
import game.common.logic.collision.zone.Zone;
import game.common.rendering.screen.GameScreen;
import game.common.rendering.tilemap.BaseConfiguration;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.tilemap.ZoneTile;
import game.scene.level.LevelEventSource;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel extends LevelEventSource {
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
    }

    public void loadZones(TileMap map) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(Zone.from(tile));
        }
    }
}
