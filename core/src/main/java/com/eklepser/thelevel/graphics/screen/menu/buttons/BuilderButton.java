package com.eklepser.thelevel.graphics.screen.menu.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.builder.BuilderScreen;
import com.eklepser.thelevel.graphics.screen.level.LevelScreen;
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
                game.setScreen(new BuilderScreen());
            }
        });
    }
}
