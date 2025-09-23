package com.eklepser.thelevel.graphics.selection_old.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.logic.game.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

public class LevelButton extends TextButton {
    public LevelButton(Game game, LevelConfiguration conf) {
        super(conf.getName(), Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LevelScreen(game, conf));
            }
        });
    }
}

