package game.common.rendering;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.config.GraphicsConstants;

public abstract class BaseScreen extends ScreenAdapter {
    protected TableLayout layout;
    protected final Game game;
    protected final Stage stage;
    protected final InputMultiplexer multiplexer;

    public BaseScreen(Game game) {
        this.game = game;

        stage = new Stage(new FitViewport(GraphicsConstants.VIEWPORT_WIDTH, GraphicsConstants.VIEWPORT_HEIGHT));
        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        renderClear();
        renderStage(delta);
    }

    protected final void renderClear() {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected final void renderStage(float delta) {
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
