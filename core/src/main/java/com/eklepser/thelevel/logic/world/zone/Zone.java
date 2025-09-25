package com.eklepser.thelevel.logic.world.zone;


import com.badlogic.gdx.math.Rectangle;

public abstract class Zone implements Collidable {
    private final Rectangle rect;

    public Zone(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getRect() {
        return rect;
    }
}
