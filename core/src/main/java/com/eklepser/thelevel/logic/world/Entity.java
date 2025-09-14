package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.util.Direction;

public class Entity extends Controllable {
    private final CollisionManager collisionManager;
    private Direction facingDirection = Direction.UP;

    public Entity(Vector2 worldPos, int size, String textureName,  CollisionManager collisionManager) {
        super(worldPos, size, new Texture(Gdx.files.internal(textureName)));
        this.collisionManager = collisionManager;
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    public void checkCollisionsAndMove(Direction direction)
    {
        if (direction.equals(Direction.FORWARD)) direction = facingDirection;
        else facingDirection = direction;

        Vector2 targetWorldPos = worldPos.cpy().add(direction.vector);
        Rectangle targetRect = getRect(targetWorldPos);
        sprite.setRotation(Direction.getDegrees(direction));

        if (!collisionManager.checkWallCollisions(targetRect)) {
            move(targetWorldPos);
        }
        else hit(direction);
    }

    public void rotate(Direction rotateDirection) {
        sprite.setColor(Color.WHITE);
        facingDirection = Direction.getRotatedDirection(facingDirection, rotateDirection);
        sprite.setRotation(Direction.getDegrees(facingDirection));
    }

    public void teleport(Vector2 worldPos) {
        setPosition(worldPos.x * size, worldPos.y * size);
        this.worldPos.set(worldPos);
    }
}
