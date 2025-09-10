package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.ui.editor.Editor;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Constants;

import java.util.ArrayList;

import static com.eklepser.thelevel.util.Constants.EDITOR_MENU_SCALE;

public class PlayScreen extends ScreenAdapter {
    private Stage stage;
    private final java.util.List<Entity> entities = new ArrayList<>();
    private Editor editor;
    private GameField gameField;

    @Override
    public void show() {
        Entity hero = new Entity("world/hero.png", Vector2.Zero, Constants.TILE_SIZE);
        entities.add(hero);
        Entity ktoto = new Entity("world/hero.png", new Vector2(1, 0), Constants.TILE_SIZE);
        entities.add(ktoto);

        gameField = new GameField("world/levels/level.tmx", entities);
        stage = new Stage(new FitViewport(
            Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, new OrthographicCamera()));
        setupLayout();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        String debugText = entities.get(0).getWorldPos().toString();
        editor.setDebugText(debugText);

        gameField.act(delta);
        gameField.render();

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
        editor = new Editor(stage, entities, 20);
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

