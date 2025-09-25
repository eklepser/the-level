package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.math.Rectangle;

public class Wall extends Collidable {
    public Wall(Rectangle rect) {
        super(rect);
    }

    @Override
    public void onCollision() {

    }
}
