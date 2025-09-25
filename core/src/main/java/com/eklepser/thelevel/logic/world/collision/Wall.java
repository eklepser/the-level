package com.eklepser.thelevel.logic.world.collision;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class Wall implements Collidable {
    private final Rectangle rect;

    public Wall(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("wall collision");
        entity.resetTargetWorldPos();
        entity.hit();
    }

    public Rectangle getRect() {
        return rect;
    }
}
