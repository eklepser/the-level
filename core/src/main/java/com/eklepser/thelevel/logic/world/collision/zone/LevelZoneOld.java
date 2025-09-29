package com.eklepser.thelevel.logic.world.collision.zone;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.world.World;

public class LevelZoneOld extends ZoneOld {
    private final World world;
    private final int levelId;

    public LevelZoneOld(Rectangle rect, World world, int levelId) {
        super(rect);
        this.world = world;
        this.levelId = levelId;
    }

    public static LevelZoneOld from(RectangleMapObject rectObj, World world) {
        Rectangle rect = rectObj.getRectangle();
        int levelId = (int) rectObj.getProperties().get("id");
        return new LevelZoneOld(rect, world, levelId);
    }

    @Override
    public void onCollision(Entity entity) {
//        if (world.getSelectedLevelId() != levelId) {
//            world.getScreen().getRoot().getLevelIdString().setText("/gray " + levelId);
//            world.setSelectedLevelId(levelId);
//            world.getScreen().getRoot().getSelectingLayout().update();
//        }
    }
}
