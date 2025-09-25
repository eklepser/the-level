package com.eklepser.thelevel.logic.world.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.level.World;

public class LevelZone extends Collidable {
    private final World world;
    private final int levelId;

    public LevelZone(Rectangle rect, World world, int levelId) {
        super(rect);
        this.world = world;
        this.levelId = levelId;
    }

    public static LevelZone from(RectangleMapObject rectObj, World world) {
        Rectangle rect = rectObj.getRectangle();
        int levelId = (int) rectObj.getProperties().get("id");
        return new LevelZone(rect, world, levelId);
    }

    @Override
    public void onCollision() {
        //world.setSelectedLevelId(levelId);
    }
}
