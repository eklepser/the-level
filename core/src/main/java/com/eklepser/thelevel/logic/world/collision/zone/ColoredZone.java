package com.eklepser.thelevel.logic.world.collision.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class ColoredZone extends Zone {
    private final String colorName;

    public ColoredZone(Rectangle rect, Executor executor, String colorName) {
        super(rect);
        this.colorName = colorName;
    }

    public static ColoredZone from(RectangleMapObject rectObj, Executor executor) {
        Rectangle rect = rectObj.getRectangle();
        String color = rectObj.getProperties().get("color").toString();
        return new ColoredZone(rect, executor, color);
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() {
        return colorName;
    }
}
