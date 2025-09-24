package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.World;

public class LevelZone extends WorldZone {
    private final int levelId;

    public LevelZone(Rectangle rect, int levelId) {
        super(rect);
        this.levelId = levelId;
    }

    public static LevelZone from(RectangleMapObject rectObj) {
        Rectangle rect = rectObj.getRectangle();
        int levelId = (int) rectObj.getProperties().get("id");
        return new LevelZone(rect, levelId);
    }

    @Override
    public void onCollision(World world) {
        world.setSelectedLevelId(levelId);
    }
}
