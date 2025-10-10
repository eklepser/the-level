package game.scene.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;
import game.scene.common.logic.event.EventSource;
import game.scene.common.logic.event.GameEvent;
import game.scene.common.rendering.tilemap.TileMap;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScene<E extends GameEvent> extends EventSource<E> {
    protected final TileMap map;
    protected final Vector2 startPos;

    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public abstract void update(float delta);

    protected abstract void loadZones(TileMap map);

    protected AbstractScene(TileMap tileMap) {
        this.map = tileMap;
        this.startPos = tileMap.getStartPos();
        this.zones = new ArrayList<>();
        this.entities = new ArrayList<>();
        loadZones(map);
    }
}
