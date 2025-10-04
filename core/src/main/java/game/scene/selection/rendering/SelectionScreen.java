package game.scene.selection.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.config.GraphicsConstants;
import game.resources.LevelLoader;
import game.scene.common.rendering.Layout;
import game.scene.common.rendering.TableLayout;
import game.scene.selection.logic.LevelMetadata;
import game.scene.selection.logic.SelectionProcessor;

import java.util.List;

public class SelectionScreen extends ScreenAdapter {
    private final Game game;
    private final Stage stage;
    private final InputMultiplexer multiplexer;

    private final List<LevelMetadata> levels;
    private final TableLayout layout;

    public SelectionScreen(Game game, Class<? extends Layout> layoutClass) {
        this.game = game;
        stage = new Stage(new FitViewport(
            GraphicsConstants.VIEWPORT_WIDTH, GraphicsConstants.VIEWPORT_HEIGHT, new OrthographicCamera()));
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);

        levels = LevelLoader.loadMetadata("data/builder");

        if (BuilderSelectionLayout.class.equals(layoutClass)) {
            layout = new BuilderSelectionLayout(game, levels);
        }
        else layout = new PlaySelectionLayout(game, levels);
    }

    @Override
    public void show() {
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new SelectionProcessor(game));
        stage.addActor(layout);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}

