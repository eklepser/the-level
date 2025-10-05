package game.scene.world.logic;

import com.badlogic.gdx.math.Vector2;
import game.config.Paths;
import game.common.rendering.tilemap.TileMap;
import game.common.rendering.GameScreen;
import game.scene.world.rendering.WorldScreen;
import game.common.logic.collision.Collidable;
import game.common.logic.MapConfiguration;
import game.common.logic.entity.Entity;
import game.scene.level.logic.LevelConfiguration;

import java.util.ArrayList;
import java.util.List;

public final class World {
    private final GameScreen screen;
    private final TileMap map;
    //private final CollisionManager collisionManager;
    private final Vector2 startPos;
    private final Entity player;
    private final List<Collidable> collidables;
    private int selectedLevelId;
    private final List<LevelConfiguration> levelConfigs;

    public World(WorldConfiguration config, WorldScreen screen) {
        //super(config, game);
        map = screen.getMap();
        startPos = map.getStartPos();
        player = new Entity((int) startPos.x, (int) startPos.y,
            "world/entity/target.png");
        collidables = new ArrayList<>();
        levelConfigs = MapConfiguration.listFrom(LevelConfiguration.class, Paths.LEVEL_CONFIG);
        // Order is important! Screen -> map loader -> collision manager -> processors.
        this.screen = screen;
//        MapLoader.loadCollidables(this, collidables);
//        collisionManager = new CollisionManager(this);
//        screen.addProcessor(new WorldProcessor(game, this));
    }

//    @Override
//    public void draw() {
//        renderer.render();
//        batch.begin();
//        player.draw(batch, 1.0f);
//        batch.end();
//    }

//    @Override
//    public void update(float delta) {
//        collisionManager.update();
//        player.update();
//        player.act(delta);
//    }

//    @Override
//    public CollisionContext getCollisionContext() {
//        return new CollisionContext(collidables, List.of(player), false);
//    }
//
//    // Class logic:
//    public LevelConfiguration getSelectedLevelConfig() {
//        return levelConfigs.stream()
//            .filter(config -> config.id == selectedLevelId)
//            .findFirst()
//            .orElse(null);
//    }

    // Getters & setters:
    public GameScreen getScreen() {
        return screen;
    }

    public Entity getPlayer() {
        return player;
    }

    public int getSelectedLevelId() {
        return selectedLevelId;
    }

    public void setSelectedLevelId(int selectedLevelId) {
        this.selectedLevelId = selectedLevelId;
    }

    public List<LevelConfiguration> getLevelConfigurations() {
        return levelConfigs;
    }
}
