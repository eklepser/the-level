package game.scene.level.logic;

import game.data.level.LevelData;
import game.scene.common.logic.AbstractScene;
import game.scene.level.logic.command.Command;
import game.scene.level.logic.event.LevelEvent;
import game.scene.level.logic.event.NewCommandEvent;

public abstract class AbstractLevel extends AbstractScene<LevelEvent> {
    protected final LevelData levelData;

    public AbstractLevel(LevelData levelData) {
        super(levelData.tileMap);
        this.levelData = levelData;
    }

    public void makeTurn(Command command) {
        super.makeTurn();
        fire(new NewCommandEvent(command));
    }
}
