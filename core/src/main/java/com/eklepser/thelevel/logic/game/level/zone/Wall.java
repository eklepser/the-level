package com.eklepser.thelevel.logic.game.level.zone;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;

public class Wall extends LevelZone {
    public Wall(Rectangle rect) {
        super(rect);
    }

    @Override
    public void onPossibleCollision() {

    }

    @Override
    public void onCollision(Executor executor) {

    }
}
