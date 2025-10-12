package game.scene.world.logic;

import game.data.world.WorldData;
import game.scene.common.logic.AbstractScene;
import game.scene.world.logic.event.WorldEvent;

public abstract class AbstractWorld extends AbstractScene<WorldEvent> {
    protected final WorldData worldData;

    public AbstractWorld(WorldData worldData) {
        super(worldData.tileMap);
        this.worldData = worldData;
    }

    public void makeTurn() {
        super.makeTurn();
    }
}
