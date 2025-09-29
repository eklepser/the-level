package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.screen.Layout;
import com.eklepser.thelevel.graphics.render.MapLoader;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.logic.world.common.Configuration;

public abstract class GameScreen extends ScreenAdapter {
    protected final Stage stage;
    protected final TileMap map;
    protected final SpriteBatch batch;
    protected final TileSet tileset;
    protected final OrthographicCamera camera;
    protected final InputMultiplexer inputMultiplexer;
    public static final int TILE_SIZE = 32;

    public GameScreen(Configuration config) {
        stage = new Stage(new FitViewport(
            Layout.VIEWPORT_WIDTH, Layout.VIEWPORT_HEIGHT, new OrthographicCamera()));
        this.map = MapLoader.load(config.mapName);

        batch = new SpriteBatch();
        tileset = new TileSet("world/map/gamefield-tileset.png", TILE_SIZE, TILE_SIZE);

        camera = new OrthographicCamera();
        camera.setToOrtho(true);
        setupCamera();

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    protected abstract void setupCamera();

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

    public TileMap getMap() {
        return map;
    }
}
