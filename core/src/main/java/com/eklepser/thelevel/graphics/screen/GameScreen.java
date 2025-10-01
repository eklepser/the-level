package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.Tileset;
import com.eklepser.thelevel.util.Resources;

public abstract class GameScreen extends ScreenAdapter {
    protected final Stage stage;
    protected final TileMap map;
    protected final SpriteBatch batch;
    protected final Tileset tileset;
    protected final OrthographicCamera camera;
    protected final InputMultiplexer inputMultiplexer;
    public static final int TILE_SIZE = 32;

    public GameScreen(TileMap map) {
        stage = new Stage(new FitViewport(
            TableLayout.VIEWPORT_WIDTH, TableLayout.VIEWPORT_HEIGHT, new OrthographicCamera()));
        this.map = map;

        batch = new SpriteBatch();
        tileset = Resources.getTileset();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, TableLayout.VIEWPORT_WIDTH, TableLayout.VIEWPORT_HEIGHT);
        setupCamera();

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    protected abstract void setupCamera();

    protected void centerCamera() {
        float levelWidth = map.width * TILE_SIZE;
        float levelHeight = map.height * TILE_SIZE;

        float levelCenterX = levelWidth / 2f;
        float levelCenterY = levelHeight / 2f;

        camera.position.set(levelCenterX, levelCenterY, 0);
        camera.update();
    }

    protected void adaptCamera() {
        float zoom = map.width * 1.5f / 10.0f;
        float cameraX = (map.width * Layout.TILE_SIZE - Layout.EDITOR_MENU_SCALE * Layout.VIEWPORT_WIDTH / zoom) / 2.0f;
        float cameraY = map.height * Layout.TILE_SIZE / 2.0f;
        camera.setToOrtho(false, Layout.VIEWPORT_WIDTH / zoom,
            Layout.VIEWPORT_HEIGHT / zoom);
        camera.position.set(cameraX, cameraY, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        map.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    // Getters:
    public TileMap getMap() {
        return map;
    }

    public Stage getStage() {
        return stage;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Tileset getTileset() {
        return tileset;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }
}
