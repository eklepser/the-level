package com.eklepser.thelevel.graphics.screen.menu.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.level.LevelScreen;
import com.eklepser.thelevel.graphics.screen.world.WorldScreen;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.logic.world.world.WorldConfiguration;
import com.eklepser.thelevel.util.Resources;

public class StartButton extends TextButton {
    public StartButton(Game game) {
        super("START GAME", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LevelConfiguration config = Configuration.from(
                    LevelConfiguration.class, "builder/level_test_0.json");
                game.setScreen(new LevelScreen(game, config));
            }
        });
    }
}
