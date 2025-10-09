package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.common.logic.AbstractLevel;
import game.common.logic.collision.CollisionContext;
import game.common.logic.collision.CollisionHandler;
import game.common.logic.collision.zone.LevelZoneFactory;
import game.common.logic.collision.zone.WorldZoneFactory;
import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;
import game.common.rendering.screen.GameScreen;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.tilemap.ZoneTile;
import game.resources.LevelLoader;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;
import game.scene.level.logic.execution.Executor;
import game.scene.level.rendering.LevelLayout;
import game.scene.selection.logic.LevelMetadata;
import game.scene.world.rendering.WorldScreen;

import java.util.ArrayList;
import java.util.List;

public final class World extends AbstractLevel {
    private final CollisionContext collisionContext;
    private final CollisionHandler collisionHandler;

    private final Vector2 startPos;

    private int selectedLevelId;
    private final List<LevelMetadata> levels;

    public World(WorldConfiguration config) {
        super(config);

        collisionContext = new CollisionContext(map.collision, zones, entities);
        collisionHandler = new CollisionHandler(collisionContext);

        startPos = map.getStartPos();

        levels = LevelLoader.loadMetadata("data/builder");

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
