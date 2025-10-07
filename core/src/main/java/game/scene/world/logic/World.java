package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.collision.CollisionContext;
import game.common.logic.collision.CollisionHandler;
import game.common.logic.collision.zone.Zone;
import game.common.rendering.tilemap.ZoneTile;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.screen.GameScreen;
import game.resources.LevelLoader;
import game.scene.level.rendering.LevelLayout;
import game.scene.selection.logic.LevelMetadata;
import game.scene.world.rendering.WorldScreen;
import game.common.logic.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public final class World {
    private final GameScreen screen;

    private final TileMap map;
    private final Vector2 startPos;

    private int selectedLevelId;
    private final List<LevelMetadata> levels;

    private final List<Zone> zones;
    private final List<Entity> entities;
    private final CollisionHandler collisionHandler;

    public World(WorldConfiguration config, WorldScreen screen) {
        this.screen = screen;

        map = screen.getMap();
        startPos = map.getStartPos();

        levels = LevelLoader.loadMetadata("data/builder");

        zones = new ArrayList<>();
        entities = new ArrayList<>();

        CollisionContext collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        spawnEntity((int) startPos.x, (int) startPos.y);
    }

    public void update(float delta) {
        collisionHandler.update();
        entities.forEach(Entity::update);
        entities.forEach(entity -> entity.act(delta));
    }

//    // Class logic:
//    public LevelConfiguration getSelectedLevelConfig() {
//        return levelConfigs.stream()
//            .filter(config -> config.id == selectedLevelId)
//            .findFirst()
//            .orElse(null);
//    }

    public void spawnEntity(int worldPosX, int worldPosY) {
        Entity entity = new Entity(worldPosX, worldPosY, "tileset/target.png");
        entities.add(entity);
    }

    public void loadZones(TileMap map, LevelLayout layout) {
        for (ZoneTile tile : map.zones) {
            if (tile.type.equals("start")) continue;
            zones.add(Zone.from(tile));
        }
    }

    // Getters & setters:
    public GameScreen getScreen() {
        return screen;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public int getSelectedLevelId() {
        return selectedLevelId;
    }

    public void setSelectedLevelId(int selectedLevelId) {
        this.selectedLevelId = selectedLevelId;
    }
}
