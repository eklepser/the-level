package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.data.IO.UserDataIO;
import game.data.level.LevelData;
import game.data.IO.LevelDataIO;
import game.data.user.*;
import game.data.world.WorldData;
import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.collision.CollisionHandler;
import game.scene.common.logic.collision.zone.WorldZoneFactory;
import game.scene.common.logic.entity.Entity;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.level.rendering.LevelScreen;
import game.scene.world.logic.event.WorldTurnEvent;

import java.util.List;
import java.util.Map;

public final class World extends AbstractWorld {
    private final CollisionContext collisionContext;
    private final CollisionHandler collisionHandler;

    private final Vector2 startPos;

    private final UserData userData;
    private final Map<String, LevelStatus> levelProgressMap;
    private final Map<String, LevelData> levelDataMap;

    private LevelStatus selectedLevelStatus;
    private LevelData selectedLevelData;

    public World(WorldData worldData) {
        super(worldData);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        userData = UserDataIO.loadUserData();
        levelProgressMap = userData.progressData.getStatusMap();
        levelDataMap = LevelDataIO.loadDataMap("assets/world");

        // start pos calculating
        if (userData.worldPosition.x != 0 && userData.worldPosition.y != 0) {
            startPos = new Vector2(userData.worldPosition.x, userData.worldPosition.y);
        }
        else {
            startPos = map.getStartPos();
        }

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
        fire(new WorldTurnEvent());
    }

    @Override
    protected void loadZones(TileMap map) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(WorldZoneFactory.worldZone(tile,  this));
        }
    }

    public void startLevel() {
        if (selectedLevelStatus == null) return;
        if (selectedLevelStatus.equals(LevelStatus.LOCKED)) return;
        progressManager.saveWorldPosition();
        ScreenNavigator.gotoScreen(new LevelScreen(selectedLevelData));
    }

    public void setSelectedLevel(String selectedLevelTag) {
        selectedLevelData = levelDataMap.get(selectedLevelTag);
        selectedLevelStatus = levelProgressMap.get(selectedLevelTag);
        if (selectedLevelStatus == null) selectedLevelStatus = LevelStatus.LOCKED;
    }

    public LevelStatus getSelectedLevelStatus() {
        return selectedLevelStatus;
    }

    public LevelData getSelectedLevelData() {
        return selectedLevelData;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
