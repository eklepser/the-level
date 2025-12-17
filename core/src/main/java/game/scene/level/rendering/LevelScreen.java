package game.scene.level.rendering;

import com.badlogic.gdx.math.Matrix4;
import game.config.Display;
import game.data.level.LevelData;
import game.scene.common.rendering.screen.GameCamera;
import game.scene.common.rendering.screen.GameScreen;
import game.scene.level.logic.Level;
import game.scene.level.rendering.component.editor.LevelProcessor;
import game.scene.level.window.HelpWindow;
import game.scene.level.window.WinWindow;

public final class LevelScreen extends GameScreen {
    private final GameCamera camera;
    private final Level level;
    private final LevelLayout layout;

    private final BackgroundEffect backgroundParticles;

    private final HelpWindow helpWindow;
    private final WinWindow winWindow;

    private final LevelData levelData;

    private boolean isViewInverted = false;

    public LevelScreen(LevelData levelData) {
        super(levelData.tileMap);

        camera = new GameCamera();
        this.levelData = levelData;
        level = new Level(levelData);

        helpWindow = new HelpWindow();
        winWindow = new WinWindow();
        layout = new LevelLayout(this, level, helpWindow, winWindow);
        backgroundParticles = new BackgroundEffect(BackgroundEffect.Shape.CIRCLE);

        stage.addActor(layout);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
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
        backgroundParticles.update(delta, Display.VIEWPORT_WIDTH, Display.VIEWPORT_HEIGHT);
    }

    @Override
    protected void draw() {
        Matrix4 screenMatrix = new Matrix4().setToOrtho2D(
            0, 0,
            Display.VIEWPORT_WIDTH,
            Display.VIEWPORT_HEIGHT
        );
        backgroundParticles.draw(screenMatrix);

        batch.begin();
        map.draw(batch, 10);
        level.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }

    public void invertView() {
        isViewInverted = !isViewInverted;

        float zoom = map.height / 16.0f;

        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE);
        if (isViewInverted) {
            camera.offset(zoom * Display.EDITOR_MENU_SCALE * Display.VIEWPORT_WIDTH / 2.0f , 0);
        }
        else  {
            camera.offset(-zoom * Display.EDITOR_MENU_SCALE * Display.VIEWPORT_WIDTH / 2.0f , 0);
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (layout != null) {
            layout.toggleEditorSide(isViewInverted);
        }

        helpWindow.invertPosition(isViewInverted);
        winWindow.invertPosition(isViewInverted);
    }

    public LevelData getLevelData() {
        return levelData;
    }

    public Level getLevel() {
        return level;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }
}

