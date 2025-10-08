package game.scene.level.logic;

import game.common.logic.collision.CollisionContext;
import game.common.logic.AbstractLevel;
import game.common.logic.collision.CollisionHandler;
import game.common.logic.entity.Entity;
import game.common.logic.collision.zone.Zone;
import game.common.logic.event.EventType;
import game.common.rendering.tilemap.TileMap;
import game.scene.level.logic.event.LevelEvent;
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

    public Level(LevelConfiguration config) {
        super(config);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        executor = new Executor(config, this);

        loadZones(map);
        entitiesToAdd = new ArrayList<>();


        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    public void update(float delta) {
        collisionHandler.update();
        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

    // Class logic:
    public void win() {
        System.out.println("Level: win");
        fire(new WinEvent());
        executor.stop();
    }

    public void runExecution(List<String> inputLines) {
        executor.runExecution(inputLines);
    }

    public void reset() {
        entities.clear();
        spawnEntity((int) startPos.x, (int) startPos.y);
        executor.stop();
    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, "tileset/target.png");
        entities.add(entity);
    }

    // Getters & setters:
    public CollisionContext getCollisionContext() {
        return collisionContext;
    }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public LevelConfiguration getConfig() { return (LevelConfiguration) config;}

    public TileMap getMap() {
        return map;
    }

    public List<Zone> getZones() {
        return zones;
    }
}
