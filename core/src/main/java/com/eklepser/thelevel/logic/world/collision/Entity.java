package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.util.Direction;

public class Entity extends Controllable {
    public Entity(Vector2 worldPos, int size, String textureName) {
        super(worldPos, size, new Texture(Gdx.files.internal(textureName)));
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    public Entity(Vector2 worldPos, int size, Sprite sprite) {
        super(worldPos, size, sprite);
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    public Entity(int worldPosX, int worldPosY, int size, String textureName) {
        super(new Vector2(worldPosX, worldPosY), size, new Texture(Gdx.files.internal(textureName)));
        setPosition(worldPosX * size, worldPosY * size);
    }

    public void update()
    {
        if (!targetWorldPos.equals(worldPos)) move(targetWorldPos);
    }

    public void rotate(Direction rotateDirection) {
        sprite.setColor(Color.WHITE);
        facingDirection = Direction.getRotatedDirection(facingDirection, rotateDirection);
        sprite.setRotation(Direction.getDegrees(facingDirection));
    }

    private void move(Vector2 targetWorldPos) {
        addAction(new SequenceAction(Actions.moveTo(
        targetWorldPos.x * size, targetWorldPos.y * size, animationSpeed),
        new Action() {
            @Override
            public boolean act(float delta) {
                worldPos.set(targetWorldPos);
                return true;
            }
        }));
    }

    public void hit() {
        Direction direction = facingDirection;
        addAction(Actions.sequence(
            Actions.moveBy(direction.vector.x * size / 4.0f,
                direction.vector.y * size / 4.0f, animationSpeed / 2),
            Actions.moveBy(-direction.vector.x * size / 4.0f,
                -direction.vector.y * size / 4.0f, animationSpeed / 2)
        ));
    }

    public void resetTargetWorldPos() {
        targetWorldPos.set(worldPos);
    }
}
