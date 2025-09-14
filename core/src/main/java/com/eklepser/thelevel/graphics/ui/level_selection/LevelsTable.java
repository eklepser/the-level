package com.eklepser.thelevel.graphics.ui.level_selection;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.graphics.ui.level_selection.buttons.BackButton;
import com.eklepser.thelevel.graphics.ui.level_selection.buttons.LevelButton;
import com.eklepser.thelevel.logic.world.level.LevelDescription;
import com.eklepser.thelevel.util.Constants;

import java.util.ArrayList;

public class LevelsTable extends Table {
    private final Game game;

    public LevelsTable(Game game) {
        this.game = game;
        setupLayout();
    }

    private void setupLayout() {
        ArrayList<LevelDescription> descs = loadLevelsDescs();
        for (LevelDescription desc : descs) {
            add(new LevelButton(game, desc)).width(Constants.VIEWPORT_WIDTH / 4.0f).padBottom(20);
            row();
        }
        add(new BackButton(game)).width(Constants.VIEWPORT_WIDTH / 8.0f);
    }

    private ArrayList<LevelDescription> loadLevelsDescs() {
        Json json = new Json();
        return json.fromJson(ArrayList.class, LevelDescription.class,
            Gdx.files.internal("world/levels/levels_desc.json"));
    }
}
