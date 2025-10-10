package game.scene.world.logic.event;

import game.scene.level.logic.LevelConfigurationOld;

public final class OnLevelEntranceEvent extends  WorldEvent {
    public final LevelConfigurationOld levelConfiguration;

    public OnLevelEntranceEvent(LevelConfigurationOld levelConfiguration) {
        this.levelConfiguration = levelConfiguration;
    }
}
