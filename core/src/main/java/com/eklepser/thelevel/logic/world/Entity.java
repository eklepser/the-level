package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.util.Direction;

public class Entity {
    private final Batch batch;
    private final Texture texture;
    private Vector2 worldPos;
    private final int size;
    private Vector2 offset = Vector2.Zero;

    public Entity(Batch batch, Texture texture, Vector2 worldPos, int size) {
        this.batch = batch;
        this.texture = texture;
        this.worldPos = worldPos;
        this.size = size;
    }

    public Entity(Batch batch, String textureName, Vector2 worldPos, int size) {
        this.batch = batch;
        this.texture = new Texture(Gdx.files.internal(textureName));
        this.worldPos = worldPos;
        this.size = size;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public void draw() {
        float x = offset.x + worldPos.x;
        float y = offset.y + worldPos.y;
        batch.draw(texture, x, y, size, size);
        //batch.draw(texture, pos.x, pos.y, size, size);
    }

    public void move(Direction direction)
    {
        worldPos.x += direction.vector.x * size;
        worldPos.y += direction.vector.y * size;
    }
}
