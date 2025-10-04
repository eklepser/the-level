package game.scene.level.rendering;

import com.badlogic.gdx.Game;
import game.common.rendering.GameScreen;
import game.common.rendering.TableLayout;
import game.config.GraphicsConstants;
import game.scene.level.rendering.component.editor.EditorProcessor;
import game.scene.level.window.HelpWindow;
import game.scene.level.window.WinWindow;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;

public class LevelScreen extends GameScreen {
    private final Level level;
    private final LevelLayout layout;
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;

    public LevelScreen(Game game, LevelConfiguration config) {
        super(game, config.tileMap);

        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);

        // Order is important! Level -> layout -> loadZones.
        level = new Level(config, this);
        layout = new LevelLayout(level);
        level.loadZones(config.tileMap, layout);
    }

    @Override
    public void show() {
        camera.center(map.width * GraphicsConstants.TILE_SIZE, map.height * GraphicsConstants.TILE_SIZE);
        camera.offset(-GraphicsConstants.EDITOR_MENU_SCALE * GraphicsConstants.VIEWPORT_WIDTH / 2.0f, 0);

        stage.addActor(layout);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);

        multiplexer.addProcessor(new EditorProcessor(this));
        multiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        level.update(delta);
        stage.act(delta);

        batch.begin();
        level.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();

        stage.draw();
    }

    // Getters:
    public LevelLayout getLayout() {
        return layout;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public WinWindow getWinWindow() {
        return winWindow;
    }
}

