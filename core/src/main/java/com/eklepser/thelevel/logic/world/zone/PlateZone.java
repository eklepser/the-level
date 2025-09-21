package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;

public class PlateZone extends Zone {

    public PlateZone(Rectangle rect) {
        super(rect);
    }

    public static Zone from(RectangleMapObject rectObj) {
        Rectangle rect = rectObj.getRectangle();
        return new PlateZone(rect);
    }

    @Override
    public void onPossibleCollision() {

    }

    @Override
    public void onCollision(Executor executor) {

    }
}
