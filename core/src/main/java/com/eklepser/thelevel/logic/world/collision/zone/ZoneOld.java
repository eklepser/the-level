package com.eklepser.thelevel.logic.world.collision.zone;


import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.collision.Collidable;

public abstract class ZoneOld implements Collidable {
    private final Rectangle rect;

    public ZoneOld(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getRect() {
        return rect;
    }
}
