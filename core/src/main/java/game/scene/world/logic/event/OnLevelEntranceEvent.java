package game.scene.world.logic.event;

import game.scene.level.logic.LevelConfiguration;

public final class OnLevelEntranceEvent extends  WorldEvent {
    public final LevelConfiguration levelConfiguration;

    public OnLevelEntranceEvent(LevelConfiguration levelConfiguration) {
        this.levelConfiguration = levelConfiguration;
    }
}
