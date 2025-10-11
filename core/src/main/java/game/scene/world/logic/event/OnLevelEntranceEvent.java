package game.scene.world.logic.event;

import game.data.level.LevelData;

public final class OnLevelEntranceEvent extends  WorldEvent {
    public final LevelData levelData;

    public OnLevelEntranceEvent(LevelData levelData) {
        this.levelData = levelData;
    }
}
