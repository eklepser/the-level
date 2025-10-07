package game.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.entity.Entity;
import game.common.logic.collision.zone.Zone;
import game.common.logic.event.EventSource;
import game.common.rendering.tilemap.BaseConfiguration;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.tilemap.ZoneTile;
import game.scene.level.logic.Level;
import game.scene.level.logic.event.LevelEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel extends EventSource<LevelEvent> {
    protected final BaseConfiguration config;

    protected final TileMap map;
    protected final Vector2 startPos;

    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public AbstractLevel(BaseConfiguration config) {
        this.config = config;

        map = config.tileMap;
        startPos = map.getStartPos();

        zones = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public void loadZones(TileMap map) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(Zone.from(tile, (Level) this));
        }
    }
}
