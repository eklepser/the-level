package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.ui.code_editor.Editor;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Constants;

import static com.eklepser.thelevel.util.Constants.EDITOR_MENU_SCALE;

public class PlayScreen extends ScreenAdapter {
    private Stage stage;
    private Entity entity;

    @Override
    public void show() {
        stage = new Stage(new FitViewport(
            Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT));
        entity = new Entity("world/cat.png", Vector2.Zero, Constants.TILE_SIZE);
        stage.addActor(entity);

        setupLayout();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    private void setupLayout() {
        // Setup components location:
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);
        Editor editor = new Editor(entity);
        rootTable.add(editor.getTable())
            .width(stage.getWidth() * EDITOR_MENU_SCALE)
            .top();
        rootTable.add().expand();

        // Setup input processors:
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(editor.getKeyboardProcessor());
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }
}

