package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.AbstractWorld;
import game.common.logic.collision.CollisionContext;
import game.common.logic.collision.CollisionHandler;
import game.common.logic.collision.zone.WorldZoneFactory;
import game.common.logic.entity.Entity;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.tilemap.ZoneTile;
import game.resources.LevelLoader;
import game.scene.level.logic.LevelConfigurationOld;
import game.scene.world.logic.event.OnLevelEntranceEvent;

import java.util.List;
import java.util.Map;

public final class World extends AbstractWorld {
    private final CollisionContext collisionContext;
    private final CollisionHandler collisionHandler;

    private final Vector2 startPos;

    private final Map<String, LevelConfigurationOld> levelConfigs;
    private LevelConfigurationOld selectedLevelConfig;

    public World(WorldConfiguration config) {
        super(config);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        startPos = map.getStartPos();

        levelConfigs = LevelLoader.loadConfigurations("assets/world");

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
        selectedLevelConfig = levelConfigs.get(selectedLevelTag);
        fire(new OnLevelEntranceEvent(selectedLevelConfig));
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
