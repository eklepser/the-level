package game.scene.world.rendering;

import game.common.rendering.TableLayout;
import game.common.rendering.component.ColoredString;
import game.scene.world.rendering.component.LevelStatusLayout;

public final class WorldLayout extends TableLayout {
    private final LevelStatusLayout levelStatusLayout;
    private final ColoredString levelIdString;

    public WorldLayout() {
        levelStatusLayout = new LevelStatusLayout();
        levelIdString = new ColoredString();

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(levelStatusLayout).left().padLeft(20).expandX();
        row();
        add().expandY();
        row();
        add(levelIdString).left().pad(0, 20, 15, 0).expandX();
    }

    //Getters:
    public LevelStatusLayout getSelectingLayout() {
        return levelStatusLayout;
    }

    public ColoredString getLevelIdString() {
        return levelIdString;
    }
}
