package game.common.logic.zone;

import game.common.logic.entity.Entity;
import game.scene.world.logic.World;

public class LevelZone extends Zone {
    private final World world;
    private final int levelId;

    public LevelZone(int x, int y, World world, int levelId) {
        super(x, y);
        this.world = world;
        this.levelId = levelId;
    }

//    public static LevelZone from(RectangleMapObject rectObj, World world) {
//        Rectangle rect = rectObj.getRectangle();
//        int levelId = (int) rectObj.getProperties().get("id");
//        return new LevelZone(rect, world, levelId);
//    }

    @Override
    public void onCollision(Entity entity) {
//        if (world.getSelectedLevelId() != levelId) {
//            world.getScreen().getRoot().getLevelIdString().setText("/gray " + levelId);
//            world.setSelectedLevelId(levelId);
//            world.getScreen().getRoot().getSelectingLayout().update();
//        }
    }
}
