package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.resources.LevelLoader;
import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.collision.CollisionHandler;
import game.scene.common.logic.collision.zone.WorldZoneFactory;
import game.scene.common.logic.entity.Entity;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.level.data.LevelData;
import game.scene.world.data.WorldData;
import game.scene.world.logic.event.OnLevelEntranceEvent;

import java.util.List;
import java.util.Map;

public final class World extends AbstractWorld {
    private final CollisionContext collisionContext;
    private final CollisionHandler collisionHandler;

    private final Vector2 startPos;

    private final Map<String, LevelData> levelDataMap;
    private LevelData selectedLevelData;

    public World(WorldData worldData) {
        super(worldData);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        startPos = map.getStartPos();

        levelDataMap = LevelLoader.loadDataMap("assets/world");

        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    @Override
    public void update(float delta) {
        collisionHandler.update();
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    @Override
    protected void loadZones(TileMap map) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(WorldZoneFactory.worldZone(tile,  this));
        }
    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, "tileset/target.png");
        entities.add(entity);
    }

    public void setSelectedLevelConfig(String selectedLevelTag) {
        selectedLevelData = levelDataMap.get(selectedLevelTag);
        fire(new OnLevelEntranceEvent(selectedLevelData));
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
