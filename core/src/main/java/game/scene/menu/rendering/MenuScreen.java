package game.scene.menu.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.config.GraphicsConstants;
import game.scene.common.rendering.TableLayout;
import game.scene.menu.rendering.component.BuilderButton;
import game.scene.menu.rendering.component.ExitButton;
import game.scene.menu.rendering.component.LevelsButton;
import game.scene.menu.rendering.component.StartButton;
import game.scene.common.rendering.component.ColoredString;

public class MenuScreen extends ScreenAdapter {
    private final Game game;
    private final Stage stage;

    public MenuScreen(Game game) {
        this.game = game;
        stage = new Stage(new FitViewport(
            GraphicsConstants.VIEWPORT_WIDTH, GraphicsConstants.VIEWPORT_HEIGHT, new OrthographicCamera()));
    }

    @Override
    public void show() {
        setupLayout();
        setupInputProcessors();
    }

    private void setupLayout() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);

        ColoredString name = new ColoredString("/blue_4 The LEVEL");
        rootTable.add(name).padBottom(20).row();;

        rootTable.add(new StartButton(game)).width(GraphicsConstants.VIEWPORT_WIDTH / 4.0f)
            .height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f);
        rootTable.row();

        rootTable.add(new LevelsButton(game)).width(GraphicsConstants.VIEWPORT_WIDTH / 4.0f)
            .height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        rootTable.row();

        rootTable.add(new BuilderButton(game)).width(GraphicsConstants.VIEWPORT_WIDTH / 4.0f)
            .height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        rootTable.row();

        rootTable.add(new ExitButton()).width(GraphicsConstants.VIEWPORT_WIDTH / 8.0f)
            .height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        stage.addActor(rootTable);
    }

    private void setupInputProcessors() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
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

