package com.eklepser.thelevel.graphics.ui.level_selection.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.logic.world.level.LevelDescription;
import com.eklepser.thelevel.util.Resources;

public class LevelButton extends TextButton {
    public LevelButton(Game game, LevelDescription desc) {
        super(desc.name(), Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, desc));
            }
        });
    }
}

