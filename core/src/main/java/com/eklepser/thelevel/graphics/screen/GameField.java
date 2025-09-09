package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eklepser.thelevel.logic.world.Entity;

public class GameField extends Actor {
    private final Entity entity;

    public GameField(Entity entity) {
        this.entity = entity;
        //entity.setOffset(new Vector2(getX(), getY()));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.flush();
        batch.setTransformMatrix(getStage().getCamera().view);
        batch.setProjectionMatrix(getStage().getCamera().projection);
        entity.setOffset(new Vector2(getX(), getY()));
        entity.draw();
        batch.flush();

        //entity.draw();
        //entity.setOffset(new Vector2(getX(), getY()));
    }
}
