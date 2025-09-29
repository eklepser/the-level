package com.eklepser.thelevel.graphics.menu.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.builder.BuilderMap;
import com.eklepser.thelevel.graphics.builder.GameScreen;
import com.eklepser.thelevel.graphics.game.level.LevelScreen;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.logic.world.world.WorldConfiguration;
import com.eklepser.thelevel.util.Resources;

public class BuilderButton extends TextButton {
    public BuilderButton(Game game) {
        super("Build level", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                WorldConfiguration worldConfig = Configuration.from(
                    WorldConfiguration.class, Resources.WORLD_CONFIG).get(0);
                LevelConfiguration configuration = Configuration.from(
                    LevelConfiguration.class, Resources.LEVEL_CONFIG).get(0);
                game.setScreen(new LevelScreen(game, configuration));
            }
        });
    }
}
