package com.eklepser.thelevel.graphics.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eklepser.thelevel.logic.world.Cat;

public class World extends Actor {
    private final Cat cat;

    public World(Cat cat) {
        this.cat = cat;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        cat.draw();
    }
}
