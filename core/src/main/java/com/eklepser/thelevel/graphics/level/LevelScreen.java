package com.eklepser.thelevel.graphics.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.level.editor.KeyboardProcessor;
import com.eklepser.thelevel.graphics.level.root.RootTable;
import com.eklepser.thelevel.logic.game.level.Level;
import com.eklepser.thelevel.logic.game.level.LevelConfiguration;
import com.eklepser.thelevel.util.Layout;

public class LevelScreen extends ScreenAdapter {
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;
    private final Stage stage;
    private final Level level;
    private final LevelMap levelMap;
    private final InputMultiplexer multiplexer;
    private final RootTable rootTable;

    public LevelScreen(Game game, LevelConfiguration conf) {
        stage = new Stage(new FitViewport(
            Layout.VIEWPORT_WIDTH, Layout.VIEWPORT_HEIGHT, new OrthographicCamera()));
        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);

        level = new Level(this, conf);
        rootTable = new RootTable(this, level);
        levelMap = new LevelMap(level);

        multiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        setupLayout();
        setupInputProcessors();
    }

    private void setupLayout() {
        stage.addActor(rootTable);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
    }

    private void setupInputProcessors() {
        multiplexer.addProcessor(new KeyboardProcessor((LevelScreen) level.getScreen()));
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelMap.act(delta);
        levelMap.draw();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public RootTable getRootTable() { return rootTable; }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public WinWindow getWinWindow() { return winWindow; }
}

