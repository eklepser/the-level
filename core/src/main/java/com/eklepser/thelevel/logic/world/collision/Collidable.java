package com.eklepser.thelevel.logic.world.collision;

import com.eklepser.thelevel.logic.decoder.execution.Executor;

public interface Collidable {
    void onPossibleCollision();
    void onCollision(Executor executor);
}
