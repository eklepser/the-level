package com.eklepser.thelevel.graphics.menu.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.world.WorldScreen;
import com.eklepser.thelevel.logic.world.WorldConfiguration;
import com.eklepser.thelevel.util.ConfigurationLoader;
import com.eklepser.thelevel.util.Resources;

public class StartButton extends TextButton {
    public StartButton(Game game) {
        super("START GAME", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new SelectionScreen(game));
                WorldConfiguration worldConf = ConfigurationLoader.
                    loadWorldConfigurations().get(0);
                game.setScreen(new WorldScreen(game, worldConf));
            }
        });
    }
}
