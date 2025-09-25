package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.level.World;

public abstract class WorldZone {
    private final Rectangle rect;

    public WorldZone(Rectangle rect) {
        this.rect = rect;
    }

    public abstract void onCollision(World world);

    public Rectangle getRect() { return rect; }
}
