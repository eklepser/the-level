package com.eklepser.thelevel.logic.world.collision.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class ColoredZoneOld extends ZoneOld {
    private final String colorName;

    public ColoredZoneOld(Rectangle rect, Executor executor, String colorName) {
        super(rect);
        this.colorName = colorName;
    }

    public static ColoredZoneOld from(RectangleMapObject rectObj, Executor executor) {
        Rectangle rect = rectObj.getRectangle();
        String color = rectObj.getProperties().get("color").toString();
        return new ColoredZoneOld(rect, executor, color);
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() {
        return colorName;
    }
}
