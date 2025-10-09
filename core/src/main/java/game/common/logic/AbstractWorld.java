package game.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.collision.zone.LevelZoneFactory;
import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;
import game.common.logic.event.EventSource;
import game.common.rendering.tilemap.BaseConfiguration;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.tilemap.ZoneTile;
import game.scene.level.logic.Level;
import game.scene.level.logic.event.LevelEvent;
import game.scene.world.logic.event.WorldEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld extends EventSource<WorldEvent> {
    protected final BaseConfiguration config;

    protected final TileMap map;
    protected final Vector2 startPos;

    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public abstract void update(float delta);

    protected abstract void loadZones(TileMap map);

    public AbstractWorld(BaseConfiguration config) {
        this.config = config;

        map = config.tileMap;
        startPos = map.getStartPos();

        zones = new ArrayList<>();
        loadZones(map);
        entities = new ArrayList<>();
    }
}
