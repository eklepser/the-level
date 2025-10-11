package game.scene.world.rendering;

import game.data.level.LevelData;
import game.scene.common.logic.event.EventListener;
import game.scene.common.rendering.TableLayout;
import game.scene.world.logic.World;
import game.scene.world.logic.event.OnLevelEntranceEvent;
import game.scene.world.logic.event.WorldEvent;
import game.scene.world.rendering.component.LevelStatusLayout;

public final class WorldLayout extends TableLayout implements EventListener<WorldEvent> {
    private final LevelStatusLayout levelStatusLayout;

    public WorldLayout(World world) {
        world.subscribe(this);

        levelStatusLayout = new LevelStatusLayout();

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(levelStatusLayout).left().padLeft(20).expandX();
        row();
        add().expandY();
    }

    @Override
    public void onEvent(WorldEvent event) {
        if (event instanceof OnLevelEntranceEvent entranceEvent) {
            System.out.println("EVENT");
            LevelData levelData = entranceEvent.levelData;
            if (levelData == null) return;
            levelStatusLayout.setStatus(levelData);
        }
    }
}
