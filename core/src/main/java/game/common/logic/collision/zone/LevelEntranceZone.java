package game.common.logic.collision.zone;

import game.common.logic.entity.Entity;
import game.scene.world.logic.World;

public final class LevelEntranceZone extends WorldZone {
    private final World world;
    private final String levelTag;

    public LevelEntranceZone(int x, int y, World world, String levelTag) {
        super(x, y);
        this.world = world;
        this.levelTag = levelTag;
    }

    @Override
    public void onCollision(Entity entity) {
        world.setSelectedLevelConfig(levelTag);
//        if (world.getSelectedLevelId() != levelId) {
//            world.getScreen().getRoot().getLevelIdString().setText("/gray " + levelId);
//            world.setSelectedLevelId(levelId);
//            world.getScreen().getRoot().getSelectingLayout().update();
//        }
    }
}
