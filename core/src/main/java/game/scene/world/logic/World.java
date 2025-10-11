package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.config.Paths;
import game.data.level.LevelData;
import game.data.level.LevelDataIO;
import game.data.user.CompletionStatus;
import game.data.user.LevelStatus;
import game.data.user.UserDataIO;
import game.data.user.UserProgressData;
import game.data.world.WorldData;
import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.collision.CollisionHandler;
import game.scene.common.logic.collision.zone.WorldZoneFactory;
import game.scene.common.logic.entity.Entity;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.level.rendering.LevelScreen;
import game.scene.world.logic.event.OnLevelEntranceEvent;

import java.util.List;
import java.util.Map;

public final class World extends AbstractWorld {
    private final CollisionContext collisionContext;
    private final CollisionHandler collisionHandler;

    private final Vector2 startPos;

    private final Map<String, LevelStatus> levelProgressMap;
    private final Map<String, LevelData> levelDataMap;

    private LevelStatus selectedLevelStatus;
    private LevelData selectedLevelData;

    public World(WorldData worldData) {
        super(worldData);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        levelProgressMap = UserDataIO.loadUserData(Paths.USER_DATA).progressData.getStatusMap();
        levelDataMap = LevelDataIO.loadDataMap("assets/world");

        startPos = map.getStartPos();
        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    protected void onTurnMade() {
        collisionHandler.wallsUpdate();
        entities.forEach(Entity::update);
        collisionHandler.zonesUpdate();
    }

    @Override
    protected void loadZones(TileMap map) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(WorldZoneFactory.worldZone(tile,  this));
        }
    }

    public void startLevel() {
        if (selectedLevelStatus.equals(LevelStatus.LOCKED)) return;
        ScreenNavigator.gotoScreen(new LevelScreen(selectedLevelData));
    }

    public void setSelectedLevelConfig(String selectedLevelTag) {
        selectedLevelData = levelDataMap.get(selectedLevelTag);
        selectedLevelStatus = levelProgressMap.get(selectedLevelTag);

        if (selectedLevelStatus == null) selectedLevelStatus = LevelStatus.LOCKED;

        fire(new OnLevelEntranceEvent(selectedLevelData,  selectedLevelStatus));
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
