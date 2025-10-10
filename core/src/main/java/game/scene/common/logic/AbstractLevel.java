package game.scene.common.logic;

import com.badlogic.gdx.math.Vector2;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;
import game.scene.common.logic.event.EventSource;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.level.data.LevelData;
import game.scene.level.logic.event.LevelEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel extends EventSource<LevelEvent> {
    protected final LevelData levelData;

    protected final TileMap map;
    protected final Vector2 startPos;

    protected final List<Zone> zones;
    protected final List<Entity> entities;

    public abstract void update(float delta);

    protected abstract void loadZones(TileMap map);

    public AbstractLevel(LevelData levelData) {
        this.levelData = levelData;

        map = levelData.tileMap;
        startPos = map.getStartPos();

        zones = new ArrayList<>();
        loadZones(map);
        entities = new ArrayList<>();
    }
}
