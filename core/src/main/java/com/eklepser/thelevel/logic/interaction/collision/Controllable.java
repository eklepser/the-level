package com.eklepser.thelevel.logic.interaction.collision;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eklepser.thelevel.util.Direction;

public abstract class Controllable extends Actor {
    protected final Sprite sprite;
    protected final int size;
    protected float animationSpeed = 0.1f;
    protected final Vector2 worldPos;
    protected final Vector2 targetWorldPos = new Vector2();
    protected Direction facingDirection = Direction.UP;

    public Controllable(Vector2 worldPos, int size, Texture texture) {
        this.worldPos = worldPos;
        targetWorldPos.set(worldPos);
        this.size = size;
        this.sprite = new Sprite(texture);
    }

    public Controllable(Vector2 worldPos, int size, Sprite sprite) {
        this.worldPos = worldPos;
        targetWorldPos.set(worldPos);
        this.size = size;
        this.sprite = sprite;
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

    public void setMoving(Direction direction) {
        if (direction.equals(Direction.FORWARD)) direction = facingDirection;
        else facingDirection = direction;
        targetWorldPos.set(worldPos.cpy().add(direction.vector));
        sprite.setRotation(Direction.getDegrees(direction));
    }

    public void teleport(Vector2 worldPos) {
        setPosition(worldPos.x * size, worldPos.y * size);
        this.worldPos.set(worldPos);
    }

    public void setAnimationSpeed(float animationSpeed) { this.animationSpeed = animationSpeed; }

    public Rectangle getRect(Vector2 worldPos) {
        return new Rectangle(worldPos.x * size, worldPos.y * size, size, size);
    }

    public Rectangle getRect() { return getRect(worldPos); }

    public Rectangle getTargetRect() { return getRect(targetWorldPos); }

    public Rectangle getFacingRect() {
        return getRect(worldPos.cpy().add(facingDirection.vector));
    }

    public Vector2 getWorldPos() { return worldPos; }

    public Direction getFacingDirection() { return facingDirection; }
}
