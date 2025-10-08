package game.common.logic.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import game.common.logic.Direction;

public class Entity extends TiledActor {
    public Entity(int worldPosX, int worldPosY, String textureName) {
        super(new Vector2(worldPosX, worldPosY), textureName);
        setPosition(worldPosX * size, worldPosY * size);

        facingDirection = Direction.UP;
    }

    // Class logic:
    public void update() {
        if (!targetWorldPos.equals(worldPos)) {
            move(targetWorldPos);
        }
    }

    public void rotate(Direction rotateDirection) {
        sprite.setColor(Color.WHITE);
        facingDirection = Direction.getRotatedDirection(facingDirection, rotateDirection);
        sprite.setRotation(Direction.getDegrees(facingDirection));
    }

    public void move(Vector2 targetWorldPos) {
        this.targetWorldPos.set(targetWorldPos);

        float targetX = targetWorldPos.x * size;
        float targetY = targetWorldPos.y * size;

        addAction(Actions.sequence(
            Actions.moveTo(targetX, targetY, animationSpeed),
            new Action() {
                @Override
                public boolean act(float delta) {
                    worldPos.set(targetWorldPos);
                    return true;
                }
            }
        ));
    }

    public void hit() {
        addAction(Actions.sequence(
            Actions.moveBy(facingDirection.vector.x * size / 4.0f,
                facingDirection.vector.y * size / 4.0f, animationSpeed / 2),
            Actions.moveBy(-facingDirection.vector.x * size / 4.0f,
                -facingDirection.vector.y * size / 4.0f, animationSpeed / 2)
        ));
    }

    public void resetTargetWorldPos() {
        targetWorldPos.set(worldPos);
    }

    // Getters & setters
    public void setTargetWorldPos(Direction direction) {
        facingDirection = direction;
        sprite.setRotation(Direction.getDegrees(direction));
        targetWorldPos.set(targetWorldPos).add(direction.vector);
    }
}
