package com.eklepser.thelevel.graphics.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.selection.SelectionScreen;
import com.eklepser.thelevel.util.Resources;

public class ExitLevelButton extends TextButton {
    public ExitLevelButton(Game game) {
        super("Exit", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit");
                game.setScreen(new SelectionScreen(game));
            }
        });
    }
}
