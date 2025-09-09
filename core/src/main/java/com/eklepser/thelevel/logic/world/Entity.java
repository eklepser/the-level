package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Direction;

public class Entity {
    private final Batch batch;
    private final Sprite sprite;
    private final Vector2 pos;
    private final int size;
    private Vector2 offset = Vector2.Zero;
    private Direction facingDirection = Direction.UP;

    public Entity(Batch batch, Texture texture, Vector2 worldPos, int size) {
        this.batch = batch;
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        this.pos = worldPos;
        this.size = size;
    }

    public Entity(Batch batch, String textureName, Vector2 worldPos, int size) {
        this.batch = batch;
        sprite = new Sprite(new Texture(Gdx.files.internal(textureName)));
        this.pos = worldPos;
        this.size = size;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public Vector2 getWorldPos() {
        return new Vector2(pos.x / Constants.TILE_SIZE, pos.y / Constants.TILE_SIZE);
    }

    public void draw() {
        float x = offset.x + pos.x;
        float y = offset.y + pos.y;
        sprite.setPosition(x, y);
        sprite.setSize(size, size);
        sprite.draw(batch);
        //batch.draw(sprite, x, y, size, size);
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void move(Direction direction)
    {
        pos.x += direction.vector.x * size;
        pos.y += direction.vector.y * size;
        facingDirection = direction;
        sprite.setOriginCenter();
        sprite.setRotation(Direction.getDegrees(direction));
    }

    public void moveTo(Vector2 newPos)
    {
        pos.x = newPos.x * size;
        pos.y = newPos.y * size;
    }

    public void rotate(Direction rotateDirection) {
        facingDirection = Direction.getRotatedDirection(facingDirection, rotateDirection);
        sprite.setOriginCenter();
        sprite.setRotation(Direction.getDegrees(facingDirection));
    }
}
