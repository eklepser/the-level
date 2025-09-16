package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.graphics.game.WinWindow;

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
    public void onCollision() {
        if (!activated) {
            System.out.println("WIN COLLISION DETECTED");
            window.toggle();
        }
        activated = true;
    }
}
