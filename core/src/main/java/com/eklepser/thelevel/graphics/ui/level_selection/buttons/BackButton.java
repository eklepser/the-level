package com.eklepser.thelevel.graphics.ui.level_selection.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.graphics.screen.MenuScreen;
import com.eklepser.thelevel.logic.world.level.LevelDescription;
import com.eklepser.thelevel.util.Resources;

public class BackButton extends TextButton {
    public BackButton(Game game) {
        super("BACK", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back");
                game.setScreen(new MenuScreen(game));
            }
        });
    }
}

