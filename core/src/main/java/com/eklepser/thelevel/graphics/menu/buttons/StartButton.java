package com.eklepser.thelevel.graphics.menu.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.logic.world.world.World;
import com.eklepser.thelevel.logic.world.world.WorldConfiguration;
import com.eklepser.thelevel.util.Resources;

public class StartButton extends TextButton {
    public StartButton(Game game) {
        super("START GAME", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                WorldConfiguration worldConfig = Configuration.from(
                    WorldConfiguration.class, Resources.WORLD_CONFIG).get(0);
                game.setScreen(new World(worldConfig, game).getScreen());
            }
        });
    }
}
