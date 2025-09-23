package com.eklepser.thelevel.logic.game.level.zone;

import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;

public abstract class LevelZone {
    private final Rectangle rect;

    public LevelZone(Rectangle rect) {
        this.rect = rect;
    }

    public abstract void onPossibleCollision();

    public abstract void onCollision(Executor executor);

    public Rectangle getRect() { return rect; }
}
