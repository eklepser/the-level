package com.eklepser.thelevel.graphics.selection;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.graphics.selection.buttons.BackButton;
import com.eklepser.thelevel.graphics.selection.buttons.LevelButton;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Layout;

import java.util.ArrayList;

public class LevelsTable extends Table {
    private final Game game;

    public LevelsTable(Game game) {
        this.game = game;
        setupLayout();
    }

    private void setupLayout() {
        Json json = new Json();
        ArrayList<LevelConfiguration> configs = json.fromJson(ArrayList.class, LevelConfiguration.class,
        Gdx.files.internal("world/level/levels.json"));
        for (LevelConfiguration conf : configs) {
            add(new LevelButton(game, conf)).width(Layout.VIEWPORT_WIDTH / 4.0f)
                .height(Layout.VIEWPORT_HEIGHT / 16.0f).padBottom(20);
            row();
        }
        add(new BackButton(game)).width(Layout.VIEWPORT_WIDTH / 8.0f);
    }
}
