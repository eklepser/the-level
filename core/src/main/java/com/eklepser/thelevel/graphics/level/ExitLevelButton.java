package com.eklepser.thelevel.graphics.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.logic.world.Configuration;
import com.eklepser.thelevel.logic.world.level.World;
import com.eklepser.thelevel.logic.world.level.WorldConfiguration;
import com.eklepser.thelevel.util.Resources;

public class ExitLevelButton extends TextButton {
    public ExitLevelButton(Game game) {
        super("Exit", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit");
                WorldConfiguration worldConfig = Configuration.from(
                    WorldConfiguration.class, "world/world.json").get(0);
                game.setScreen(new World(worldConfig, game).getScreen());
            }
        });
    }
}
