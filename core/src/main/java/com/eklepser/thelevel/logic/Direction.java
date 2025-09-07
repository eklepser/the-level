package com.eklepser.thelevel.logic;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final Vector2 vector;

    Direction(float x, float y) {
        this.vector = new Vector2(x, y);
    }
}
