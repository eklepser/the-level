package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public class CollisionManager {
    private final List<Rectangle> walls;

    public CollisionManager(List<Rectangle> walls) {
        this.walls = walls;
    }

    public boolean checkWallCollisions(Rectangle rectangle) {
        for (Rectangle wall : walls) {
            if (rectangle.overlaps(wall)) {
                return true;
            }
        }
        return false;
    }
}
