package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.eklepser.thelevel.util.Direction;
import com.eklepser.thelevel.util.Layout;

public class Entity extends Controllable implements Collidable {
    public Entity(Vector2 worldPos, int size, String textureName) {
        super(worldPos, size, new Texture(Gdx.files.internal(textureName)));
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    public Entity(Vector2 worldPos, int size, Sprite sprite) {
        super(worldPos, size, sprite);
        setPosition(worldPos.x * size, worldPos.y * size);
    }

    @Override
    public void onPossibleCollision() {
        hit(facingDirection);
    }

    @Override
    public void onCollision() { }

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
        addAction(Actions.moveTo(
            targetWorldPos.x * size, targetWorldPos.y * size, animationSpeed));
        worldPos.set(targetWorldPos);
    }

    private void hit(Direction direction) {
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

    public Entity clone() {
        //Entity entity = (Entity) super.clone();
        Entity newEntity = new Entity(worldPos.cpy(), Layout.TILE_SIZE, sprite);
        Array<Action> actions = getActions();
        actions.forEach(System.out::println);
        actions.forEach(newEntity::addAction);

        return newEntity;
    }
}
