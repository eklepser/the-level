package com.eklepser.thelevel.logic.world.level;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.util.Layout;

public abstract class GameScreen extends ScreenAdapter {
    protected final Stage stage;
    protected final GameMap gameMap;
    protected final InputMultiplexer inputMultiplexer;

    public GameScreen(Game game, GameMap gameMap) {
        this.gameMap = gameMap;
        stage = new Stage(new FitViewport(
            Layout.VIEWPORT_WIDTH, Layout.VIEWPORT_HEIGHT, new OrthographicCamera()));
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameMap.update(delta);
        gameMap.draw();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void addProcessor(InputProcessor processor) {
        inputMultiplexer.addProcessor(processor);
    }

    // Getters:
    public Stage getStage() {
        return stage;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
