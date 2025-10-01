package com.eklepser.thelevel.graphics.screen.menu.buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.selection.PlaySelectionLayout;
import com.eklepser.thelevel.graphics.screen.selection.SelectionScreen;
import com.eklepser.thelevel.util.Resources;

public class LevelsButton extends TextButton {
    public LevelsButton(Game game) {
        super("Play user levels", Resources.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SelectionScreen(game, PlaySelectionLayout.class));
            }
        });
    }
}
