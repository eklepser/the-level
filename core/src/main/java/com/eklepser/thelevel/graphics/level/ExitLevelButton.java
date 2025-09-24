package com.eklepser.thelevel.graphics.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.world.WorldScreen;
import com.eklepser.thelevel.logic.world.WorldConfiguration;
import com.eklepser.thelevel.util.ConfigurationLoader;
import com.eklepser.thelevel.util.Resources;

public class ExitLevelButton extends TextButton {
    public ExitLevelButton(Game game) {
        super("Exit", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit");
                WorldConfiguration worldConf = ConfigurationLoader.
                    loadWorldConfigurations().get(0);
                game.setScreen(new WorldScreen(game, worldConf));
            }
        });
    }
}
