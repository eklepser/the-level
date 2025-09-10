package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Direction;

public class Entity extends Actor {
    private final Sprite sprite;
    private final Vector2 pos;
    private final int size;
    private Direction facingDirection = Direction.UP;

    public Entity(Texture texture, Vector2 worldPos, int size) {
        sprite = new Sprite(texture);
        sprite.setOriginCenter();
        this.pos = worldPos.scl(Constants.TILE_SIZE);
        this.size = size;
    }

    public Entity(String textureName, Vector2 worldPos, int size) {
        sprite = new Sprite(new Texture(Gdx.files.internal(textureName)));
        this.pos = worldPos.scl(Constants.TILE_SIZE);
        this.size = size;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float x = pos.x;
        float y = pos.y;
        sprite.setPosition(x, y);
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
        pos.x += direction.vector.x * size;
        pos.y += direction.vector.y * size;
        facingDirection = direction;
        sprite.setOriginCenter();
        sprite.setRotation(Direction.getDegrees(direction));
        addAction(new MoveToAction());
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

    public Vector2 getWorldPos() {
        return new Vector2(pos.x / Constants.TILE_SIZE, pos.y / Constants.TILE_SIZE);
    }

    public Direction getFacingDirection() { return facingDirection; }
}
