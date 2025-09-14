package com.eklepser.thelevel.logic.world;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.level.Level;

import java.util.List;

public class CollisionManager {
    private final Level level;
    private final List<Rectangle> walls;

    public CollisionManager(Level level) {
        this.level = level;
        walls = level.getWalls();
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
