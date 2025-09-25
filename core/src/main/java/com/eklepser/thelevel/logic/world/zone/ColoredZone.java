package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;

public class ColoredZone extends Collidable {
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
    public void onCollision() {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() { return colorName; }
}
