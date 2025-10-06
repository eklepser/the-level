package game.scene.level.logic;

import game.common.logic.AbstractLevel;
import game.common.logic.collision.CollisionHandler;
import game.common.logic.entity.Entity;
import game.common.logic.zone.Zone;
import game.common.tilemap.TileMap;
import game.common.tilemap.ZoneTile;
import game.scene.level.logic.editor.execution.Executor;
import game.scene.level.rendering.LevelLayout;
import game.scene.level.rendering.LevelScreen;

import java.util.ArrayList;
import java.util.List;

public final class Level extends AbstractLevel {
    private final List<Entity> entitiesToAdd;
    private final CollisionHandler collisionHandler;

    public Level(LevelConfiguration config, LevelScreen screen) {
        super(config, screen);
        entitiesToAdd = new ArrayList<>();
        collisionHandler = new CollisionHandler(map, zones, entities);

        loadZones(map);

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
    public void reset() {
        entities.clear();
        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, "tileset/target.png");
        entities.add(entity);
    }

    // Getters:
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
