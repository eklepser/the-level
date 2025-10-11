package game.scene.level.logic;

import game.data.level.LevelData;
import game.scene.common.logic.AbstractScene;
import game.scene.level.logic.event.LevelEvent;

public abstract class AbstractLevel extends AbstractScene<LevelEvent> {
    protected final LevelData levelData;

    public AbstractLevel(LevelData levelData) {
        super(levelData.tileMap);
        this.levelData = levelData;
    }
}
