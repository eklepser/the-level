package com.eklepser.thelevel.graphics.ui.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.LevelSelectionScreen;
import com.eklepser.thelevel.util.Resources;

public class CloseLevelButton extends TextButton {
    public CloseLevelButton(Game game) {
        super("Exit", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit");
                game.setScreen(new LevelSelectionScreen(game));
            }
        });
    }
}
