package game.scene.level.rendering;

import game.common.GameCamera;
import game.common.GameScreen;
import game.config.Display;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;

public final class LevelScreen extends GameScreen {
    private final GameCamera camera;
    private final Level level;

    private final LevelConfiguration config;

    public LevelScreen(LevelConfiguration config) {
        super(config.tileMap);

        camera = new GameCamera();
        this.config = config;
        level = new Level(config, this);

        stage.addActor(new LevelLayout(level));
        //multiplexer.addProcessor(new EditorProcessor());
    }

    @Override
    public void show() {
        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE);
        camera.offset(-Display.EDITOR_MENU_SCALE * Display.VIEWPORT_WIDTH / 2.0f, 0);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    protected void update(float delta) {
        level.update(delta);
    }

    @Override
    protected void draw() {
        batch.begin();
        map.draw(batch, 10);
        level.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }

    // Getters:
    public LevelConfiguration getConfig() {
        return config;
    }

    public Level getLevel() {
        return level;
    }
}

