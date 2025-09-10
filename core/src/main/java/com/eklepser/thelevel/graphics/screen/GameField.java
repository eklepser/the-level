package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eklepser.thelevel.logic.world.Entity;

public class GameField extends Actor {
    private final Entity entity;

    public GameField(Entity entity) {
        this.entity = entity;
    }

    public void act(float delta) {
    }

    public void draw(Batch batch, float parentAlpha) {
//        batch.flush();
//        entity.setOffset(new Vector2(getX(), 0));
//        entity.draw(batch, 1.0f);
    }
}
