package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.util.Direction;

public class Cat {
    private final Batch batch;
    private final Texture texture;
    private final Vector2 pos;
    private final int size;

    public Cat(Batch batch, Texture texture, Vector2 pos, int size) {
        this.batch = batch;
        this.texture = texture;
        this.pos = pos;
        this.size = size;
    }

    public void draw() {
        batch.draw(texture, pos.x, pos.y, size, size);
    }

    public void move(Direction direction)
    {
        pos.x += direction.vector.x * size;
        pos.y += direction.vector.y * size;
    }
}
