package game.scene.level.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.tilemap.ZoneTile;
import game.scene.level.rendering.LevelLayout;
import game.scene.level.rendering.LevelScreen;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.collision.CollisionHandler;
import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private final LevelConfiguration config;
    private final LevelScreen screen;
    private final TileMap map;
    private final Vector2 startPos;

    private final List<Zone> zones;
    private final List<Entity> entities;
    private final List<Entity> entitiesToAdd;
    private final CollisionHandler collisionHandler;

    public Level(LevelConfiguration config, LevelScreen screen) {
        this.config = config;
        this.screen = screen;
        map = screen.getMap();

        startPos = map.getStartPos();
        zones = new ArrayList<>();
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();

        collisionHandler = new CollisionHandler(map, zones, entities);

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

    public void loadZones(TileMap map, LevelLayout layout) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(Zone.from(tile, layout));
        }
    }

    // Getters:
    public LevelScreen getScreen() { return screen; }

    public List<Entity> getEntities() { return entities; }

    public List<Entity> getEntitiesToAdd() { return entitiesToAdd; }

    public Executor getExecutor() {
        return screen.getLayout().getEditor().getExecutor();
    }

    public LevelConfiguration getConfig() {
        return config;
    }

    public TileMap getMap() {
        return map;
    }

    public List<Zone> getZones() {
        return zones;
    }
}
