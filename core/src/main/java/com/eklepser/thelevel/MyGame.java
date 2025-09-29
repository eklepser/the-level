package com.eklepser.thelevel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eklepser.thelevel.graphics.screen.menu.MenuScreen;
import com.eklepser.thelevel.util.Resources;

public class MyGame extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        Resources.load();
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
