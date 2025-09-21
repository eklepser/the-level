package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;

public class ColoredZone extends Zone {
    private final String colorName;

    public ColoredZone(Rectangle rect, String colorName) {
        super(rect);
        this.colorName = colorName;
    }

    public static ColoredZone from(RectangleMapObject rectObj) {
        Rectangle rect = rectObj.getRectangle();
        String color = rectObj.getProperties().get("color").toString();
        return new ColoredZone(rect, color);
    }

    @Override
    public void onPossibleCollision() {
        System.out.println("Colored Zone possible collision");
    }

    @Override
    public void onCollision(Executor executor) {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() { return colorName; }
}
