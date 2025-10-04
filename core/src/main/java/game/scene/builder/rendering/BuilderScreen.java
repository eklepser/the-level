package game.scene.builder.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.common.rendering.TableLayout;
import game.config.GraphicsConstants;
import game.common.rendering.GameScreen;
import game.scene.level.logic.LevelConfiguration;
import game.scene.builder.rendering.component.GridActor;
import game.scene.builder.logic.Builder;
import game.scene.builder.logic.BuilderProcessor;

public class BuilderScreen extends GameScreen {
    private final LevelConfiguration config;
    private final Game game;
    private final Builder builder;
    private final BuilderLayout layout;
    private final Stage gridStage;
    private final GridActor gridActor;

    public BuilderScreen(Game game, LevelConfiguration config) {
        super(game, config.tileMap);
        this.config = config;
        this.game = game;
        // Order is important! Builder -> gridStage -> layout.
        builder = new Builder(this);

        gridActor = new GridActor(this);
        gridStage = new Stage(new FitViewport(
            GraphicsConstants.VIEWPORT_WIDTH, GraphicsConstants.VIEWPORT_HEIGHT, camera));
        gridStage.addActor(gridActor);

        layout = new BuilderLayout(this);
    }

    @Override
    public void show() {
        camera.center(map.width * GraphicsConstants.TILE_SIZE,
            map.height * GraphicsConstants.TILE_SIZE);

        stage.addActor(layout);

        multiplexer.addProcessor(new BuilderProcessor(game, this));
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(gridStage);
    }

    @Override
    public void render(float delta) {
        layout.update();
        updateCamera(delta);

        renderClear();
        renderMap();

        gridStage.draw();
        renderStage(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        gridStage.getViewport().update(width, height, true);
        camera.center(map.width * GraphicsConstants.TILE_SIZE,
            map.height * GraphicsConstants.TILE_SIZE);
    }

    public void moveCamera(Vector2 direction) {
        camera.position.add(direction.x, direction.y, 0);
    }

    private void updateCamera(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            stage.setKeyboardFocus(null);
        }

        if (layout.getConfigTable().hasTextFieldFocus()) return;

        float moveDistance = 200 * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y += moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y -= moveDistance;
        }
        camera.update();
    }

    // Getters:
    public LevelConfiguration getConfig() {
        return config;
    }

    public Builder getBuilder() {
        return builder;
    }

    public BuilderLayout getLayout() {
        return layout;
    }

    public Stage getGridStage() {
        return gridStage;
    }

    public GridActor getGridActor() {
        return gridActor;
    }
}
