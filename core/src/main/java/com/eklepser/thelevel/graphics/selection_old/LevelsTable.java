package com.eklepser.thelevel.graphics.selection_old;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.logic.game.level.LevelConfiguration;

import java.util.ArrayList;

public class LevelsTable extends Table {
    private final Game game;
    private final int colsNum = 3;

    public LevelsTable(Game game) {
        this.game = game;
    }

//    private void setupLayout() {
//        for (int i = 0; i < configs.size(); i++) {
//            LevelConfiguration conf = configs.get(i);
//            add(new LevelButton(game, conf)).width(Layout.VIEWPORT_WIDTH / 6.0f)
//                .height(Layout.VIEWPORT_HEIGHT / 12.0f).padBottom(10);
//            if ((i + 1) % colsNum == 0) row();
//        }
//        row().colspan(colsNum);
//        add(new BackButton(game)).width(Layout.VIEWPORT_WIDTH / 6.0f)
//            .height(Layout.VIEWPORT_HEIGHT / 12.0f);
//    }

    private void loadConfigurations(String configurationType) {
        Json json = new Json();
        ArrayList<LevelConfiguration> configs = json.fromJson(
            ArrayList.class, LevelConfiguration.class,
            Gdx.files.internal("world/level/" + configurationType + ".json"));
    }
}
