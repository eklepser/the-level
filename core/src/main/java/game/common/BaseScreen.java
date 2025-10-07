package game.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.common.rendering.TableLayout;
import game.config.Display;

// Basic screen class with Stage and InputMultiplexer aggregation
public abstract class BaseScreen extends ScreenAdapter {
    protected final Stage stage;
    protected final InputMultiplexer multiplexer;

    public BaseScreen() {
        stage = new Stage(new FitViewport(Display.VIEWPORT_WIDTH, Display.VIEWPORT_HEIGHT));

        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // UI update and draw
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
