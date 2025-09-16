package com.eklepser.thelevel.graphics.selection.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.menu.MenuScreen;
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

