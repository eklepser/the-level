package game.scene.level.logic;

import game.config.Paths;
import game.data.level.LevelData;
import game.data.user.LevelStatus;
import game.data.user.UserData;
import game.data.user.UserDataIO;
import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.collision.CollisionHandler;
import game.scene.common.logic.collision.zone.LevelZoneFactory;
import game.scene.common.logic.collision.zone.WinZone;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.level.logic.command.Command;
import game.scene.level.logic.event.NewCommandEvent;
import game.scene.level.logic.event.WinEvent;
import game.scene.level.logic.execution.Executor;

import java.util.ArrayList;
import java.util.List;

public final class Level extends AbstractLevel {
    private final CollisionContext collisionContext;
    private final CollisionHandler collisionHandler;

    private final Executor executor;

    private final List<Entity> entitiesToAdd;

    private boolean isWin = false;

    public Level(LevelData levelData) {
        super(levelData);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        executor = new Executor(levelData, this);

        entitiesToAdd = new ArrayList<>();

        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }
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
            zones.add(LevelZoneFactory.levelZone(tile,  this));
        }
    }

    public void runExecution(List<String> inputLines) {
        executor.runExecution(inputLines);
    }

    public void reset() {
        executor.clearActions();

        entities.clear();
        spawnEntity((int) startPos.x, (int) startPos.y);

        isWin = false;
        for (Zone zone : zones) {
            if (zone instanceof WinZone winZone) {
                winZone.setActivated(false);
            }
        }
    }

    public void win() {
        isWin = true;
        System.out.println("ON WIN");

        fire(new WinEvent());

        UserData userData = UserDataIO.loadUserData(Paths.USER_DATA);
        userData.progressData.setStatus(levelData.metadata.tag, LevelStatus.COMPLETED);
        UserDataIO.saveUserData(userData, Paths.USER_DATA);
    }

    public void setExecutionDelay(float delay) {
        executor.setExecutionDelay(delay);
    }

    public CollisionContext getCollisionContext() { return collisionContext; }

    public List<Entity> getEntities() { return entities; }

    public LevelData getLevelData() { return levelData;}

    public TileMap getMap() { return map; }

    public List<Zone> getZones() { return zones; }

    public boolean isWin() { return isWin; }
}
