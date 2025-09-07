package com.eklepser.thelevel;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eklepser.thelevel.graphics.screen.PlayScreen;


public class MyGame extends com.badlogic.gdx.Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        Resources.load();
        batch = new SpriteBatch();
        setScreen(new PlayScreen());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
