package game.scene.world.rendering;

import game.common.logic.event.EventListener;
import game.common.rendering.TableLayout;
import game.common.rendering.component.ColoredString;
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
            levelStatusLayout.setStatus(entranceEvent.levelConfiguration);
        }
    }
}
