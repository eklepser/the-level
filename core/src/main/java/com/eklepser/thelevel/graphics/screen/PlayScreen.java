package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.ui.code_editor.Editor;
import com.eklepser.thelevel.graphics.world.World;
import com.eklepser.thelevel.logic.Cat;

public class PlayScreen extends ScreenAdapter {
    private Stage stage;
    private Table rootTable;
    private Cat cat;

    @Override
    public void show() {
        stage = new Stage(new FitViewport(500, 500));

        cat = new Cat(stage.getBatch(), new Texture(Gdx.files.internal("world/cat.png")), Vector2.Zero, 40);
        Gdx.input.setInputProcessor(stage);

        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        setupLayout();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    private void setupLayout() {
        Table gamePanel = new Table();
        World gameField = new World(cat);
        gamePanel.add(gameField).grow();

        Editor editor = new Editor(cat, gameField);

        rootTable.add(editor.getTable()).width(stage.getWidth() * 0.5f);
        rootTable.add(gamePanel).width(stage.getWidth() * 0.5f).expand();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(editor.getKeyboardProcessor());
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }
}

