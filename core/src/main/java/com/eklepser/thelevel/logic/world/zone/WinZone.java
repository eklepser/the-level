package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.graphics.game.WinWindow;
import com.eklepser.thelevel.logic.decoder.execution.Executor;

public class WinZone extends Zone {
    private final WinWindow window;
    private boolean activated = false;

    public WinZone(Rectangle rect, WinWindow window) {
        super(rect);
        this.window = window;
    }

    public static WinZone from(RectangleMapObject rectObj, WinWindow window) {
        Rectangle rect = rectObj.getRectangle();
        return new WinZone(rect, window);
    }

    @Override
    public void onPossibleCollision() { }

    @Override
    public void onCollision(Executor executor) {
        if (!activated) {
            System.out.println("WIN COLLISION DETECTED");
            executor.win();
        }
        activated = true;
    }

    public void setActivated(boolean activated) { this.activated = activated; }
}
