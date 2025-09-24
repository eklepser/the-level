package com.eklepser.thelevel.graphics.world;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.logic.world.WorldConfiguration;
import com.eklepser.thelevel.logic.world.World;
import com.eklepser.thelevel.util.Layout;

public class WorldScreen extends ScreenAdapter {
    private final Game game;
    private final Stage stage;
    private final World world;
    private final WorldMap worldMap;
    private final Table rootTable;

    public WorldScreen(Game game, WorldConfiguration conf) {
        this.game = game;
        stage = new Stage(new FitViewport(
            Layout.VIEWPORT_WIDTH, Layout.VIEWPORT_HEIGHT, new OrthographicCamera()));

        world = new World(this, conf);
        rootTable = new Table();
        worldMap = new WorldMap(world);
    }

    @Override
    public void show() {
        setupLayout();
        setupInputProcessors();
    }

    private void setupLayout() {

    }

    private void setupInputProcessors() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new KeyboardProcessor(game, world));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldMap.act(delta);
        worldMap.draw();

        stage.act(delta);
        stage.draw();
    }



    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}

