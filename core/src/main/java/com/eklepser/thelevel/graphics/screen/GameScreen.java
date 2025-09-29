package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.render.MapLoader;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.util.Resources;

public abstract class GameScreen extends ScreenAdapter {
    protected final Stage stage;
    protected final TileMap map;
    protected final SpriteBatch batch;
    protected final TileSet tileset;
    protected final OrthographicCamera camera;
    protected final InputMultiplexer inputMultiplexer;
    public static final int TILE_SIZE = 32;

    public GameScreen(TileMap map) {
        stage = new Stage(new FitViewport(
            Layout.VIEWPORT_WIDTH, Layout.VIEWPORT_HEIGHT, new OrthographicCamera()));
        this.map = map;

        batch = new SpriteBatch();
        tileset = Resources.getTileSet();

        camera = new OrthographicCamera();
        camera.setToOrtho(true);
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    private void draw() {
        for (int y = 0; y < map.height; y++) {
            for (int x = 0; x < map.width; x++) {
                int tileId = map.tiles[y][x];
                if (tileId < 0) continue;

                TextureRegion region = tileset.getTile(tileId);
                if (region == null) continue;

                float screenX = x * TILE_SIZE;
                float screenY = y * TILE_SIZE;

                batch.draw(region, screenX, screenY);
            }
        }
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

    public TileSet getTileset() {
        return tileset;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }
}
