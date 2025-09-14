package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.collision.Collidable;

public abstract class Zone implements Collidable  {
    private final Rectangle rect;

    public Zone(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getRect() { return rect; }
}
