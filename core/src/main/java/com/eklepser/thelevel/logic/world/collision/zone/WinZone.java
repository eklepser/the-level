package com.eklepser.thelevel.logic.world.collision.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.graphics.game.level.window.WinWindow;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class WinZone extends Zone {
    private final WinWindow window;
    private final Executor executor;
    private boolean activated = false;

    public WinZone(Rectangle rect, WinWindow window, Executor executor) {
        super(rect);
        this.window = window;
        this.executor = executor;
    }

    public static WinZone from(RectangleMapObject rectObj, WinWindow window, Executor executor) {
        Rectangle rect = rectObj.getRectangle();
        return new WinZone(rect, window, executor);
    }

    @Override
    public void onCollision(Entity entity) {
        if (!activated) {
            System.out.println("WIN COLLISION DETECTED");
            executor.win();
        }
        activated = true;
    }

    public void setActivated(boolean activated) { this.activated = activated; }
}
