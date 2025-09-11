package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.eklepser.thelevel.util.Direction;

public abstract class Controllable extends Actor {
    protected final Sprite sprite;
    protected final int size;
    protected float animationSpeed = 0;
    protected final Vector2 worldPos;

    public Controllable(Vector2 worldPos, int size, Texture texture) {
        this.worldPos = worldPos;
        this.size = size;
        this.sprite = new Sprite(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setSize(size, size);
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        sprite.setColor(Color.WHITE);
        super.act(delta);
    }

    public Vector2 getWorldPos() { return new Vector2(worldPos.x, worldPos.y); }

    public Rectangle getRect(Vector2 worldPos) {
        return new Rectangle(worldPos.x * size, worldPos.y * size, size, size);
    }

    public void setAnimationSpeed(float animationSpeed) { this.animationSpeed = animationSpeed; }

    protected void move(Vector2 targetWorldPos) {
        addAction(Actions.moveTo(
            targetWorldPos.x * size, targetWorldPos.y * size, animationSpeed));
        worldPos.set(targetWorldPos);
    }

    protected void hit(Direction direction) {
        addAction(Actions.sequence(
            Actions.moveBy(direction.vector.x * size / 4.0f,
                direction.vector.y * size / 4.0f, animationSpeed / 2),
            Actions.moveBy(-direction.vector.x * size / 4.0f,
                -direction.vector.y * size / 4.0f, animationSpeed / 2)
        ));
    }
}
