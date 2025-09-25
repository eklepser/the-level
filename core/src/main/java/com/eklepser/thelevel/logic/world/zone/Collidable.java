package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.math.Rectangle;

public abstract class Collidable {
    private final Rectangle rect;

    public Collidable(Rectangle rect) {
        this.rect = rect;
    }

    public abstract void onCollision();

    public Rectangle getRect() {
        return rect;
    }
}
