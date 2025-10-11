package game.scene.world.logic.event;

import game.data.level.LevelData;
import game.data.user.LevelStatus;

public final class OnLevelEntranceEvent extends WorldEvent {
    public final LevelData levelData;
    public final LevelStatus levelStatus;

    public OnLevelEntranceEvent(LevelData levelData, LevelStatus levelStatus) {
        this.levelData = levelData;
        this.levelStatus = levelStatus;
    }
}
