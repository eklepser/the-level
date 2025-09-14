package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.ui.common.HelpWindow;
import com.eklepser.thelevel.graphics.ui.editor.EditorTable;
import com.eklepser.thelevel.graphics.ui.editor.KeyboardProcessor;
import com.eklepser.thelevel.logic.world.GameField;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.util.Constants;

import static com.eklepser.thelevel.util.Constants.EDITOR_MENU_SCALE;

public class PlayScreen extends ScreenAdapter {
    private final Stage stage;
    private final Level level;
    private final GameField gameField;
    private final EditorTable editor;
    private final HelpWindow helpWindow = new HelpWindow();

    public PlayScreen() {
        stage = new Stage(new FitViewport(
            Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, new OrthographicCamera()));
        level = new Level("world/levels/level.tmx");
        gameField = new GameField(level);
        editor = new EditorTable(this, level.getEntities(), 20);
    }

    @Override
    public void show() {
        setupLayout();
        setupInputProcessors();
    }

    private void setupLayout() {
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.add(editor)
            .width(stage.getWidth() * EDITOR_MENU_SCALE)
            .top();
        rootTable.add().expand();
        stage.addActor(rootTable);
        stage.addActor(helpWindow);
    }

    private void setupInputProcessors() {
        InputMultiplexer multiplexer = new InputMultiplexer();
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

    public EditorTable getEditor() {
        return editor;
    }
}

