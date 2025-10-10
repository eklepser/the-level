package game.scene.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;
import game.scene.common.logic.event.EventSource;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.world.data.WorldData;
import game.scene.world.logic.event.WorldEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld extends EventSource<WorldEvent> {
    protected final WorldData worldData;

    protected final TileMap map;
    protected final Vector2 startPos;

    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public abstract void update(float delta);

    protected abstract void loadZones(TileMap map);

    public AbstractWorld(WorldData worldData) {
        this.worldData = worldData;

        map = worldData.tileMap;
        startPos = map.getStartPos();

        zones = new ArrayList<>();
        loadZones(map);
        entities = new ArrayList<>();
    }
}
