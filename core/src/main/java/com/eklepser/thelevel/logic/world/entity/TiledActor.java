package com.eklepser.thelevel.logic.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.eklepser.thelevel.graphics.screen.Layout;
import com.eklepser.thelevel.util.Direction;

public abstract class TiledActor extends Actor {
    protected final Sprite sprite;
    protected final int size;
    protected float animationSpeed = 0.1f;
    protected final Vector2 worldPos;
    protected final Vector2 targetWorldPos = new Vector2();
    protected Direction facingDirection = Direction.UP;

    public TiledActor(Vector2 worldPos, String textureName) {
        this.worldPos = new Vector2(worldPos);
        this.targetWorldPos.set(worldPos);
        size = Layout.TILE_SIZE;

        Texture texture = new Texture(Gdx.files.internal(textureName));
        sprite = new Sprite(texture);
        sprite.setSize(size, size);

        setOrigin(Align.center);
        updateActorPosition();
    }

    protected void updateActorPosition() {
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        sprite.setColor(Color.WHITE);
        super.act(delta);
    }

    // Getters & setters:
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

    public Vector2 getTargetWorldPos() { return targetWorldPos; }

    public Direction getFacingDirection() { return facingDirection; }
}
