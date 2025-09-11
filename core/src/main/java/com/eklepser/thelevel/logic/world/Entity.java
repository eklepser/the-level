package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.eklepser.thelevel.util.Direction;

public class Entity extends Actor {
    private final Sprite sprite;
    private final Vector2 worldPos;
    private final int size;
    private Direction facingDirection = Direction.UP;
    private float animationSpeed = 0;

    public Entity(Texture texture, Vector2 worldPos, int size) {
        sprite = new Sprite(texture);
        this.size = size;
        this.worldPos = worldPos;
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    public Entity(String textureName, Vector2 worldPos, int size) {
        sprite = new Sprite(new Texture(Gdx.files.internal(textureName)));
        this.size = size;
        this.worldPos = worldPos;
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(getX(), getY());
        sprite.setSize(size, size);
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void move(Direction direction)
    {
        if (direction.equals(Direction.FORWARD)) direction = facingDirection;

        Vector2 targetWorldPos = new Vector2(
            worldPos.x + direction.vector.x, worldPos.y + direction.vector.y);
        addAction(Actions.moveTo(
            targetWorldPos.x * size, targetWorldPos.y * size, animationSpeed));

        worldPos.set(targetWorldPos);
        facingDirection = direction;
        sprite.setRotation(Direction.getDegrees(direction));
    }

    public void moveTo(Vector2 worldPos) {
        setPosition(worldPos.x * size, worldPos.y * size);
        this.worldPos.set(worldPos);
    }

    public void rotate(Direction rotateDirection) {
        facingDirection = Direction.getRotatedDirection(facingDirection, rotateDirection);
        sprite.setRotation(Direction.getDegrees(facingDirection));
    }

    public Vector2 getWorldPos() {
        return new Vector2(worldPos.x, worldPos.y);
    }

    public void setAnimationSpeed(float animationSpeed) {
        this.animationSpeed = animationSpeed;
    }
}
