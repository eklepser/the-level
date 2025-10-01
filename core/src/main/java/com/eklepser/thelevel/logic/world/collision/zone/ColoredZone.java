package com.eklepser.thelevel.logic.world.collision.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class ColoredZone extends Zone {
    private final String colorName;

    public ColoredZone(int x, int y, Executor executor, String colorName) {
        super(x, y);
        this.colorName = colorName;
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.println("Colored zone collision!");
    }

    public String getColorName() {
        return colorName;
    }
}
