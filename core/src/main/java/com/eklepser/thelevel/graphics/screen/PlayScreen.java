package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.Resources;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeField;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.world.World;
import com.eklepser.thelevel.logic.Cat;
import com.eklepser.thelevel.logic.decoder.Translator;

public class PlayScreen extends ScreenAdapter {
    private Stage stage;
    private Table rootTable;
    private Cat cat;

    @Override
    public void show() {
        stage = new Stage();
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

    private void setupLayout() {
        Table leftPanel = new Table();
        Table rightPanel = new Table();

        World gameField = new World(cat);
        rightPanel.add(gameField).grow();

        leftPanel.add(new TextLabel("Code:")).padBottom(10);
        CodeField codeField = new CodeField(leftPanel, 5);

        Button button = new TextButton("Run", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Running");
                Translator.translateAll(codeField.getCodeLines(), cat, gameField);
            }
        });

        leftPanel.row();
        leftPanel.add(button);

        rootTable.add(leftPanel).width(Gdx.graphics.getWidth() * 0.3f);
        rootTable.add(rightPanel).width(Gdx.graphics.getWidth() * 0.7f).expand();
    }
}

