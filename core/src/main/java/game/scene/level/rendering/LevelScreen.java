package game.scene.level.rendering;

import game.config.Display;
import game.data.level.LevelData;
import game.scene.common.rendering.screen.GameCamera;
import game.scene.common.rendering.screen.GameScreen;
import game.scene.level.logic.Level;
import game.scene.level.rendering.component.editor.LevelProcessor;

public final class LevelScreen extends GameScreen {
    private final GameCamera camera;
    private final Level level;

    private final LevelData levelData;

    public LevelScreen(LevelData levelData) {
        super(levelData.tileMap);

        camera = new GameCamera();
        this.levelData = levelData;
        level = new Level(levelData);

        LevelLayout layout = new LevelLayout(level);
        stage.addActor(layout);
        multiplexer.addProcessor(0, new LevelProcessor(layout.getEditor()));
    }

    @Override
    public void show() {
        float zoom = map.height / 16.0f;
        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE);
        camera.zoom = zoom;
        camera.offset(-zoom * Display.EDITOR_MENU_SCALE * Display.VIEWPORT_WIDTH / 2.0f , 0);
        camera.update();
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

    public LevelData getLevelData() {
        return levelData;
    }

    public Level getLevel() {
        return level;
    }
}

