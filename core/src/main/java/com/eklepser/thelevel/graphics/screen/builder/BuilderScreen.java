package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.utils.Loader;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;

public class BuilderScreen extends GameScreen {
    private final Builder builder;
    private final BuilderLayout layout;
    private final Stage gridStage;

    public BuilderScreen(LevelConfiguration config) {
        super(config.tileMap);

        // Order is important! Builder -> gridStage -> layout.
        builder = new Builder(this);
        gridStage = new Stage(new FitViewport(
            TableLayout.VIEWPORT_WIDTH, TableLayout.VIEWPORT_HEIGHT, camera));
        gridStage.addActor(new GridActor(this));
        layout = new BuilderLayout(this);
    }

    @Override
    protected void setupCamera() { centerCamera(); }

    @Override
    public void show() {
        stage.addActor(layout);
        inputMultiplexer.addProcessor(new BuilderProcessor(this, map));
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(gridStage);
    }

    @Override
    public void render(float delta) {
        layout.update();

        updateCamera(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        gridStage.draw();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        gridStage.getViewport().update(width, height, true);
        centerCamera();
    }

    public void moveCamera(Vector2 direction) {
        camera.position.add(direction.x, direction.y, 0);
    }

    private void updateCamera(float delta) {
        float moveDistance = 200 * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y -= moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y += moveDistance;
        }
        camera.update();
    }

    // Getters:
    public Builder getBuilder() {
        return builder;
    }

    public Stage getGridStage() {
        return gridStage;
    }
}
