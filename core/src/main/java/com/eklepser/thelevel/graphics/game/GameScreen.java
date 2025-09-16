package com.eklepser.thelevel.graphics.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.game.editor.Editor;
import com.eklepser.thelevel.graphics.game.editor.KeyboardProcessor;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Layout;

import static com.eklepser.thelevel.util.Layout.EDITOR_MENU_SCALE;

public class GameScreen extends ScreenAdapter {
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;
    private final Stage stage;
    private final Level level;
    private final GameField gameField;
    private final Editor editor;
    private final InputMultiplexer multiplexer;

    public GameScreen(Game game, LevelConfiguration conf) {
        stage = new Stage(new FitViewport(
            Layout.VIEWPORT_WIDTH, Layout.VIEWPORT_HEIGHT, new OrthographicCamera()));
        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);

        level = new Level(this, conf);
        gameField = new GameField(level);
        editor = new Editor(this, level);

        multiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        setupLayout();
        setupInputProcessors();
    }

    private void setupLayout() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.add(editor).width(stage.getWidth() * EDITOR_MENU_SCALE).top().fillY();
        rootTable.add().expand();
        stage.addActor(rootTable);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
    }

    private void setupInputProcessors() {
        multiplexer.addProcessor(new KeyboardProcessor(this));
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameField.act(delta);
        gameField.draw();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public Editor getEditor() {
        return editor;
    }

    public WinWindow getWinWindow() { return winWindow; }
}

